package holdemij

object Main {

  def main(args: Array[String]): Unit = {
    val myDeck = new Deck
    myDeck.cards.foreach(println)

  }
}
