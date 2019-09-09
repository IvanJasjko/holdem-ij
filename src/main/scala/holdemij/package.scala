package object holdemij {

  case class Card(value: String, suit: String, score: Int)
  case class Result(player: String, hand: Cards, combination: String, score: Int)
  case class Outcome(combination: String, score: Int)

  type Cards = Seq[Card]

}
