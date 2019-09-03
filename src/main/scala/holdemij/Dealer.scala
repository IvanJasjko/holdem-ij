package holdemij

object Dealer {
  val deck = new Deck

  def dealHands(n: Int): Seq[Seq[Card]] = {
    for (i <- 0 to n) yield (deck.draw(2))
  }

  def dealFlop(): Seq[Card] = {
    deck.draw(3)
  }

  def dealExtra(): Seq[Card] = {
    deck.discard(1)
    deck.draw(1)
  }
}
