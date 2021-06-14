import scala.annotation.tailrec
import scala.util.{Left, Right}

/*
Your task is to process a sequence of integer numbers to determine the following statistics:
    o) minimum value
    o) maximum value
    o) number of elements in the sequence
    o) average value

For example: [6, 9, 15, -2, 92, 11]
    o) minimum value = -2
    o) maximum value = 92
    o) number of elements in the sequence = 6
    o) average value = 21.833333
*/
object CalcStats {

  case class Statistics(min: Int, max: Int, size: Int, sum: Double = 0.0) {
    def avg: Double = sum / size

    def increment(next: Int): Statistics = copy(
      min = if (min > next) next else min,
      max = if (max < next) next else max,
      sum = sum + next
    )

    def printStats() = {
      println(s"min: ${min}")   // -2
      println(s"max: ${max}")   // 92
      println(s"size: ${size}") // 6
      println(s"avg: ${avg}")   // 21.83
    }
  }

  object Statistics {
    def apply(list: List[Int]): Statistics = Statistics(
      min = list.head,
      max = list.head,
      size = list.size
    )
  }

  class CalcStats(list: List[Int]) {
    def calculate(): Either[Exception, Statistics] = list match {
      case Nil => Left(IllegalArgumentException("List must not be empty"))
      case null => Left(IllegalArgumentException("List must not be null"))
      case (head :: tail) => calculateStatistics(list, Statistics(list))
      case _ => Left(IllegalArgumentException("Invalid Type"))
    }

    @tailrec
    private def calculateStatistics(
                                     list: List[Int],
                                     estatisticas: Statistics
                                   ): Either[Exception, Statistics] = list match {
      case Nil => Right(estatisticas)
      case (head :: tail) => calculateStatistics(tail, estatisticas.increment(head))
      case _ => Left(IllegalStateException("Invalid Type"))
    }
  }

  @main def mainCalcStats = {
    new CalcStats(List(6, 9, 15, -2, 92, 11)).calculate() match {
      case Right(estatisticas: Statistics) => estatisticas.printStats()
      case Left(error) => println(s"error: ${error.getMessage}")
    }
  }
}