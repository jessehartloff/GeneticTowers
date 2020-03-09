package genetics.aimbot

object AimBot {

  val numberOfDimensions: Int = 1
  val projectileSpeed: Double = 7.0
  val delta: Double = 0.0001

  def costFunction(sourceLocation: PhysicsVector, targetLocation: PhysicsVector,
                   targetVelocity: PhysicsVector): PhysicsVector => Double = {

    projectileVelocity: PhysicsVector => {
      recursiveCost(
        sourceLocation, projectileVelocity,
        targetLocation, targetVelocity,
        Double.PositiveInfinity,
        1.0, 1.0,
        0, 10
      )
    }

  }

  def recursiveCost(sourceLocation: PhysicsVector, projectileVelocity: PhysicsVector,
                    targetLocation: PhysicsVector, targetVelocity: PhysicsVector,
                    minDistance: Double,
                    time: Double, dt: Double,
                    depth: Int, maxDepth: Int): Double = {

    if (depth == maxDepth || dt < delta) {
      minDistance
    } else {
      val distance = computeDistance(sourceLocation, projectileVelocity, targetLocation, targetVelocity, time)
      val distanceAhead = computeDistance(sourceLocation, projectileVelocity, targetLocation, targetVelocity, time + delta)

      if (distanceAhead < distance) {
        recursiveCost(
          sourceLocation, projectileVelocity,
          targetLocation, targetVelocity,
          distance,
          time + dt, dt,
          depth + 1, maxDepth
        )
      } else {
        recursiveCost(
          sourceLocation, projectileVelocity,
          targetLocation, targetVelocity,
          distance,
          time - (2.0 * dt / 3.0), dt / 3.0,
          depth + 1, maxDepth
        )
      }

    }
  }

  def computeDistance(sourceLocation: PhysicsVector, projectileVelocity: PhysicsVector,
                      targetLocation: PhysicsVector, targetVelocity: PhysicsVector,
                      time: Double): Double = {
    val newProjectileLocation = new PhysicsVector(
      sourceLocation.x + projectileVelocity.x * time,
      sourceLocation.y + projectileVelocity.y * time
    )
    val newTargetLocation = new PhysicsVector(
      targetLocation.x + targetVelocity.x * time,
      targetLocation.y + targetVelocity.y * time
    )
    newProjectileLocation.distance2d(newTargetLocation)
  }


  def incubator(genes: List[Double]): PhysicsVector = {
    val radians = genes.head * 2 * Math.PI
    new PhysicsVector(Math.cos(radians) * projectileSpeed, Math.sin(radians) * projectileSpeed)
  }

}
