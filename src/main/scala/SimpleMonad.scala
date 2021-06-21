import cats.Monad
import cats.syntax.flatMap._
import cats.syntax.functor._

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}

object SimpleMonad {
  /*
    Small definition
    - wrapping a value into a monadic value
    - the flatMap mechanism
    MONADS
  */
  trait SimpleMonad[M[_]] {
    def pure[A](value: A): M[A]
    def flatMap[A, B](ma: M[A])(f: A => M[B]): M[B]
    def map[A, B](ma: M[A])(f: A => B): M[B] = flatMap(ma)(x => pure(f(x)))
  }

  // flatMap, map call
  def getPairs[M[_], A, B](ma: M[A], mb: M[B])(implicit monad: Monad[M]): M[(A, B)] =
    monad.flatMap(ma)(a => monad.map(mb)(b => (a, b)))

  // same result of above implementation but using for for-comprehensions
  def getPairsFor[M[_] : Monad, A, B](ma: M[A], mb: M[B]): M[(A, B)] =
    for {
      a <- ma
      b <- mb
    } yield (a, b)

  implicit val ec: ExecutionContext = ExecutionContext
    .fromExecutorService(Executors.newFixedThreadPool(8))

  def main(args: Array[String]): Unit = {
    println(getPairsFor(List(1,2,3), List('a', 'b', 'c')))
    println(getPairsFor(Option(2), Option('d')))
    getPairsFor(Future(42), Future('Z')).foreach(println)
  }
}
