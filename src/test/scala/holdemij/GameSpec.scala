package holdemij

import org.scalatest.{FunSuite, Matchers}

class GameSpec extends FunSuite with Matchers {

  test("High Card Tie") {

    val hand1 = Seq(Card("A", "Diamonds", 13), Card("K", "Clubs", 12))
    val hand2 = Seq(Card("A", "Spades", 13), Card("K", "Hearts", 12))
    val hands = Seq(hand1, hand2)

    val flop = Seq(Card("7", "Spades", 6), Card("3", "Hearts", 2), Card("10", "Diamonds", 9))
    val turn = Seq(Card("Q", "Spades", 11))
    val river = Seq(Card("4", "Spades", 3))
    Game.processGame(hands, flop, turn, river, debug = true).length shouldBe 2
  }

  test("High Card Tie on Table") {

    val hand1 = Seq(Card("2", "Diamonds", 1), Card("3", "Clubs", 2))
    val hand2 = Seq(Card("2", "Spades", 1), Card("4", "Hearts", 3))
    val hands = Seq(hand1, hand2)

    val flop = Seq(Card("7", "Spades", 6), Card("6", "Hearts", 5), Card("10", "Diamonds", 9))
    val turn = Seq(Card("Q", "Spades", 11))
    val river = Seq(Card("5", "Spades", 4))
    Game.processGame(hands, flop, turn, river, debug = true).length shouldBe 2
  }

  test("High Card Winner") {

    val hand1 = Seq(Card("A", "Diamonds", 13), Card("J", "Clubs", 10))
    val hand2 = Seq(Card("A", "Spades", 13), Card("K", "Hearts", 12))
    val hands = Seq(hand1, hand2)

    val flop = Seq(Card("7", "Spades", 6), Card("3", "Hearts", 2), Card("10", "Diamonds", 9))
    val turn = Seq(Card("Q", "Spades", 11))
    val river = Seq(Card("4", "Spades", 3))
    Game.processGame(hands, flop, turn, river, debug = true).length shouldBe 1
    Game.processGame(hands, flop, turn, river, debug = true).head.player shouldBe "Player 2"
  }

  test("Kicker for 3 of a kind") {

    val hand1 = Seq(Card("A", "Diamonds", 13), Card("J", "Hearts", 10))
    val hand2 = Seq(Card("A", "Spades", 13), Card("K", "Hearts", 12))
    val hands = Seq(hand1, hand2)

    val flop = Seq(Card("7", "Spades", 6), Card("3", "Hearts", 2), Card("10", "Diamonds", 9))
    val turn = Seq(Card("A", "Clubs", 13))
    val river = Seq(Card("A", "Hearts", 13))
    Game.processGame(hands, flop, turn, river, debug = true).length shouldBe 1
    Game.processGame(hands, flop, turn, river, debug = true).head.player shouldBe "Player 2"
  }

  test("Stronger Full House") {

    val hand1 = Seq(Card("A", "Diamonds", 13), Card("J", "Hearts", 10))
    val hand2 = Seq(Card("A", "Spades", 13), Card("K", "Hearts", 12))
    val hands = Seq(hand1, hand2)

    val flop = Seq(Card("J", "Spades", 10), Card("J", "Diamonds", 10), Card("K", "Diamonds", 12))
    val turn = Seq(Card("A", "Clubs", 13))
    val river = Seq(Card("A", "Hearts", 13))
    Game.processGame(hands, flop, turn, river, debug = true).length shouldBe 1
    Game.processGame(hands, flop, turn, river, debug = true).head.player shouldBe "Player 2"
  }

  test("3 of a kind with weak kickers") {

    val hand1 = Seq(Card("A", "Diamonds", 13), Card("3", "Hearts", 2))
    val hand2 = Seq(Card("A", "Spades", 13), Card("2", "Hearts", 1))
    val hands = Seq(hand1, hand2)

    val flop = Seq(Card("8", "Spades", 7), Card("Q", "Diamonds", 11), Card("K", "Diamonds", 12))
    val turn = Seq(Card("A", "Clubs", 13))
    val river = Seq(Card("A", "Hearts", 13))
    Game.processGame(hands, flop, turn, river).length shouldBe 2
  }

  test("2 pairs") {

    val hand1 = Seq(Card("6", "Diamonds", 5), Card("4", "Hearts", 3))
    val hand2 = Seq(Card("8", "Spades", 7), Card("2", "Hearts", 1))
    val hands = Seq(hand1, hand2)

    val flop = Seq(Card("6", "Spades", 5), Card("4", "Diamonds", 3), Card("2", "Diamonds", 1))
    val turn = Seq(Card("8", "Clubs", 7))
    val river = Seq(Card("A", "Hearts", 13))
    Game.processGame(hands, flop, turn, river, debug = true).length shouldBe 1
    Game.processGame(hands, flop, turn, river, debug = true).head.player shouldBe "Player 2"
  }

  test("4 of a kind") {

    val hand1 = Seq(Card("A", "Diamonds", 13), Card("A", "Spades", 13))
    val hand2 = Seq(Card("J", "Clubs", 10), Card("K", "Hearts", 12))
    val hands = Seq(hand1, hand2)

    val flop = Seq(Card("J", "Spades", 10), Card("J", "Hearts", 10), Card("J", "Diamonds", 10))
    val turn = Seq(Card("A", "Clubs", 13))
    val river = Seq(Card("A", "Hearts", 13))
    Game.processGame(hands, flop, turn, river, debug = true).length shouldBe 1
    Game.processGame(hands, flop, turn, river, debug = true).head.player shouldBe "Player 1"
  }
}
