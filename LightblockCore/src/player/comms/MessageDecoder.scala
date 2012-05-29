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
  val charset = Charset.forName("UTF-16BE")

  def decode(context: ChannelHandlerContext, channel: Channel, buffer: ChannelBuffer) = {
    buffer.readUnsignedByte() match {
      case MessageTypes.KeepAlive=>
        buffer.readInt()
        ClientKeepAlive()
      case MessageTypes.Login =>
        val protocol = buffer.readInt()
        val name = readString(buffer)
        buffer.readShort()
        buffer.readInt()
        buffer.readInt()
        buffer.readByte()
        buffer.readByte()
        buffer.readByte()
        ClientLoginRequest(protocol, name)
      case MessageTypes.Handshake =>
        val handshakeParams = readString(buffer) split ";"
        ClientHandshake(handshakeParams(0),handshakeParams(1))
      case MessageTypes.PlayerPosition =>
        ClientPlayerPosition(
          buffer.readDouble(),
          buffer.readDouble(),
          buffer.readDouble(),
          buffer.readDouble(),
          buffer.readByte() == 1
        )
      case MessageTypes.PositionLook =>
        ClientPositionLook(buffer.readDouble(),
          buffer.readDouble(),
          buffer.readDouble(),
          buffer.readDouble(),
          buffer.readFloat(),
          buffer.readFloat(),
          buffer.readByte() == 1)
      case _ =>
        null
    }
  }

  def readString(buffer: ChannelBuffer) = {
    val l = buffer.readShort()
    val s = buffer.toString(buffer.readerIndex(),l*2,charset)
    buffer.readBytes(l*2)
    s
  }


}
