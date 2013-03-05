package monads

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

import scalaz._
import scalaz.Scalaz._

object Monads extends App {

  implicit val futureMonad = new Monad[Future] {
    def point[A](a: => A): Future[A] =
      future(a)

    def bind[A, B](fa: Future[A])(f: A => Future[B]): Future[B] =
      fa.flatMap(f)
  }

  val f1: Future[Int] = future(1)
  val f2: Future[Int] = future(2)
  val f3: Future[Int] = future(3)

  val ls: Future[List[Int]] = List(f1, f2, f3).sequence.flatMap(
    _.map(i => future(2 * i)).sequence
  )

  ls onSuccess {
    case list =>
      list foreach println
  }

  println("Printed before anything else... or maybe interleaved.")

  Await.ready(ls, 5 seconds)
}
