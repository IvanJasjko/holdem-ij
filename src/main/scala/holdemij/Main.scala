package holdemij

import Dealer._
import Game._

object Main {
  def main(args: Array[String]): Unit = {
    val playerNum = 2
    processGame(dealHands(playerNum), dealFlop(), dealExtra(), dealExtra())
  }
}
