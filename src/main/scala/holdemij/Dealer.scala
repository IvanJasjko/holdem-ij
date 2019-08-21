package holdemij

class Dealer(cards: Seq[Card]) {

  val firstHand: Seq[Card] = cards.slice(0,2)
  val secondHand: Seq[Card] = cards.slice(2,4)
  val flop: Seq[Card] = cards.slice(5,8)
  val turn: Seq[Card] = cards.slice(9,10)
  val river: Seq[Card] = cards.slice(11,12)

  //TODO Implement card combination classifications
}
