object Semigroups {
  // Semigroups combine elements of the same type
  import cats.Semigroup
  import cats.instances.int._
  import cats.instances.string._

  // My own semigroup to my Expense Case Class
  case class Expense(id: Long, amount: Double)
  given Semigroup[Expense] with
    override def combine(
                          firstExpense: Expense,
                          secondExpense: Expense
                        ) = Expense(
      Math.max(firstExpense.id, secondExpense.id),
      firstExpense.amount + secondExpense.amount
    )

  //API
  def combineThings[T](list: List[T])
                      (implicit semigroup: Semigroup[T]): T = {
    list.reduce(semigroup.combine)
  }

  @main def main() = {
    // combine Int result: 48
    println(combineThings(List(2, 46)))

    // combine String result Hello Cats
    println(combineThings(List("Hello ", "Cats")))

    // combine result: Expense(3,120.0)
    println(combineThings(List(Expense(1, 50), Expense(2, 30), Expense(3, 40))))
  }
}