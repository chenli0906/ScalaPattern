package InertSequence

import scala.util.Random

/**
  * Created by Administrator on 16-1-23.
  */
object StreamSeq {

  val integers = Stream.from(0)
  val generate = new Random()
  val randoms = Stream.continually(generate.nextInt)

  def main(args: Array[String]) {
    val someints = integers take 5

    someints foreach println

    println("=========================")

    val aFewRandoms = randoms take 5

    aFewRandoms foreach println

    println("=========================")

    val aFewMoreRandoms = randoms take 6

    aFewMoreRandoms foreach println
  }
}
