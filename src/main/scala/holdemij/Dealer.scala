package holdemij

object Dealer {

  lazy val deck = new Deck

  def dealHands(n: Int): Seq[Cards] = {
    for (_ <- 1 to n) yield deck.draw(2)
  }

  def dealFlop(): Cards = {
    deck.draw(3)
  }

  def dealExtra(): Cards = {
    deck.discard(1)
    deck.draw(1)
  }
}
