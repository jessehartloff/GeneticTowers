package genetics.geometry


object Line {

  def costFunction(points: List[Point]): Line => Double = {
    null
  }

  def incubator(genes: List[Double]): Line = {
    null
  }

}


class Line(val slope: Double, val yIntercept: Double) {

  override def toString: String = {
    f"($slope%1.3f*x + $yIntercept%1.3f)"
  }

}
