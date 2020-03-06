package genetics.geometry

object Polynomial {

  def costFunction(points: List[Point]): Polynomial => Double = {
    polynomial: Polynomial => {
      points.foldRight(0.0)((point: Point, distance: Double) => distance + Math.pow(polynomial.evaluate(point.x) - point.y, 2.0)) / points.length
    }
  }

  def incubator(genes: List[Double]): Polynomial = {
    new Polynomial(genes)
  }

}


/**
  * Represents a polynomial ending with the constant coefficient
  *
  * Ex. new Polynomial(List(1.5, -2.2, 5)) represents 1.5*pow(x, 2) - 2.2*x + 5
  *
  */
class Polynomial(val coefficients: List[Double]) {

  def evaluate(x: Double): Double = {
    coefficients.foldLeft(0.0)((acc: Double, coefficient: Double) => acc * x + coefficient)
  }

  override def toString: String = {
    coefficients.toString
  }

}
