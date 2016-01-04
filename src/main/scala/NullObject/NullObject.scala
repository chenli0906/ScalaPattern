/**
  * Created by 10170216 on 2015/12/10.
  */
object NullObject {

  case class Person(firstName: String="John", lastName: String="Doe")

  val nullPerson = Person()

  def fetchPerson(people: Map[Int, Person], id: Int) =
    people.getOrElse(id, nullPerson)

  def buildPerson(firstNameOption: Option[String], lastNameOption: Option[String]) =
    (for(
      firstName <- firstNameOption;
      lastName <- lastNameOption)
      yield Person(firstName, lastName)).getOrElse(Person("John", "Doe"))

  def main(args: Array[String]) {
    val joe = Person("Joe", "Smith")
    val jack = Person("Jack", null)
    val somePeople = Map(1 -> joe, 2 -> jack)

    println(fetchPerson(somePeople, 2))

    println(buildPerson(Some("Mike"), Some("Linn")))

    println(buildPerson(Some("Smith"),None))
  }
}
