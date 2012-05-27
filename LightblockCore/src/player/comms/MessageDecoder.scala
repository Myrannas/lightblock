package player.comms

import org.jboss.netty.handler.codec.replay.ReplayingDecoder
import org.jboss.netty.channel.{Channel, ChannelHandlerContext}
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.handler.codec.frame.FrameDecoder
import java.nio.charset.Charset

/**
 * User: michael
 * Date: 5/27/12
 * Time: 11:48 PM
 */

class MessageDecoder extends FrameDecoder{
  val charset = Charset.forName("UTF-16")

  def decode(context: ChannelHandlerContext, channel: Channel, buffer: ChannelBuffer) = {
    buffer.readByte() match {
      case MessageTypes.Login =>
        val protocol = buffer.readInt()
        val name = readString(buffer)
        ClientLoginRequest(protocol, name)
      case MessageTypes.Handshake =>
        val handshakeParams = readString(buffer) split ";"
        ClientHandshake(handshakeParams(0),handshakeParams(1))
    }
  }

  def readString(buffer: ChannelBuffer) = {
    val l = buffer.readShort()
    val s = buffer.toString(buffer.readerIndex(),l,charset)
    buffer.readBytes(l*2)
    s
  }
}
