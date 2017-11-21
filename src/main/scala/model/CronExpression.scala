package model

import lib.CronMeta

case class FromTo(from: Option[Int], to: Option[Int])

case class CronEvaluator(
  cronMeta: CronMeta,
  cronValue: String,
  cronEvaluation: List[FromTo]
) {

  def fromToToList: List[Int] = {
    cronEvaluation.flatMap { ft =>
      for {
        from <- ft.from
        to <- ft.to
      } yield {
        (from to to).toList
      }
    }.flatten
  }

  def validCron: Boolean = {
    cronEvaluation.map {
      case ft if ft.from.nonEmpty && ft.to.nonEmpty => true
      case _ => false
    }.forall(_ == true)
  }
}

