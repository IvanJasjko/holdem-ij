package object holdemij {

  case class Card(value: String, suit: String, score: Int)

  case class Result(player: String, hand: Cards, combination: String, score: Int, combo_score: Int = 0, kicker_score: Int = 0)

  case class Outcome(combination: String, score: Int, combo_score: Int = 0, kicker_score: Int = 0)

  type Cards = Seq[Card]

}
