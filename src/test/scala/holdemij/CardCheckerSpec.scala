package holdemij

import org.scalatest.{FunSuite, Matchers}

class CardCheckerSpec extends FunSuite with Matchers {

  test("High Card") {
    val testCombination1 = Seq(
      Card("9", "Diamonds", 8),
      Card("10", "Spades", 9),
      Card("4", "Clubs", 3),
      Card("K", "Diamonds", 12),
      Card("7", "Hearts", 6),
      Card("A", "Diamonds", 13),
      Card("2", "Diamonds", 1))
    CardChecker.getScore(testCombination1) shouldBe Outcome("High Card", 0, 48)
  }

  test("Test flush") {
    val testCombination = Seq(
      Card("2", "Spades", 1),
      Card("3", "Spades", 2),
      Card("J", "Spades", 10),
      Card("7", "Clubs", 6),
      Card("4", "Clubs", 3),
      Card("7", "Spades", 6),
      Card("A", "Spades", 13))
    CardChecker.getScore(testCombination) shouldBe Outcome("Flush", 500, 32)
  }

  test("Test pair") {
    val testCombination = Seq(
      Card("2", "Spades", 1),
      Card("3", "Spades", 2),
      Card("J", "Spades", 10),
      Card("7", "Clubs", 6),
      Card("4", "Clubs", 3),
      Card("7", "Spades", 6),
      Card("A", "Clubs", 13))
    CardChecker.getScore(testCombination) shouldBe Outcome("Pair", 100, 12, 29)
  }

  test("Test 3 of a kind") {
    val testCombination1 = Seq(
      Card("J", "Diamonds", 10),
      Card("J", "Clubs", 10),
      Card("J", "Spades", 10),
      Card("2", "Clubs", 1),
      Card("4", "Diamonds", 3),
      Card("9", "Spades", 8),
      Card("A", "Hearts", 13))
    CardChecker.getScore(testCombination1) shouldBe Outcome("Three of a Kind", 300, 30, 25)
  }

  test("Test 4 of a kind") {
    val testCombination = Seq(
      Card("J", "Diamonds", 10),
      Card("J", "Clubs", 10),
      Card("J", "Spades", 10),
      Card("A", "Clubs", 13),
      Card("A", "Diamonds", 13),
      Card("A", "Spades", 13),
      Card("A", "Hearts", 13))
    CardChecker.getScore(testCombination) shouldBe Outcome("Four of a Kind", 700, 52, 30)
  }

  test("Test full house") {
    val testCombination1 = Seq(
      Card("J", "Diamonds", 10),
      Card("J", "Clubs", 10),
      Card("J", "Spades", 10),
      Card("K", "Clubs", 12),
      Card("A", "Diamonds", 13),
      Card("A", "Spades", 13),
      Card("A", "Hearts", 13))
    CardChecker.getScore(testCombination1) shouldBe Outcome("Full House", 600, 13, 10)

    val testCombination2 = Seq(
      Card("J", "Diamonds", 10),
      Card("J", "Clubs", 10),
      Card("J", "Spades", 10),
      Card("K", "Clubs", 12),
      Card("K", "Diamonds", 12),
      Card("A", "Spades", 13),
      Card("A", "Hearts", 13))
    CardChecker.getScore(testCombination2) shouldBe Outcome("Full House", 600, 10, 13)
  }

  test("Test straight") {
    val testCombination1 = Seq(
      Card("2", "Diamonds", 1),
      Card("3", "Clubs", 2),
      Card("4", "Spades", 3),
      Card("5", "Clubs", 4),
      Card("6", "Diamonds", 5),
      Card("7", "Spades", 6),
      Card("8", "Hearts", 7))
    CardChecker.getScore(testCombination1) shouldBe Outcome("Straight",400, 25)

    val testCombination2 = Seq(
      Card("J", "Diamonds", 10),
      Card("J", "Clubs", 10),
      Card("2", "Spades", 1),
      Card("3", "Clubs", 2),
      Card("4", "Diamonds", 3),
      Card("5", "Spades", 4),
      Card("6", "Hearts", 5))
    CardChecker.getScore(testCombination2) shouldBe Outcome("Straight",400, 15)

    val testCombination3 = Seq(
      Card("J", "Diamonds", 10),
      Card("2", "Clubs", 1),
      Card("3", "Spades", 2),
      Card("4", "Clubs", 3),
      Card("5", "Diamonds", 4),
      Card("6", "Spades", 5),
      Card("K", "Hearts", 12))
    CardChecker.getScore(testCombination3) shouldBe Outcome("Straight",400, 15)

    val testCombination4 = Seq(
      Card("J", "Diamonds", 10),
      Card("Q", "Clubs", 11),
      Card("3", "Spades", 2),
      Card("4", "Clubs", 3),
      Card("5", "Diamonds", 4),
      Card("6", "Spades", 5),
      Card("7", "Hearts", 6))
    CardChecker.getScore(testCombination4) shouldBe Outcome("Straight",400, 20)
  }

  test("Test straight flush") {
    val testCombination1 = Seq(
      Card("4", "Diamonds", 3),
      Card("5", "Diamonds", 4),
      Card("7", "Diamonds", 6),
      Card("6", "Diamonds", 5),
      Card("8", "Diamonds", 7),
      Card("2", "Spades", 1),
      Card("9", "Spades", 8))
    CardChecker.getScore(testCombination1) shouldBe Outcome("Straight Flush", 800, 30)
  }

  test("Test royal flush") {
    val testCombination1 = Seq(
      Card("9", "Diamonds", 8),
      Card("10", "Diamonds", 9),
      Card("J", "Diamonds", 10),
      Card("K", "Diamonds", 12),
      Card("Q", "Diamonds", 11),
      Card("A", "Diamonds", 13),
      Card("2", "Diamonds", 1))
    CardChecker.getScore(testCombination1) shouldBe Outcome("Royal Flush", 900)
  }
}
