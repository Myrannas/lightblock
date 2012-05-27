package player.comms

/**
 * User: michael
 * Date: 5/27/12
 * Time: 11:50 PM
 */
case class ClientHandshake(username: String, host: String)
case class ClientLoginRequest(protocolVersion: Int, username: String)
case class ServerLoginRequest(entity: Int, levelType: String, mode: Int, dimension: Int, difficulty: Byte, maxPlayers: Byte)
