object Sample {
  @bench def f(): Int = {
    Thread.sleep(1000)
    42
  }
}
