import akka.actor.{Props, ActorSystem}
import player.PlayerSupervisor

/**
 * User: michael
 * Date: 5/27/12
 * Time: 9:37 PM
 */
object Launcher {
  def main(args: Array[String]) {
    println("Starting lightblock")

    val actorSystem = ActorSystem.create()
    actorSystem.actorOf(Props[PlayerSupervisor],"players")
  }
}
