package CustomControlFlow

/**
  * Created by Administrator on 16-1-26.
  */
object Choose {

  //Call By Value
  def test1[E](expression: E) = {expression;expression}
  //Call By Name
  def test2[E](expression: => E) = {expression;expression}

  def simplerChoose[E](num: Int, first: => E, second: => E, third: => E) =
    if (num == 1) first
    else if (num == 2) second
    else if (num == 3) third

  def main(args: Array[String]) {

    test2(println("hello"))

    simplerChoose(2,
      println("hello, world"),
      println("goodbye, cruel world"),
      println("meh, indifferent world"))
  }
}
