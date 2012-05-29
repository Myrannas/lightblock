package player.comms

import org.jboss.netty.handler.codec.frame.FrameDecoder
import org.jboss.netty.handler.codec.replay.{VoidEnum, ReplayingDecoder}
import org.jboss.netty.buffer.{ChannelBuffers, ChannelBuffer}
import PacketSerializer._
import org.jboss.netty.channel.{ChannelFuture, ChannelFutureListener, Channel, ChannelHandlerContext}

/**
 * User: michael
 * Date: 5/27/12
 * Time: 10:21 PM
 */

class FrameAssembler extends ReplayingDecoder[VoidEnum] {
  def decode(context: ChannelHandlerContext, channel: Channel, buffer: ChannelBuffer, n: VoidEnum) = {
    buffer.markReaderIndex()
    val messageStart = buffer.readerIndex()

    buffer.readUnsignedByte() match {
      case MessageTypes.KeepAlive=>
        buffer.readInt()
        //Login packet
      case MessageTypes.Login =>
        buffer.readBytes(4)
        readString(buffer)
        buffer.readShort()
        buffer.readInt()
        buffer.readInt()
        buffer.readByte()
        buffer.readByte()
        buffer.readByte()
      case MessageTypes.Handshake =>
        readString(buffer)
      case MessageTypes.PlayerPosition=>
        buffer.readBytes(33)
      case MessageTypes.PositionLook =>
        buffer.readBytes(41)
      case MessageTypes.Ping=>
        val kickBuffer = ChannelBuffers.dynamicBuffer()
        kickBuffer.writePacket(ServerKick("Lightblock powered server\u00A70\u00A71024"))
        context.getChannel.write(kickBuffer).addListener(new ChannelFutureListener {
          def operationComplete(p1: ChannelFuture) {
            context.getChannel.close()
          }
        })
      case i : Short =>
        var kickBuffer = ChannelBuffers.dynamicBuffer()
        kickBuffer.writePacket(ServerKick("Unknown packet id 0x%02x".format(i)))
        context.getChannel.write(kickBuffer).awaitUninterruptibly()
        context.getChannel.close()
        throw new Exception("Unknown packet id 0x%02x".format(i))
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
