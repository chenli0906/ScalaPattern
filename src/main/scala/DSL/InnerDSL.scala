package DSL

import scala.sys.process._

/**
  * Created by Administrator on 16-1-26.
  */
object InnerDSL {

  def main(args: Array[String]) {
    "ping baidu.com" #| "find \"Reply from\"" !
  }
}
