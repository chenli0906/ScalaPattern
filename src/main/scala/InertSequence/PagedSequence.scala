package InertSequence

/**
  * Created by LeoChen on 2016/1/24.
  */
object PagedSequence {

  def pagedSequence(pageNum: Int): Stream[String] =
    getPage(pageNum) match {
      case Some(page: String) => page #:: pagedSequence(pageNum + 1)
      case None => Stream.Empty
    }

  def getPage(page: Int) =
    page match {
      case 1 => Some("Page1")
      case 2 => Some("Page2")
      case 3 => Some("Page3")
      case _ => None
    }

  def main(args: Array[String]) {
    println(pagedSequence(1) take 2 force)

    println(pagedSequence(1) force)
  }
}
