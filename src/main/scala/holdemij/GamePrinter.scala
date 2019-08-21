package holdemij

object GamePrinter {
  def printGame(dealer: Dealer) = {
    println(s"1st Hand: ${dealer.playerHand}")
    println(s"2nd Hand: ${dealer.botHand}")
    println(s"FLOP: ${dealer.flop}")
    println(s"TURN: ${dealer.turn}")
    println(s"RIVER: ${dealer.river}")
  }
}
