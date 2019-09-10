package holdemij

import scala.annotation.tailrec

object CardChecker {

  def getScore(combination: Cards): Outcome = {
    //First 2 cards are always players hand when the combination is made
    val hand = combination.take(2)
    combination match {
      case _ if isRoyalFlush(combination) => Outcome("Royal Flush", 900)
      case _ if isStraightFlush(combination) => Outcome("Straight Flush", 800)
      case _ if isFourOfAKind(combination) => Outcome("Four of a Kind", 700)
      case _ if isFullHouse(combination) => Outcome("Full House", 600)
      case _ if isFlush(combination) => Outcome("Flush", 500)
      case _ if isStraight(combination) => Outcome("Straight", 400)
      case _ if isThreeOfAKind(combination) => Outcome("Three of a Kind", 300)
      case _ if isTwoPairs(combination) => Outcome("Two Pairs", 200)
      case _ if isPair(combination) => Outcome("Pair", 100)
      case _ => Outcome("High Card", hand.maxBy(_.score).score)
    }
  }
  @tailrec
  private def isRoyalFlush(combination: Cards): Boolean = {
    val sortedCards = combination.map(_.score).sorted
    if (sortedCards.length < 5)
      false
    else if (isStraightFlush(combination.take(5)) && combination.take(5).head.score == 9)
      true
    else
      isRoyalFlush(combination.sortBy(_.score).tail)
  }

  @tailrec
  private def isStraightFlush(combination: Cards): Boolean = {
    val sortedCards = combination.map(_.score).sorted
    if (sortedCards.length < 5)
      false
    else if (sortedCards.take(5).sliding(2).forall(pair => pair.head == pair(1) - 1) && isFlush(combination.take(5)))
      true
    else
      isStraightFlush(combination.sortBy(_.score).tail)
  }

  private def isFourOfAKind(combination: Cards): Boolean = {
    combination.groupBy(_.score).count { case (_, cardz) => cardz.length == 4 } == 1
  }

  private def isFullHouse(combination: Cards): Boolean = {
    val triples = combination.groupBy(_.score).count { case (_, cardz) => cardz.length == 3 }
    triples == 2 ||
      (triples == 1 &&
        combination.groupBy(_.score).count { case (_, cardz) => cardz.length == 2 } >= 1)
  }

  private def isFlush(combination: Cards): Boolean = {
    combination.groupBy(_.suit).count { case (_, cardz) => cardz.length >= 5 } == 1
  }

  @tailrec
  private def isStraight(combination: Cards): Boolean = {
    val sortedCards = combination.map(_.score).sorted
    if (sortedCards.length < 5)
      false
    else if (sortedCards.take(5).sliding(2).forall(pair => pair.head == pair(1) - 1))
      true
    else
      isStraight(combination.sortBy(_.score).tail)
  }

  private def isThreeOfAKind(combination: Cards): Boolean = {
    combination.groupBy(_.score).count { case (_, cardz) => cardz.length == 3 } == 1
  }

  private def isTwoPairs(combination: Cards): Boolean = {
    combination.groupBy(_.score).count { case (_, cardz) => cardz.length == 2 } > 1
  }

  private def isPair(combination: Cards): Boolean = {
    combination.groupBy(_.score).count { case (_, cardz) => cardz.length == 2 } == 1
  }
}
