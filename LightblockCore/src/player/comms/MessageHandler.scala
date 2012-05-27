package player.comms

import akka.actor.ActorRef
import org.jboss.netty.channel._
import player.MessageException


/**
 * User: michael
 * Date: 5/28/12
 * Time: 12:00 AM
 */

class MessageHandler(actor: ActorRef) extends SimpleChannelHandler {
  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
    actor ! e.getMessage
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, e: ExceptionEvent) {
    actor ! MessageException(e.getCause)
  }
}
