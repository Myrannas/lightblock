package player

import akka.actor.{Props, Actor}


/**
 * User: michael
 * Date: 5/27/12
 * Time: 9:56 PM
 */

case class InstantiatePlayer()

class PlayerSupervisor extends Actor{
  context.actorOf(Props[ConnectionListener],"listener")

  protected def receive = {
    case _ =>
  }
}
