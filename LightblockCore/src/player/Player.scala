package player

import akka.actor.{ActorLogging, Actor}
import comms._
import comms.PacketSerializer._
import org.jboss.netty.channel.Channel
import org.jboss.netty.buffer.{ChannelBuffers, ChannelBuffer}
import java.util.Random

/**
 * User: michael
 * Date: 5/27/12
 * Time: 10:00 PM
 */

case class MessageException(e: Throwable)

class Player extends Actor with ActorLogging{
  var channel: Channel = null
  val buffer = ChannelBuffers.dynamicBuffer()


  protected def receive = {
    case ClientHandshake(username, hostname) =>
      log.info("Received handshake from {}", username)
      val r = new Random
      val s = r.nextInt()

      buffer.writePacket(ServerHandshake("%016x".format(s)))
      channel.write(buffer.duplicate())
      buffer.clear()

    case ClientLoginRequest(protocol,username) =>
      log.info("Received login from {}", username)

      buffer.writePacket(ServerLoginRequest(0,"DEFAULT",0,0,0,256))
      channel.write(buffer.duplicate())
      buffer.clear()

      buffer.writePacket(ServerAllocateBlock(0,0,mode = true))
      channel.write(buffer.duplicate())
      buffer.clear()

      buffer.writePacket(ServerChunk(0,0,0,0,0,Array[Byte](),groundUp = false))
      channel.write(buffer.duplicate())
      buffer.clear()

      buffer.writePacket(ServerPositionLook(6.5, 67.2, 65.2, 7.5, 0, 0, onGround = false))
      channel.write(buffer.duplicate())
      buffer.clear()

    case MessageException(e) =>
      log.error("Exception encountered : {}",e.getMessage)
      throw e

    case buffer : Channel =>
      log.info("Initializing buffer")
      channel = buffer
    case ChannelClosed =>
      log.info("Channel closed")
      context.stop(self)
    case ClientKeepAlive() =>
    case _ =>

  }
}
