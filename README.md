# scala-macro-practice

こういう↓benchアノテーションを作ってみます

- メソッドに@benchとつけると、メソッドの実行時間がprintされるようになる
- メソッド以外につけるとコンパイルエラーになる

## 実行例

```bash:
$ sbt sample/console
scala> Sample.f()
elapsed: 1002961000ns
res0: Int = 42
```
