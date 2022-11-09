package org.polyvariant

import language.dynamics

package object gitmarkers {

  object <<<<<<< extends Dynamic {

    def applyDynamic[A](name: String)(diff: DiffLR[A]): Diff[A] = Diff(
      refLeft = name,
      refRight = diff.rhs.refRight,
      lhs = diff.valueLeft,
      rhs = diff.rhs.valueRight,
    )

  }

  implicit class AnyDiffedOps[A](s: A) {
    def =======(rhs: DiffR[A]): DiffLR[A] = DiffLR(s, rhs)
    def >>>>>>>(ref: String): DiffR[A] = DiffR(ref, s)
  }

  case class DiffLR[A](valueLeft: A, rhs: DiffR[A])
  case class DiffR[A](refRight: String, valueRight: A)

  case class Diff[A](refLeft: String, refRight: String, lhs: A, rhs: A) {

    def describe: String =
      s"""Found conflict:
         |- First ref: $refLeft
         |  Value: $lhs
         |- Second ref: $refRight
         |  Value: $rhs""".stripMargin

  }

}
