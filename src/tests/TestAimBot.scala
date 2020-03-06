package tests

import org.scalatest.FunSuite

class TestAimBot extends FunSuite {

  val EPSILON: Double = 0.05

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }


  test("Genetic Algorithms Hits a Moving target") {

  }

}
