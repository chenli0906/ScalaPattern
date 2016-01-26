package CustomControlFlow

/**
  * Created by Administrator on 16-1-26.
  */
object AvgTime {

  def timeRun[E](toTime: => E) = {
    val start = System.currentTimeMillis
    toTime
    System.currentTimeMillis - start
  }

  def avgTime[E](times: Int, toTime: => E) = {
    val allTimes = for (_ <- Range(0, times)) yield timeRun(toTime)
    allTimes.sum / times
  }

  def main(args: Array[String]) {
    println(avgTime(5, Thread.sleep(1000)))
  }

}
