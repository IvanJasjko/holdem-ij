package holdemij

import CardChecker._

object Game {

  def processGame(hands: Seq[Seq[Card]], flop: Seq[Card], turn: Seq[Card], river: Seq[Card]): Unit = {
    val games = hands.map { hand => hand ++ flop ++ turn ++ river }
      .zipWithIndex

    val results = games.map {
      case (game, player) =>
        (s"Player ${player + 1}", game.take(2), getScore(game).combination, getScore(game).score)
    }

    results.foreach { result => println(s"${result._1}'s hand: ${decodeCards(result._2)}  [${result._3}]") }
    println(s"Table: ${decodeCards(flop)} ${decodeCards(turn)} ${decodeCards(river)}")
    val winner = results.maxBy(_._4)
    println(s"Winner: ${winner._1} with a ${winner._3}!")
  }

  def decodeCards(cards: Seq[Card]): String = {
    cards.map(card => s"(${card.value} of ${card.suit})").mkString(" ")
  }

}
