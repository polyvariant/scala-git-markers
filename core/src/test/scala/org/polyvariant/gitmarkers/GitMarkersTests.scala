package org.polyvariant.gitmarkers

import weaver._

object GitMarkersTests extends FunSuite {

  val bar = "bar"

  val example =
// format: off

<<<<<<< HEAD
"foo"
=======
"baz"
>>>>>>> bar

// format: on

  test("example is recognized") {
    assert.eql(
      example.describe,
      s"""Found conflict:
         |- First ref: HEAD
         |  Value: foo
         |- Second ref: bar
         |  Value: baz""".stripMargin,
    )
  }
}
