package player.comms

/**
 * User: michael
 * Date: 5/27/12
 * Time: 11:50 PM
 */
trait Packet

case class ClientHandshake(username: String, host: String) extends Packet
case class ServerHandshake(hash: String) extends Packet
case class ClientLoginRequest(protocolVersion: Int, username: String) extends Packet
case class ServerLoginRequest(entity: Int, levelType: String, mode: Int, dimension: Int, difficulty: Byte, maxPlayers: Short) extends Packet
case class ClientKeepAlive()
case class ServerKick(reason: String) extends Packet
case class ClientPositionLook(x : Double, y:Double, stance : Double, z : Double, yaw : Double, pitch: Double, onGround: Boolean) extends Packet
case class ServerPositionLook(x: Double, stance: Double, y: Double, z:Double, yaw:Float, pitch:Float, onGround:Boolean) extends Packet
case class ClientPlayerPosition(x: Double, y: Double, stance: Double, z: Double, onGround: Boolean) extends Packet
case class ServerAllocateBlock(x  : Int, y : Int, mode : Boolean) extends Packet
case class ServerChunk(x : Int, y : Int, primaryMap: Int, addMap : Int, size: Int, data: Array[Byte], groundUp : Boolean) extends Packet
class Vector3(val x : Double, val y : Double, val z : Double)