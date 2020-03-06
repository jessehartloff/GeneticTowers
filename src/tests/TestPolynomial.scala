package tests

import org.scalatest.FunSuite

class TestPolynomial extends FunSuite {

  val EPSILON: Double = 0.1

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }


  test("Genetic Algorithm Compute Polynomial Regression") {

  }


}
