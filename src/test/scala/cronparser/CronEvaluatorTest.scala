package cronparser

import lib.CronMethods._
import lib._
import org.scalatest._

class CronEvaluatorTest extends FlatSpec with Matchers {

  "The hyphen method '1-5'" should "return a list of 1 to 5" in {
    val hyphenCron = cronExpressionEvaluation("1-5", Minute)
    hyphenCron.fromToToList shouldEqual List(1, 2, 3, 4, 5)
  }

  "the comma method '1, 5'" should "return a list of 1 to 5" in {
    val commaCron = cronExpressionEvaluation("1, 5", Minute)
    commaCron.fromToToList shouldEqual List(1, 5)
  }

  "a mix of hyphens and commas '1, 5, 6-10, 12'" should "return a list of [1, 5, 6, 7, 8, 9, 10, 12]" in {
    val mixCron = cronExpressionEvaluation("1, 5, 6-10, 12", Minute)
    mixCron.fromToToList shouldEqual List(1, 5, 6, 7, 8, 9, 10, 12)
  }

  "'*/15'" should "return a list of [0, 15, 30, 45]" in {
    val fifteenCron = cronExpressionEvaluation("*/15", Minute)
    fifteenCron.fromToToList shouldEqual List(0, 15, 30, 45)
  }
}
