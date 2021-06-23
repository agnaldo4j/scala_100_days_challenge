object SimpleGivenEvample {

  // implict value
  given implicitInt: Int = 10

  //using implicit value
  def printGivenValue(using intValue: Int) = println(intValue)

  @main def executeGiven() = {
    //call method using implicit value
    printGivenValue
  }
}
