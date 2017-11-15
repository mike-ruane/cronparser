package util

import scala.util.matching.Regex
object RegexUtils {
  import scala.language.implicitConversions

    class RichRegex(underlying: Regex) {
      def matches(s: String) = underlying.pattern.matcher(s).matches
    }
    implicit def regexToRichRegex(r: Regex): RichRegex = new RichRegex(r)

  val myRegex = """^\*?\/?[0-9](,?-?[0-9])+$""".r
  val myWordRegex = """^\*?\/?(?:[\s]|)(mon|tue|wed|thu|fri|sat|sun)(?=[\s]|$)""".r
  val myOtherWordRegex = """^\*?\/?(?:[\s]|)(mon|tue|wed|thu|fri|sat|sun)(?=[\s]|)""".r
  val test = """(,?-?(?:[\s]|)(mon|tue|wed|thu|fri|sat|sun)(?=[\s]|$)"""
  val joinedRegex =  """^\*?\/?(?:[\s]|)(mon|tue|wed|thu|fri|sat|sun)(?=[\s]|)(,?-?)(?:[\s]|)(mon|tue|wed|thu|fri|sat|sun)(?=[\s]|$)""".r

}
