package FunctionalInterfaces

/**
  * Created by LeoChen on 2015/11/8.
  */
object PersonExample {
  (int1: Int, int2: Int) => int1 + int2

  case class Person(firstName: String, lastName: String)

  val p1 = Person("Michael", "Bevilacqua")
  val p2 = Person("Pedro", "Vasquez")
  val p3 = Person("Robert", "Aarons")

  val people = Vector(p3, p2, p1)

  def main(args: Array[String]) {

    //anonymous function
    val sortedPeople = people.sortWith((p1, p2) => p1.firstName < p2.firstName)

    println(sortedPeople)
  }
}
