package neuralnetwork

object NeuralNetwork{

  def costFunction(trainingData: Map[List[Double], String]): NeuralNetwork => Double = {
    null
  }

  def makeIncubator(numberOfInputs: Int, outputCategories: List[String]): List[Double] => NeuralNetwork = {
    null
  }

  def numberOfDimensions(numberOfInputNodes: Int, numberOfCategories: Int): Int = {
    0
  }
}


class NeuralNetwork(numberOfInputNodes: Int, outputCategories: List[String], genes: List[Double]) {

  def findCategory(inputs: List[Double]): String = {
    ""
  }

}
