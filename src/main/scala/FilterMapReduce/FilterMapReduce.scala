package FilterMapReduce

/**
  * Created by 10170216 on 2016/1/15.
  */
object FilterMapReduce {

  val priceList = List(15.3,21.3,45.6,34.0,22.0,12.0)

  def calculateDiscount(prices : Seq[Double]) : Double = {
    prices filter(price => price >= 20.0) map
      (price => price * 0.10) reduce
      ((total, price) => total + price)
  }

  def main(args: Array[String]) {
    println(calculateDiscount(priceList))
  }
}
