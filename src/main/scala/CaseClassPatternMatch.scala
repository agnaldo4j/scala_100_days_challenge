abstract class BaseCaseClass

case class MyCaseClass(teste: String, idade: Int) extends BaseCaseClass

case class MySecondCaseClass(cidade: String) extends BaseCaseClass

def doMatch(caseClass: BaseCaseClass) = caseClass match {
  case myVal@MyCaseClass("name", 46) => s"same attributes $myVal"
  case myVal@MyCaseClass(teste, idade) => {
    s"""
    extracted attributes: $teste - $idade
    automatic casting value: ${myVal.teste}
    using baseClass need explicit casting: ${caseClass.asInstanceOf[MyCaseClass].teste}
    """.stripMargin
  }
  case myVal: MyCaseClass => s"MyCaseClass: $myVal"
  case myVal: MySecondCaseClass => s"MySecondCaseClass: $myVal"
  case _ => "default"
}

@main def execute() = {
  println(doMatch(MyCaseClass("nome", 46)))
  println(doMatch(MySecondCaseClass("name")))
}