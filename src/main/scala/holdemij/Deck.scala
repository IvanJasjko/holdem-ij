package holdemij

import scala.collection.mutable.ListBuffer
import scala.util.Random

class Deck {

  private val suits = Seq("Spades", "Hearts", "Clubs", "Diamonds")
  private val ranks = (for (i <- 2 to 10) yield i.toString) ++ Seq("J", "Q", "K", "A")

  private def makeCard(rank: (String, Int)): Cards = {
    for (suit <- suits) yield Card(rank._1, suit, rank._2)
  }

  private def makeDeck(): Cards = {
    val cardList = for (rank <- ranks.zipWithIndex) yield makeCard(rank)
    cardList.flatten
  }

  val cards: ListBuffer[Card] = Random.shuffle(makeDeck()).to[ListBuffer]

  def draw(n: Int): Cards = {
    val hand = cards.take(n)
    hand.foreach(cards.-=)
    hand
  }

  def discard(n: Int): Unit = {
    1 to n foreach { _ => cards -= cards.head }
  }
}
