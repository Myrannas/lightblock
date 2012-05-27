package player

import comms.{ClientHandshake, ClientLoginRequest}
import akka.actor.{ActorLogging, Actor}
import org.jboss.netty.buffer.ChannelBuffer

/**
 * User: michael
 * Date: 5/27/12
 * Time: 10:00 PM
 */

case class MessageException(e: Throwable)

class Player extends Actor with ActorLogging{
  val channel : ChannelBuffer = null



  protected def receive = {
    case ClientHandshake(username, hostname) =>
      log.info("Received handshake from {}", username)
    case MessageException(e)=>
      log.error("Exception encountered : {}",e.getMessage)
      throw e
    case ClientLoginRequest(protocol,name) =>
    case _ =>
  }
}
