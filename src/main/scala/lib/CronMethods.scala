package lib
import model._
import util.Utils._

object CronMethods {

  val indexMap = Map(0 -> Minute, 1 -> Hour, 2 -> DayOfMonth, 3 -> Month, 4 -> DayOfWeek)

  def hyphenHandler(cronValue: String, cronMeta: CronMeta) = {
    val rangeList = cronValue.split("-")
    val rangeListSize = rangeList.size - 1
    val maybeFrom = stringToMaybeInt(rangeList(0))
    val maybeTo = stringToMaybeInt(rangeList(rangeListSize))
    FromTo(maybeFrom, maybeTo)
  }

  def commaHandler(cronValue: String, cronMeta: CronMeta) = {
    val splitList = cronValue.split(",").toList

    splitList.map { s =>
      if (s.contains("-")) {
        hyphenHandler(s, cronMeta)
      } else {
        val maybeFrom = stringToMaybeInt(s)
        FromTo(maybeFrom, maybeFrom)
      }
    }
  }

 def divisorHandler(cronValue: String, cronMeta: CronMeta) = {
    val cron = cronValue.split("/").toList.reverse.head
   val maybeIntValue = stringToMaybeInt(cron)
   maybeIntValue match {
     case Some(i) =>
       val cronRange = cronMeta.range
       val possibleValues = factors(cronRange.max + 1)
       if (possibleValues.contains(i)){
         val stepBy = (0 until cronRange.max by i).toList
         stepBy.map(s => FromTo(Some(s), Some(s)))
       }
       else FromTo(None, None) :: Nil
     case None => FromTo(None, None) :: Nil
   }
  }

  def starHandler(cronValue: String, cronMeta: CronMeta) = {
    val cronRange = cronMeta.range
    val minValue = cronRange.min
    val maxValue = cronRange.max
    FromTo(Some(minValue), Some(maxValue)) :: Nil
  }

  def cronExpressionEvaluation(cronValue: String, cronMeta: CronMeta): CronEvaluator = {
    cronValue match {
      case cron if cron contains "," => CronEvaluator(cronMeta, cron, commaHandler(cronValue, cronMeta))
      case cron if cron contains "-" => CronEvaluator(cronMeta, cron, hyphenHandler(cronValue, cronMeta) :: Nil)
      case cron if (cron contains "/") && cronMeta.divisor => CronEvaluator(cronMeta, cron, divisorHandler(cronValue, cronMeta))
      case cron if cron contains "/" => CronEvaluator(cronMeta, cron, FromTo(None, None) :: Nil)
      case cron if cron contains "*" => CronEvaluator(cronMeta, cron, starHandler(cronValue, cronMeta))
      case cron if cron.length > 0 => CronEvaluator(cronMeta, cron, FromTo(stringToMaybeInt(cron), stringToMaybeInt(cron)) :: Nil)
      case _ => CronEvaluator(cronMeta, cronValue, FromTo(None, None) :: Nil)
    }
  }
}


