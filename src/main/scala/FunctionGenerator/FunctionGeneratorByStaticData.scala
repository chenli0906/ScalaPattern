package FunctionGenerator

import scala.annotation.tailrec

/**
  * Created by LeoChen on 2016/1/18.
  */
object FunctionGeneratorByStaticData {

  //Generating function through the static data
  def discount(percent: Double) = {
    if(percent < 0.0 || percent > 100.0)
      throw new IllegalArgumentException("Discounts must be between 0.0 and 100.0.")
    (originalPrice: Double) =>
      originalPrice - (originalPrice * percent * 0.01)
  }

  //retrieve data from the depth nested Map
  def selector(path: Symbol*): (Map[Symbol, Any] => Option[Any]) = {
    if(path.size <= 0) throw new IllegalArgumentException("path must not be empty")
    @tailrec
    def selectorHelper(path: Seq[Symbol], ds: Map[Symbol, Any]): Option[Any] =
      if(path.size == 1) {
        ds.get(path.head)
      }else{
        val currentPiece = ds.get(path.head)
        currentPiece match {
          case Some(currentMap: Map[Symbol, Any]) =>
            selectorHelper(path.tail, currentMap)
          case None => None
          case _ => None
        }
      }
    (ds: Map[Symbol, Any]) => selectorHelper(path.toSeq, ds)
  }

  def main(args: Array[String]) {
    println(discount(50)(300))

    val simplePerson: Map[Symbol, String] = Map('name -> "Michael Bevilacqua-Linn")
    val name = selector('name)
    println(name(simplePerson))

    val moreComplexPerson = Map('name -> Map('first -> "Michael", 'last -> "Bevilacqua-Linn"))
    val firstName = selector('name, 'last)
    println(firstName(moreComplexPerson))
  }

}
