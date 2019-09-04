package holdemij

object CardChecker {

  def getScore(combination: Seq[Card]): Result = {
    Result("Pair", scala.util.Random.nextInt(40))
  }

}
