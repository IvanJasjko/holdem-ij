package holdemij

import CardChecker._

object Game {

  def processGame(hands: Seq[Cards], flop: Cards, turn: Cards, river: Cards): Unit = {
    val games = hands.map { hand => hand ++ flop ++ turn ++ river }
      .zipWithIndex

    val results = games.map {
      case (game, player) =>
        val scoreCheck = getScore(game)
        Result(s"Player ${player + 1}",
          game.take(2),
          scoreCheck.combination,
          scoreCheck.score,
          scoreCheck.combo_score,
          scoreCheck.kicker_score
        )
    }

    results.foreach {
      result =>
        println(s"${result.player}'s hand: ${decodeCards(result.hand)}  [${result.combination}]")
    }

    println(s"\nTable: ${decodeCards(flop)} ${decodeCards(turn)} ${decodeCards(river)}")

    val topScore = results.maxBy(player => (player.score, player.combo_score, player.kicker_score))
    val winners = results
      .filter(result =>
        result.score == topScore.score &&
          result.combo_score == topScore.combo_score &&
          result.kicker_score == topScore.kicker_score)

    if (winners.size == 1) {
      println(s"\nWinner: ${winners.head.player} with a ${winners.head.combination}!")
    }
    else {
      println("\nTie between:")
      winners.foreach {
        winner => println(s"${winner.player} with a ${winner.combination}!")
      }
    }
  }

  private def decodeCards(cards: Cards): String = {
    cards.map(card => s"(${card.value} of ${card.suit})").mkString(" ")
  }

}
