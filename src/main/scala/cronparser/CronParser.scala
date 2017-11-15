package cronparser
import lib.CronMethods._
import util.OutputFormatter._

object CronParser extends App {
  val command = args.reverse.head
  val argsList = args.flatMap(_.split(" "))
  val cronValueArgs = argsList.reverse.tail.reverse.zipWithIndex

  val cronEvaluator = cronValueArgs.flatMap { case (v, i) =>
    val maybeCronMeta = indexMap.get(i)
    for {
      cronMeta <- maybeCronMeta
    } yield cronExpressionEvaluation(v, cronMeta)
  }

  val largestField = cronEvaluator.map(c => c.fromToToList.mkString(" ")).map(_.length).max
  val validCron = cronEvaluator.map(_.validCron).forall(_ == true)

  if (validCron) {
    Header(largestField)
    cronEvaluator.foreach { res => successCronFormat(res, largestField)}
    System.out.format(s"%16s%31s", "command" + "|", s"$command")
    println("\n")
  } else {
    ErrorHeader
    val badCrons = cronEvaluator.filterNot(_.validCron)
    val largestBadCrons = badCrons.map(c => c.fromToToList.mkString(" ")).map(_.length).max
    Header(largestBadCrons)
    badCrons.foreach { res =>
      errorCronFormat(res, largestBadCrons)
    }
  }
}
