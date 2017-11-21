package cronparser
import lib.CronMethods._
import util.OutputFormatter._

object CronParser extends App {

  val argsList = args.flatMap(_.split(" "))
  val command = argsList.reverse.head
  val cronValueArgs = argsList.reverse.tail.reverse.zipWithIndex

  val cronEvaluator = cronValueArgs.flatMap { case (v, i) =>
    val maybeCronMeta = indexMap.get(i)
    for {
      cronMeta <- maybeCronMeta
    } yield cronExpressionEvaluation(v, cronMeta)
  }

  val validCron = cronEvaluator.map(_.validCron).forall(_ == true)

  if (validCron) {
    Header
    cronEvaluator.foreach { res => successCronFormat(res)}
    System.out.format(s"%16s%100s", "command" + "|", s"$command")
    println("\n")
  } else {
    ErrorHeader
    val badCrons = cronEvaluator.filterNot(_.validCron)
    badCrons.foreach { res =>
      errorCronFormat(res)
    }
  }
}
