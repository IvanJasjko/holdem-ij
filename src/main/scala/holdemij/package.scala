package object holdemij {

  case class Card(value: String, suit: String, score: Int)
  case class Result(combination: String, score: Int)

}
