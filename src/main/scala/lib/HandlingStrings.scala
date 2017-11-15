package lib
import model.FromTo
import util.Utils._
import scala.util.Try

object HandlingStrings {

  def stringOrInt(cronValue: String, cronMeta: CronMeta) = {
    cronMeta.stringMap.get(cronValue) match {
      case Some(m) => Some(m)
      case None => Try(cronValue.toInt).toOption
    }
  }

  def hyphenHandler(cronValue: String, cronMeta: CronMeta) = {
    val rangeList = cronValue.split("-")
    val rangeListSize = rangeList.size - 1
    val maybeFrom = stringOrInt(rangeList(0), cronMeta)
    val maybeTo = stringOrInt(rangeList(rangeListSize), cronMeta)
    FromTo(maybeFrom, maybeTo)
  }

  def commaHandler(cronValue: String, cronMeta: CronMeta) = {
    val splitList = cronValue.split(",").toList

    splitList.map { s =>
      if (s.contains("-")) {
        hyphenHandler(s, cronMeta)
      } else {
        val maybeFrom = stringOrInt(s, cronMeta)
        FromTo(maybeFrom, maybeFrom)
      }
    }
  }

  def divisorHandler(cronValue: String, cronMeta: CronMeta) = {
    val cron = cronValue.split("/").toList.reverse.head
    val maybeIntValue = stringOrInt(cron, cronMeta)
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

}
