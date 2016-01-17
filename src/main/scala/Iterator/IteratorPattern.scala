package Iterator

/**
  * Created by 10170216 on 2015/11/30.
  */
object IteratorPattern {
  case class Address(zip: Int)
  case class Person(name: String, address: Address)

  def isCloseZip(zipCode: Int) = zipCode == 19123 || zipCode == 19103

  def generateGreetings(people: Seq[Person]) =
    for (Person(name,address) <- people if isCloseZip(address.zip) ) yield {
      "Hello, %s".format(name)
    }

  def main(args: Array[String]) {
    val person1 = Person("Jack",Address(19100))
    val person2 = Person("Smith",Address(19101))
    val person3 = Person("Leo",Address(19103))
    val person4 = Person("Robot",Address(19123))

    val people = List(person1,person2,person3,person4)

    println(generateGreetings(people))
  }
}
