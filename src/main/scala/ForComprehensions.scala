val myOpt: Option[Int] = Some(1)

@main def executeForComprehensions() = {
  val pairs = for {
    //generators
    num <- List(1, 2, 3)
    char <- List('a', 'b', 'c')
    optInt <- myOpt

    // definitions
    sumValue = optInt + 1

    // filters
    if optInt > 0
  } yield num + "-" + char + "-" + optInt + "-" + sumValue
  println(s"saida: $pairs")
}