package holdemij

import scala.annotation.tailrec

object CardChecker {

  def getScore(combination: Cards): Outcome = {
    //First 2 cards are always players hand when the combination is made
    val hand = combination.take(2)
    combination match {
      case _ if isRoyalFlush(combination) => Outcome("Royal Flush", 900)
      case _ if isStraightFlush(combination)._1 => Outcome("Straight Flush", 800, isStraightFlush(combination)._2)
      case _ if isFourOfAKind(combination)._1 => Outcome("Four of a Kind", 700, isFourOfAKind(combination)._2, isFourOfAKind(combination)._3)
      case _ if isFullHouse(combination)._1 => Outcome("Full House", 600, isFullHouse(combination)._2, isFullHouse(combination)._3)
      case _ if isFlush(combination)._1 => Outcome("Flush", 500, isFlush(combination)._2)
      case _ if isStraight(combination)._1 => Outcome("Straight", 400, isStraight(combination)._2)
      case _ if isThreeOfAKind(combination)._1 => Outcome("Three of a Kind", 300, isThreeOfAKind(combination)._2, isThreeOfAKind(combination)._3)
      case _ if isTwoPairs(combination)._1 => Outcome("Two Pairs", 200, isTwoPairs(combination)._2, isTwoPairs(combination)._3)
      case _ if isPair(combination)._1 => Outcome("Pair", 100, isPair(combination)._2, isPair(combination)._3)
      case _ => Outcome("High Card", 0, combination.map(_.score).sum, 0)
    }
  }

  @tailrec
  private def isRoyalFlush(combination: Cards): Boolean = {
    val sortedCards = combination.map(_.score).sorted
    if (sortedCards.length < 5)
      false
    else if (isStraightFlush(combination.take(5))._1 && combination.take(5).head.score == 9)
      true
    else
      isRoyalFlush(combination.sortBy(_.score).tail)
  }

  @tailrec
  private def isStraightFlush(combination: Cards): (Boolean, Int) = {
    val sortedCards = combination.map(_.score).sorted
    if (sortedCards.length < 5)
      (false, 0)
    else if (sortedCards.takeRight(5).sliding(2).forall(pair => pair.head == pair(1) - 1) && isFlush(combination.take(5))._1)
      (true, sortedCards.takeRight(5).sum)
    else
      isStraightFlush(combination.sortBy(_.score).dropRight(1))
  }

  private def isFourOfAKind(combination: Cards): (Boolean, Int, Int) = {
    val quad = combination.groupBy(_.score).filter { case (_, cardz) => cardz.length == 4 }
    if (quad.size == 1)
      return (true, quad.head._2.map(_.score).sum, spareCardScore(combination, quad.head._2))
    (false, 0, 0)
  }

  private def isFullHouse(combination: Cards): (Boolean, Int, Int) = {
    val triples = combination.groupBy(_.score).filter { case (_, cardz) => cardz.length == 3 }
    val pairs = combination.groupBy(_.score).filter { case (_, cardz) => cardz.length >= 2 }

    if (triples.nonEmpty && pairs.size > triples.size || (triples.size == 2 && pairs.size == 2)) {
      val bestTripleScore = triples.toSeq.maxBy(_._1)._1
      val bestPairScore = (pairs.toSeq.map(_._1) diff List(bestTripleScore)).max
      return (true, bestTripleScore, bestPairScore)
    }
    (false, 0, 0)
  }

  private def isFlush(combination: Cards): (Boolean, Int) = {
    val flush = combination.groupBy(_.suit).filter { case (_, cardz) => cardz.length >= 5 }
    if (flush.size == 1)
      return (true, flush.toSeq.head._2.map(_.score).sum)
    (false, 0)
  }

  @tailrec
  private def isStraight(combination: Cards): (Boolean, Int) = {
    val sortedCards = combination.map(_.score).sorted
    if (sortedCards.length < 5)
      (false, 0)
    else if (sortedCards.takeRight(5).sliding(2).forall(pair => pair.head == pair(1) - 1))
      (true, sortedCards.takeRight(5).sum)
    else
      isStraight(combination.sortBy(_.score).dropRight(1))
  }

  private def isThreeOfAKind(combination: Cards): (Boolean, Int, Int) = {
    val triple = combination.groupBy(_.score).filter { case (_, cardz) => cardz.length == 3 }
    if (triple.size == 1)
      return (true, triple.head._2.map(_.score).sum, spareCardScore(combination, triple.head._2))
    (false, 0, 0)
  }

  private def isTwoPairs(combination: Cards): (Boolean, Int, Int) = {
    val pairs = combination.groupBy(_.score).filter { case (_, cardz) => cardz.length == 2 }
    if (pairs.size >= 2) {
      val sortedPairs = pairs.toSeq.sortBy(_._1).take(2).flatMap(_._2)
      return (true, sortedPairs.map(_.score).sum, spareCardScore(combination, sortedPairs))
    }
    (false, 0, 0)
  }

  private def isPair(combination: Cards): (Boolean, Int, Int) = {
    val pairs = combination.groupBy(_.score).filter { case (_, cardz) => cardz.length == 2 }
    if (pairs.size == 1)
      return (true, pairs.head._2.map(_.score).sum, spareCardScore(combination, pairs.head._2))
    (false, 0, 0)
  }

  private def spareCardScore(all_cards: Cards, combo: Cards): Int = {
    all_cards.diff(combo).map(_.score).sum
  }
}
