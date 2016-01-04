/**
  * Created by 10170216 on 2015/12/14.
  */
object Visitor {

  trait Person {
    def fullName: String
    def firstName: String
    def lastName: String
    def houseNum: Int
    def street: String
  }

  class SimplePerson(val firstName: String, val lastName: String,
                     val houseNum: Int, val street: String) extends Person {
    def fullName = firstName + " " + lastName
  }

  implicit class ExtendedPerson(person: Person) {
    def fullAddress = person.houseNum + " " + person.street
  }

  class ComplexPerson(name: Name, address: Address) extends Person {
    def fullName = name.firstName + " " + name.lastName
    def firstName = name.firstName
    def lastName = name.lastName
    def houseNum = address.houseNum
    def street = address.street
  }
  class Address(val houseNum: Int, val street: String)
  class Name(val firstName: String, val lastName: String)


  trait PerimeterShapes {
    trait Shape {
      def perimeter: Double
    }
    class Circle(radius: Double) extends Shape {
      def perimeter = 2 * Math.PI * radius
    }
    class Rectangle(width: Double, height: Double) extends Shape {
      def perimeter = 2 * width + 2 * height
    }
  }

  trait AreaShapes extends PerimeterShapes {
    trait Shape extends super.Shape {
      def area: Double
    }
    class Circle(radius: Double) extends super.Circle(radius) with Shape {
      def area = Math.PI * radius * radius
    }
    class Rectangle(width: Double, height: Double)
      extends super.Rectangle(width, height) with Shape {
      def area = width * height
    }
  }

  trait MorePerimeterShapes extends PerimeterShapes {
    class Square(side: Double) extends Shape {
      def perimeter = 4 * side
    }
  }

  trait MoreAreaShapes extends AreaShapes with MorePerimeterShapes {
    class Square(side: Double) extends super.Square(side) with Shape {
      def area = side * side
    }
  }

  def main(args: Array[String]) {

    //新的实现
    val simplePerson = new SimplePerson("Mike", "Linn", 123, "Fake. St.")
    println(simplePerson.fullName)

    //新的操作(隐式转换)
    println(simplePerson.fullAddress)

    //同时扩展新的操作和新的实现
    val name = new Name("Mike", "Linn")
    val address = new Address(123, "Fake St.")
    val complexPerson = new ComplexPerson(name, address)
    complexPerson.fullAddress

    //使用混入类型扩展捆绑在一起的数据结构和操作（类型安全）
    object SecondShapeExample extends AreaShapes {
      val someShapes = Vector(new Circle(4), new Rectangle(2, 2))
    }
    for(shape <- SecondShapeExample.someShapes) yield shape.perimeter
    for (shape <- SecondShapeExample.someShapes ) yield shape.area

    object ThirdShapeExample extends MoreAreaShapes {
      val someMoreShapes = Vector(new Circle(4), new Rectangle(2, 2), new Square(4))
    }
    for (shape <- ThirdShapeExample.someMoreShapes ) yield shape.perimeter
    for (shape <- ThirdShapeExample.someMoreShapes ) yield shape.area
  }
}
