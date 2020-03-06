package genetics.aimbot

object AimBot {

  val numberOfDimensions: Int = 1
  val projectileSpeed: Double = 7.0

  def costFunction(sourceLocation: PhysicsVector, targetLocation: PhysicsVector,
                   targetVelocity: PhysicsVector): PhysicsVector => Double = {

    projectileVelocity: PhysicsVector => {
      recursiveCost(
        sourceLocation, projectileVelocity,
        targetLocation, targetVelocity,
        Double.PositiveInfinity,
        1.0, 1.0,
        0, 20
      )
    }

  }

  def recursiveCost(sourceLocation: PhysicsVector, projectileVelocity: PhysicsVector,
                    targetLocation: PhysicsVector, targetVelocity: PhysicsVector,
                    minDistance: Double,
                    time: Double, dt: Double,
                    depth: Int, maxDepth: Int): Double = {

    if (depth == maxDepth) {
      minDistance
    } else {
      val newProjectileLocation = new PhysicsVector(
        sourceLocation.x + projectileVelocity.x * time,
        sourceLocation.y + projectileVelocity.y * time
      )
      val newTargetLocation = new PhysicsVector(
        targetLocation.x + targetVelocity.x * time,
        targetLocation.y + targetVelocity.y * time
      )

      val distance = newProjectileLocation.distance2d(newTargetLocation)
      val newMinDistance = Math.min(distance, minDistance)

      if (distance > minDistance) {
        // Base Case: Projectile is getting further away from the target
        newMinDistance
      } else {
        val lookAheadDistance = recursiveCost(
          sourceLocation, projectileVelocity,
          targetLocation, targetVelocity,
          newMinDistance,
          time + dt, dt,
          depth + 1, maxDepth
        )
        if (lookAheadDistance < newMinDistance) {
          // a better solution was found by looking forward in time
          lookAheadDistance
        } else {
          // look for a better solution by moving backwards in time at shorter intervals
          recursiveCost(
            sourceLocation, projectileVelocity,
            targetLocation, targetVelocity,
            newMinDistance,
            time - (2.0 * dt / 3.0), dt / 3.0,
            depth + 1, maxDepth
          )
        }
      }
    }
  }


  def incubator(genes: List[Double]): PhysicsVector = {
    val radians = genes.head * 2 * Math.PI
    new PhysicsVector(Math.cos(radians) * projectileSpeed, Math.sin(radians) * projectileSpeed)
  }

}
