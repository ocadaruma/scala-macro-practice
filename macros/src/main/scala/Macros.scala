import scala.reflect.macros.blackbox
import scala.language.experimental.macros
import scala.annotation.StaticAnnotation

class bench extends StaticAnnotation {
  def macroTransform(annottees: Any*) = macro benchMacro.impl
}

object benchMacro {
  def impl(c: blackbox.Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._

    val input = annottees.map(_.tree).toList
    println(input)
    val result = {
      input match {
        case q"$mods def $tname[..$tparams](...$paramss): $tpt = $expr" :: Nil =>
          q"""
             $mods def $tname[..$tparams](...$paramss): $tpt = {
               val __start__ = System.nanoTime()
               val __result__ = $expr
               val __end__ = System.nanoTime()

               print("elapsed: ")
               print(__end__ - __start__)
               println("ns")

               __result__
             }
          """
        case _ => c.abort(c.enclosingPosition, "Cannot be applied.")
      }
    }
    c.Expr[Any](result)
  }
}
