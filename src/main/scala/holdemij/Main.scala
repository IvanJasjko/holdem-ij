package holdemij

import GamePrinter.printGame

object Main {

  def main(args: Array[String]): Unit = {
    val myDeck = new Deck
    val dealer = new Dealer(myDeck.cards)
    printGame(dealer)
  }
}
