package monoids

/**
 * Created with IntelliJ IDEA.
 * User: langelvs
 * Date: 25/02/13
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */

import scalaz._
import scalaz.Scalaz._

object Monoids extends App {
  case class IntW(a: Int)

  implicit val intWMonoid = new Monoid[IntW] {
    def append(a: IntW, b: => IntW): IntW = {
      IntW(a.a*a.a + b.a*b.a)
    }
    def zero: IntW = IntW(0)
  }

  def addition[M : Monoid](a: M, b: M): M = {
    implicitly[Monoid[M]].append(a, b)
  }

  println(List(IntW(1), IntW(2), IntW(3)).concatenate)
}
