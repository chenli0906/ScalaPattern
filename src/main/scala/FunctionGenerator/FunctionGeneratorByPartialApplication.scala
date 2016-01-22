package FunctionGenerator

/**
  * Created by Administrator on 16-1-22.
  */
object FunctionGeneratorByPartialApplication {

  def addTwoInts(intOne: Int, intTwo: Int) = intOne + intTwo

  val addFortyTwo = addTwoInts(42, _: Int)

  def taxForState(amount: Double, state: Symbol) = state match {

    case ('NY) => amount * 0.0645
    case ('PA) => amount * 0.045

  }
  val nyTax = taxForState(_: Double, 'NY)
  val paTax = taxForState(_: Double, 'PA)

  def main(args: Array[String]) {

    println(addFortyTwo(100))

    println(nyTax(100))

    println(paTax(100))

  }
}
