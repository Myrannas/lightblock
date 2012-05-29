package player.comms

import org.jboss.netty.channel._
import player.MessageException
import akka.actor.{Actor, ActorRef}
import org.jboss.netty.buffer.{ChannelBufferFactory, ChannelBuffer, ChannelBuffers}


/**
 * User: michael
 * Date: 5/28/12
 * Time: 12:00 AM
 */

case class ChannelClosed()

class MessageHandler(actor: ActorRef) extends SimpleChannelHandler {

  override def channelConnected(ctx: ChannelHandlerContext, e: ChannelStateEvent) {
    actor ! ctx.getChannel
  }


  override def channelClosed(ctx: ChannelHandlerContext, e: ChannelStateEvent) {
    actor ! ChannelClosed
  }

  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
    actor ! e.getMessage
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, e: ExceptionEvent) {
    actor ! MessageException(e.getCause)
  }
}
