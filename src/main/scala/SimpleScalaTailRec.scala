import scala.annotation.tailrec

object SimpleScalaTailRec {
  def main(args: Array[String]) = {
    val result = sum(List(1,2,3,4,5), 0)
    println(s"The sum is: $result")
  }

  @tailrec def sum(ints: List[Int], acc: Int): Int = ints match {
    case Nil => acc
    case (head :: tail) => sum(tail, head + acc)
  }
}
