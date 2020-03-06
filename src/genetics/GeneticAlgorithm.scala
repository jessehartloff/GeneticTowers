package genetics

object GeneticAlgorithm {

  /**
    * Uses a genetic algorithm to optimize a generic problem
    *
    * @param incubator Determines how instances of type T are created from a List of Doubles (genes)
    * @param costFunction Determines the cost for a given instance of T
    * @param numberOfGenes The size of the List expected by the incubator
    * @tparam T The type to be optimized
    * @return An instance of T with minimal cost
    */
  def geneticAlgorithm[T](incubator: List[Double] => T, costFunction: T => Double, numberOfGenes: Int): T = {
    incubator(null)
  }

}
