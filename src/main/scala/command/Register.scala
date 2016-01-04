package command

/**
  * Created by LeoChen on 2015/11/12.
  */
//客户端
object Register {

  //命令
  def makePurchase(register: CashRegister, amount: Int) = {
    () => {
      println("Purchase in amount: " + amount)
      register.addCash(amount)
    }
  }

  //调用者
  var purchases: Vector[() => Unit] = Vector()
  def executePurchase(purchase: () => Unit) = {
    purchases = purchases :+ purchase
    purchase()
  }
}

//接收者
class CashRegister(var total: Int) {
  def addCash(toAdd: Int) {
    total += toAdd
  }
}

object Main extends App{

  val register = new CashRegister(0)

  val purchaseOne = Register.makePurchase(register, 100)

  val purchaseTwo = Register.makePurchase(register, 50)

  Register.executePurchase(purchaseOne)

  Register.executePurchase(purchaseTwo)

  println(register.total)

  register.total = 0

  for(purchase <- Register.purchases){purchase()}

  println(register.total)

}