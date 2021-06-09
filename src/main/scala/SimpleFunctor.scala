import cats.Functor
import scala.util.Try

object SimpleFunctor {
  // a small and simple definition
  trait MyFunctorExample[F[_]] {
    def map[A, B](value: F[A])(transformer: A => B): F[B]
  }

  val myList = List(10,50,100).map(_ + 1) // List(11,51,101)
  val myOption = Option(9).map(_ + 1) // Some(10)
  val myTry = Try(2).map(_ + 1) // Success(3)

  trait MyTree[+T]
  object MyTree {
    def leaf[T](value: T): MyTree[T] = Leaf(value)
    def branch[T](
                   value: T,
                   left: MyTree[T],
                   right: MyTree[T]
                 ): MyTree[T] = Branch(value, left, right)
  }
  case class Leaf[+T](value: T) extends MyTree[T]
  case class Branch[+T](
                         value: T,
                         left: MyTree[T],
                         right: MyTree[T]
                       ) extends MyTree[T]

  val myTree: MyTree[Int] = MyTree.branch(
    40,
    MyTree.branch(1, MyTree.leaf(10), MyTree.leaf(30)),
    MyTree.leaf(20)
  )

  @main def executeFunctor() = {
    import cats.instances.list._ // includes implicits for Functor[List]
    val myListFunctor = Functor[List]
    val listResult = myListFunctor.map(myList)(_ + 1) // List(11,51,101)
    println(listResult)

    import cats.instances.option._ // includes implicits for Functor[Option]
    val myOptionFunctor = Functor[Option]
    val optionResult = myOptionFunctor.map(myOption)(_ + 1) // Some(10)
    println(optionResult)

    import cats.instances.try_._ // includes implicits for Functor[Try]
    val myTryFunctor = Functor[Try]
    val tryResult = myTryFunctor.map(Try(2))(_ + 1) // Success(3)
    println(tryResult)

    // My own functor to the MyTree structure
    // Using Scala3 Uhuuu
    given Functor[MyTree] with
      override def map[A, B](fa: MyTree[A])(f: A => B): MyTree[B] = fa match {
        case Leaf(v) => Leaf(f(v))
        case Branch(v, left, right) => Branch(f(v), map(left)(f), map(right)(f))
      }

    val myTreeFunctor = Functor[MyTree]
    val myTreeResult = myTreeFunctor.map(myTree)(_ + 1)
    println(myTreeResult)
  }
}
