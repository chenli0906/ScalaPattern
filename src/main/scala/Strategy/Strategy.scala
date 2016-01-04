/**
  * Created by 10170216 on 2015/12/5.
  */
object Strategy {

  case class Person(
                     firstName: Option[String],
                     middleName: Option[String],
                     lastName: Option[String])

  def isFirstNameValid(person: Person) = person.firstName.isDefined

  def isFullNameValid(person: Person) = person match {
    case Person(firstName, middleName, lastName) =>
      firstName.isDefined && middleName.isDefined && lastName.isDefined
  }

  //将策略通过高阶函数传入
  def personCollector(isValid: (Person) => Boolean) = {
    var validPeople = Vector[Person]()
    (person: Person) => {
      if(isValid(person)) validPeople = validPeople :+ person
      validPeople
    }
  }

  def main(args: Array[String]) {

    val singleNameValidCollector = personCollector(isFirstNameValid)

    val fullNameValidCollector = personCollector(isFullNameValid)

    val p1 = Person(Some("John"), Some("Quincy"), Some("Adams"))

    val p2 = Person(Some("Mike"), None, Some("Linn"))

    val p3 = Person(None, None, None)

    println(singleNameValidCollector(p1))

    println(singleNameValidCollector(p2))

    println(singleNameValidCollector(p3))

    println(fullNameValidCollector(p1))

    println(fullNameValidCollector(p2))

    println(fullNameValidCollector(p2))

    //没有形成闭包，未保存前一次的结果
    println(personCollector(isFirstNameValid)(p1))

    println(personCollector(isFirstNameValid)(p2))
  }
}
