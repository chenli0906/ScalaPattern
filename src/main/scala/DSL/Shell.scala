package DSL

import scala.collection.JavaConverters._
import scala.io.Source

/**
  * Created by Administrator on 16-1-26.
  */
object Shell {

  case class CommandResult(status: Int, output: String, error: String)

  class Command(commandParts: List[String]) {
    def run: CommandResult = {
      val processBuilder = new ProcessBuilder(commandParts.asJava)
      val process = processBuilder.start()
      val status = process.waitFor()
      val test = process.getInputStream
      val outputAsString =
        Source.fromInputStream(process.getInputStream)("GB2312").mkString("")
      val errorAsString =
        Source.fromInputStream(process.getErrorStream)("GB2312").mkString("")
      CommandResult(status, outputAsString, errorAsString)
    }
  }

  implicit class CommandString(firstCommandString: String) {
    def run = Command(firstCommandString).run
    def pipe(secondCommandString: String) =
      Vector(firstCommandString, secondCommandString)
  }

  implicit class CommandVector(existingCommands: Vector[String]) {
    def run = {
      val pipedCommands = existingCommands.mkString(" | ")
      Command(pipedCommands).run
    }
    def pipe(nextCommand: String): Vector[String] = {
      existingCommands :+ nextCommand
    }
  }

  object Command {
    def apply(commandString: String) = new Command(commandString.split(" ").toList)
    def apply(commandParts: String*) = new Command(commandParts.toList)
  }

  def main(args: Array[String]) {
    println("ping baidu.com" run)
    println("ping baidu.com" pipe "find \"Reply from\"" run )
  }
}
