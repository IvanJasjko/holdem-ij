package holdemij

import CardChecker._

object Game {

  def processGame(hands: Seq[Cards], flop: Cards, turn: Cards, river: Cards): Unit = {
    val games = hands.map { hand => hand ++ flop ++ turn ++ river }
      .zipWithIndex

    val results = games.map {
      case (game, player) =>
        val scoreCheck = getScore(game)
        Result(s"Player ${player + 1}", game.take(2), scoreCheck.combination, scoreCheck.score)
    }

    results.foreach {
      result =>
        println(s"${result.player}'s hand: ${decodeCards(result.hand)}  [${result.combination}]")
    }

    println(s"\nTable: ${decodeCards(flop)} ${decodeCards(turn)} ${decodeCards(river)}")

    val winner = results.maxBy(_.score)
    println(s"\nWinner: ${winner.player} with a ${winner.combination}!")
  }

  private def decodeCards(cards: Seq[Card]): String = {
    cards.map(card => s"(${card.value} of ${card.suit})").mkString(" ")
  }

}
