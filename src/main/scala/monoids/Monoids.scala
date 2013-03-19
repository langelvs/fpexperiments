package monoids

/**
 * Created with IntelliJ IDEA.
 * User: langelvs
 * Date: 25/02/13
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.collection.immutable._
import scala.annotation.tailrec

import scalaz._
import scalaz.Scalaz._

object Monoids extends App {
  /*case class IntW(a: Int)

  implicit val intWMonoid = new Monoid[IntW] {
    def append(a: IntW, b: => IntW): IntW = {
      IntW(a.a*a.a + b.a*b.a)
    }
    def zero: IntW = IntW(0)
  }

  def addition[M : Monoid](a: M, b: M): M = {
    implicitly[Monoid[M]].append(a, b)
  }

  println(List(IntW(1), IntW(2), IntW(3)).concatenate)*/

  /*val f1 = future(1)
  val f2 = future(2)
  val f3 = future(3)

  val ls = Future.sequence(List(f1, f2, f3)).flatMap {
    list =>
      Future.sequence(list.map(i => future(2 * i)))
  }

  ls onSuccess {
    case list =>
      list foreach println
  }

  Await.ready(ls, 5 seconds)*/

  val t1 = System.currentTimeMillis
  //val re = range(0, 100000000).foldLeft(0)(b => a => if ( (a % 3 == 0) || (a % 5 == 0)) b + a else b)
  val re = (0 until 100000000).toStream.foldLeft(0) { case (b , a) => if ( (a % 3 == 0) || (a % 5 == 0)) b + a else b }
  val t2 = System.currentTimeMillis

  println(s"Result = $re in ${(t2 - t1) / 1000.0} seconds.")
}
