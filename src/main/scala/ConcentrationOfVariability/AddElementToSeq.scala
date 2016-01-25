package ConcentrationOfVariability

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 16-1-25.
  */
object AddElementToSeq {

  //Or LinearSeq? Or prepend?
  def testImmutable(count: Int): IndexedSeq[Int] = {
    var v = Vector[Int]()
    for (c <- Range(0, count))
      v = v :+ c
    v
  }

  def testMutable(count: Int): IndexedSeq[Int] = {
    val s = ArrayBuffer[Int](count)
    for (c <- Range(0, count))
      s.append(c)
    s.toIndexedSeq
  }

  def time[R](block: => R): R = {
    val start = System.nanoTime
    val result = block
    val end = System.nanoTime
    val elapsedTimeMs = (end - start) * 0.000001
    println("Elapsed time: %.3f msecs".format(elapsedTimeMs))
    result
  }
  def timeRuns[R](block: => R, count: Int) =
    for (_ <- Range(0, count)) time { block }

  def main(args: Array[String]) {
    val oneMillion = 1000000
    println("Immutable Version:")
    timeRuns(testImmutable(oneMillion), 5)
    println("Mutable Version:")
    timeRuns(testMutable(oneMillion), 5)
  }
}
