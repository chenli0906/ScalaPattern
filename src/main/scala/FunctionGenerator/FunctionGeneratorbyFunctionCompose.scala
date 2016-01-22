package FunctionGenerator

/**
  * Created by LeoChen on 2016/1/20.
  */
object FunctionGeneratorByFunctionCompose {

  case class HttpRequest(
                          headers: Map[String, String],
                          payload: String,
                          principal: Option[String] = None)

  def checkAuthorization(request: HttpRequest): HttpRequest = {
    val authHeader = request.headers.get("Authorization")
    val mockPrincipal = authHeader match {
      case Some(headerValue) => Some("AUser")
      case _ => None
    }
    request.copy(principal = mockPrincipal)
  }

  def logFingerprint(request: HttpRequest): HttpRequest = {
    val fingerprint = request.headers.getOrElse("X-RequestFingerprint", "")
    println("FINGERPRINT=" + fingerprint)
    request
  }

  def composeFilters(filters: Seq[(HttpRequest) => HttpRequest]) =
    filters.reduce {
      (allFilters, currentFilter) => allFilters compose currentFilter
    }

  def main(args: Array[String]) {

    val filters = Vector(checkAuthorization _,logFingerprint _)

    val filterChain = composeFilters(filters)

    val requestHeaders = Map("Authorization" -> "Auth", "X-RequestFingerprint" -> "fingerprint")

    val request = HttpRequest(requestHeaders,"body")

    filterChain(request)

  }
}
