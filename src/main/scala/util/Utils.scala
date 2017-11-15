package util

import scala.util.Try

object Utils {

  def factors(num: Int) = {
    (1 to num).filter { divisor =>
      num % divisor == 0
    }
  }

  def stringToMaybeInt(s: String): Option[Int] = Try(s.toInt).toOption
}
