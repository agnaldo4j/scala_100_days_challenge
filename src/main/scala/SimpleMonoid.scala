import cats.Monoid

object SimpleMonoid {

  case class ShoppingCart(items: List[String], total: Double)
  implicit val shoppingCartMonoid: Monoid[ShoppingCart] = Monoid.instance(
    ShoppingCart(List(), 0.0),
    (sa, sb) => ShoppingCart(sa.items ++ sb.items, sa.total + sb.total)
  )

  import cats.syntax.monoid._
  def checkout[T](list: List[T])(implicit monoid: Monoid[T]): T =
    list.foldLeft(monoid.empty)(_ |+| _)

  def main(args: Array[String]): Unit = {
    println(checkout(List(
      ShoppingCart(List("phone", "shoes"), 799),
      ShoppingCart(List("laptop"), 20000),
      ShoppingCart(List(), 0)
    )))
  }
}
