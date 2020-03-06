package genetics.geometry


object SingleValue {

  def costFunction(number: Double): SingleValue => Double = {
    testNumber: SingleValue => {
      Math.abs(testNumber.value - number)
    }
  }

  def incubator(genes: List[Double]): SingleValue = {
    new SingleValue(genes.head)
  }

}

class SingleValue(val value: Double){}
