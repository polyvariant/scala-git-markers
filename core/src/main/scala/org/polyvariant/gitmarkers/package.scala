/*
 * Copyright 2022 Polyvariant
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
