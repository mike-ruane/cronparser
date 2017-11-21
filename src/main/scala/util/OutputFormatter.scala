package util
import model.CronEvaluator

object OutputFormatter {

  def Header = {
    System.out.format(s"%16s%100s", "---------------|", "-"*100)
    println("\n")
    System.out.format(s"%16s%100s", "cron expression|", "cron value")
    println("\n")
    System.out.format(s"%16s%100s", "---------------|", "-"*100)
    println("\n")
  }

  object ErrorHeader {
    System.out.format("%47s", "******************** ERROR ********************")
    println("\n")
    System.out.format("%47s", "malformed cron in the following expressions...")
    println("\n")
    System.out.format("%47s", "******************** ERROR ********************")
    println("\n")
  }

  def successCronFormat(cronEvaluator: CronEvaluator) = {
    System.out.format(s"%16s%100s", cronEvaluator.cronMeta.position + "|", cronEvaluator.fromToToList.mkString(" "))
      println("\n")
  }

  def errorCronFormat(cronEvaluator: CronEvaluator) = {
    println(cronEvaluator.cronMeta.position + ": " + cronEvaluator.cronValue)
      println("\n")
  }

}
