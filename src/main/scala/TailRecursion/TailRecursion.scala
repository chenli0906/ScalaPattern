package TailRecursion

import scala.annotation.tailrec

/**
  * Created by LeoChen on 2016/1/8.
  */
object TailRecursion {

  case class Person(firstNames: String, lastNames: String)

  def makePeople(firstNames: Seq[String], lastNames: Seq[String]) = {
    @tailrec
    def helper(firstNames: Seq[String], lastNames: Seq[String],
               people: Vector[Person]): Seq[Person] =
      if (firstNames.isEmpty)
        people
      else {
        val newPerson = Person(firstNames.head, lastNames.head)
        helper(firstNames.tail, lastNames.tail, people :+ newPerson)
      }
    helper(firstNames, lastNames, Vector[Person]())
  }

  def main(args: Array[String]) {
    val firstName = List("Taylor","Lady","Justin")
    val lastName = List("Swift","Gaga","Bieber")

    println(makePeople(firstName,lastName))
  }
}
