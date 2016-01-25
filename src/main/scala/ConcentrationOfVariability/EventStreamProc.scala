package ConcentrationOfVariability

import scala.collection.mutable
import scala.util.Random

/**
  * Created by Administrator on 16-1-25.
  */
object EventStreamProc {

  case class Purchase(storeNumber: Int, customerNumber: Int, itemNumber: Int)

  val r = new Random
  def makeTestPurchase = Purchase(r.nextInt(100), r.nextInt(1000), r.nextInt(500))
  def infiniteTestPurchases: Stream[Purchase] =
    makeTestPurchase #:: infiniteTestPurchases

  def immutableSequenceEventProcessing(count: Int) = {
    val testPurchases = infiniteTestPurchases.take(count)
    var mapOfPurchases = Map[Int, List[Purchase]]()
    for (purchase <- testPurchases)
      mapOfPurchases.get(purchase.storeNumber) match {
        case None => mapOfPurchases =
          mapOfPurchases + (purchase.storeNumber -> List(purchase))
        case Some(existing: List[Purchase]) => mapOfPurchases =
          mapOfPurchases + (purchase.storeNumber -> (purchase :: existing))
      }
    mapOfPurchases
  }

  def mutableSequenceEventProcessing(count: Int) = {
    val testPurchases = infiniteTestPurchases.take(count)
    val mapOfPurchases = mutable.Map[Int, List[Purchase]]()
    for (purchase <- testPurchases)
      mapOfPurchases.get(purchase.storeNumber) match {
        case None => mapOfPurchases.put(purchase.storeNumber, List(purchase))
        case Some(existing: List[Purchase]) =>
          mapOfPurchases.put(purchase.storeNumber, purchase :: existing)
      }
    mapOfPurchases.toMap
  }

  def main(args: Array[String]) {
    val fiveTestPurchases = infiniteTestPurchases.take(5)
    for(purchase <- fiveTestPurchases) println(purchase)

    val fiveHundredThousand = 500000

    println("immutable version:")
    AddElementToSeq.timeRuns(immutableSequenceEventProcessing(fiveHundredThousand), 5)
    println("mutable version:")
    AddElementToSeq.timeRuns(mutableSequenceEventProcessing(fiveHundredThousand), 5)
  }
}
