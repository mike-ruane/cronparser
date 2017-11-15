package util
import model.CronEvaluator

object OutputFormatter {

  def Header(rowLength: Int) = {
    System.out.format(s"%16s%${rowLength + 2}s", "---------------|", "-"*(rowLength + 2))
    println("\n")
    System.out.format(s"%16s%${rowLength + 2}s", "cron expression|", "cron value")
    println("\n")
    System.out.format(s"%16s%${rowLength + 2}s", "---------------|", "-"*(rowLength + 2))
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

  def successCronFormat(cronEvaluator: CronEvaluator, rowLength: Int) = {
    System.out.format(s"%16s%${rowLength + 2}s", cronEvaluator.cronMeta.position + "|", cronEvaluator.fromToToList.mkString(" "))
      println("\n")
  }

  def errorCronFormat(cronEvaluator: CronEvaluator, rowLength: Int) = {
    System.out.format(s"%16s%${rowLength + 2}s", cronEvaluator.cronMeta.position + "|", cronEvaluator.cronValue)
      println("\n")
  }

}
