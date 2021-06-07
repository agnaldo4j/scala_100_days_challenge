object TypeClassScala3 {
  case class Circle(radius: Double)
  case class Rectangle(width: Double, length: Double)

  trait Shape[A]:
    extension (shape: A)
      def area(): Double

  given Shape[Circle] with
    extension (shape: Circle)
      def area(): Double = math.Pi * math.pow(shape.radius, 2)

  given Shape[Rectangle] with
    extension (shape: Rectangle)
      def area(): Double = (shape.width * shape.length)

  def areaOf[A: Shape](shape: A): Double = shape.area()
}

@main def mainTypeClassScala3 = {
  import TypeClassScala3._
  println(areaOf(Circle(10)))
  println(areaOf(Rectangle(5, 5)))
}