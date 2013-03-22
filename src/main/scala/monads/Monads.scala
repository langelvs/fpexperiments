package monads

// Language features
////
import scala.language.{ higherKinds, postfixOps }
////

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.collection.immutable._

import scalaz._
import scalaz.std.list._
import scalaz.syntax.monad._

object Monads extends App {

  implicit val futureMonad = new Monad[Future] {
    override def point[A](a: => A): Future[A] =
      future(a)

    override def bind[A, B](fa: Future[A])(f: A => Future[B]): Future[B] =
      fa.flatMap(f)
  }
  
  def sequence[T, M[+_], M_ <: Monad[M]](l: List[M[T]])(implicit ev: Monad[M]): M[List[T]] =
    ev.sequence(l)

  def sequence2[T, M[+_]](l: List[M[T]])(implicit ev: Monad[M]): M[List[T]] =
    ev.sequence(l)

  def sequence3[T, M[+_] : Monad](l: List[M[T]]): M[List[T]] =
    implicitly[Monad[M]].sequence(l)

  val f1: Future[Int] = future(1)
  val f2: Future[Int] = future(2)
  val f3: Future[Int] = future(3)
  
  val ls: Future[List[Int]] = sequence2(List(f1, f2, f3)).flatMap(
    l => sequence2(l.map(i => future(2 * i)))
  )

  ls onSuccess {
    case list =>
      list foreach println
  }

  println("Printed before anything else... or maybe interleaved.")

  Await.ready(ls, 5 seconds)
}
