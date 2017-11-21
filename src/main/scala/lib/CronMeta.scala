package lib

trait CronMeta extends Product with Serializable {
  val position: String
  val range: Range
  val divisor: Boolean = false
}

case object Minute extends CronMeta {
  override val position: String = "minute"
  override val range: Range = 0 until 60
  override val divisor: Boolean = true
}

case object Hour extends CronMeta {
  override val position: String = "hour"
  override val range: Range = 0 until 60
  override val divisor: Boolean = true
}

case object DayOfMonth extends CronMeta {
  override val position: String = "dayOfMonth"
  override val range: Range = 1 to 31
}

case object Month extends CronMeta {
  override val position: String = "month"
  override val range: Range = 1 to 12
  override val divisor: Boolean = true

}

case object DayOfWeek extends CronMeta {
  override val position: String = "dayOfWeek"
  override val range: Range = 0 until 7
}
