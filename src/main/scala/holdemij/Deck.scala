package holdemij

import scala.collection.mutable.ListBuffer
import scala.util.Random

class Deck {

  private val suits = Seq("Spades", "Hearts", "Clubs", "Diamonds")
  private val ranks = (for (i <- 2 to 10) yield i.toString) ++ Seq("J", "Q", "K", "A")

  private def makeCard(rank: String): Seq[Card] = {
    for (suit <- suits) yield Card(rank, suit)
  }

  private def makeDeck(): Seq[Card] = {
    val cardList = for (rank <- ranks) yield makeCard(rank)
    cardList.flatten
  }

  val cards: ListBuffer[Card] = Random.shuffle(makeDeck()).to[ListBuffer]

  def deal(n: Int): Seq[Card] = {
    val hand = cards.take(n)
    hand.foreach(cards.-=)
    hand
  }

}
