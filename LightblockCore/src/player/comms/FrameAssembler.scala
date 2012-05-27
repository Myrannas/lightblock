package player.comms

import org.jboss.netty.handler.codec.frame.FrameDecoder
import org.jboss.netty.channel.{Channel, ChannelHandlerContext}
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.handler.codec.replay.{VoidEnum, ReplayingDecoder}

/**
 * User: michael
 * Date: 5/27/12
 * Time: 10:21 PM
 */

class FrameAssembler extends ReplayingDecoder[VoidEnum] {
  def decode(context: ChannelHandlerContext, channel: Channel, buffer: ChannelBuffer, n: VoidEnum) = {
    buffer.markReaderIndex()
    val messageStart = buffer.readerIndex()

    buffer.readByte() match {
        //Login packet
      case MessageTypes.Login =>
        buffer.readBytes(4)
        readString(buffer)
        readString(buffer)
        buffer.readBytes(11)
      case MessageTypes.Handshake =>
        readString(buffer)
      case i : Byte =>
        throw new Exception("Unidentified starting byte")
    }

    val readLength = buffer.readerIndex() - messageStart
    buffer.resetReaderIndex()
    buffer.readSlice(readLength)
  }

  def readString(buffer: ChannelBuffer) = {
    val l = buffer.readShort()
    buffer.readBytes(l*2)
  }
}
