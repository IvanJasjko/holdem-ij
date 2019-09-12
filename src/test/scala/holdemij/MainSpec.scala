package holdemij

import org.scalatest._

class MainSpec extends FunSuite with Matchers {

  test("Check that the deck is of a right size") {
    val testDeck = new Deck
    testDeck.cards.length shouldBe 52
  }
}

