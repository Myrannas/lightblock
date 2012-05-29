package player.comms

/**
 * User: michael
 * Date: 5/28/12
 * Time: 12:08 AM
 */

object MessageTypes {
  val Chunk = 0x33

  val MapAlloc = 0x32

  val PlayerPosition = 0x0B

  val PositionLook = 0x0D

  val Ping = 0xFE
  val Kick = 0xFF

  val KeepAlive = 0x00
  val Login = 0x01
  val Handshake = 0x02
}
