package holdemij

object Main {

  def main(args: Array[String]): Unit = {
    val myDeck = new Deck
    println(myDeck.cards)
    println(myDeck.deal(2))
    println(myDeck.cards)

  }
}
