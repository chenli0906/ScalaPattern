package Decorator

/**
  * Created by 10170216 on 2015/12/12.
  */
object Decorator {

  def add(a: Int, b: Int) = a + b

  def subtract(a: Int, b: Int) = a - b

  def multiply(a: Int, b: Int) = a * b

  def divide(a: Int, b: Int) = a / b

  def makeLogger(calcFn: (Int, Int) => Int) =
    (a: Int, b: Int) => {
      val result = calcFn(a, b)
      println("Result is: " + result)
      result
    }

  def main(args: Array[String]) {
    val loggingAdd = makeLogger(add)
    val loggingSubtract = makeLogger(subtract)
    val loggingMultiply = makeLogger(multiply)
    val loggingDivide = makeLogger(divide)

    println(loggingAdd(2,3))
  }
}
