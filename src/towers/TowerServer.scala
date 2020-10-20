package towers

import com.corundumstudio.socketio.listener.DataListener
import com.corundumstudio.socketio.{AckRequest, Configuration, SocketIOClient, SocketIOServer}
import genetics.GeneticAlgorithm
import genetics.aimbot.{AimBot, PhysicsVector}
import play.api.libs.json.{JsValue, Json}

class TowerServer() {

  val config: Configuration = new Configuration {
    setHostname("localhost")
    setPort(8080)
  }

  val server: SocketIOServer = new SocketIOServer(config)
  server.addEventListener("fire", classOf[String], new FireListener(this))
  server.start()

}

object TowerServer {
  def main(args: Array[String]): Unit = {
    new TowerServer()
  }
}


class FireListener(server: TowerServer) extends DataListener[String] {

  def jsonToVector(json: JsValue): PhysicsVector = {
    new PhysicsVector((json \ "x").as[Double], (json \ "y").as[Double])
  }

  override def onData(socket: SocketIOClient, message: String, ackRequest: AckRequest): Unit = {

    //     receive --> {"towerLocation": {"x": 15, "y": 3}, "player": {"location": {"x": 15, "y": 3}, "velocity": {"x": -1, "y": 1}}}

    val gameState = Json.parse(message)

    val sourceLocation = jsonToVector((gameState \ "towerLocation").get)
    val targetLocation = jsonToVector((gameState \ "player" \ "location").get)
    val targetVelocity = jsonToVector((gameState \ "player" \ "velocity").get)

    val projectileVelocity = GeneticAlgorithm.geneticAlgorithm(
      AimBot.incubator,
      AimBot.costFunction(sourceLocation, targetLocation, targetVelocity),
      AimBot.numberOfDimensions
    )

    //     send --> {"location": {"x": 15, "y": 3}, "velocity": {"x": -1, "y": 1}}

    val projectile = Map(
      "location" -> Json.toJson(Map("x" -> sourceLocation.x, "y" -> sourceLocation.y)),
      "velocity" -> Json.toJson(Map("x" -> projectileVelocity.x, "y" -> projectileVelocity.y))
    )

    val returnMessage = Json.stringify(Json.toJson(projectile))
    socket.sendEvent("fire", returnMessage)
  }

}