package player

import comms.{MessageHandler, MessageDecoder, FrameAssembler}
import java.util.concurrent.Executors
import org.jboss.netty.bootstrap.ServerBootstrap
import org.jboss.netty.channel.{Channels, ChannelPipelineFactory, ChannelFactory}
import java.net.InetSocketAddress
import org.jboss.netty.channel.socket.nio.{NioServerSocketChannelFactory, NioServerSocketChannel, NioClientSocketChannelFactory}
import akka.actor.{OneForOneStrategy, Props, ActorLogging, Actor}
import akka.actor.SupervisorStrategy.Stop


/**
 * User: michael
 * Date: 5/27/12
 * Time: 9:54 PM
 */

class ConnectionListener extends Actor with ActorLogging{
  var factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool())
  val server = new ServerBootstrap(factory)
  server.setPipelineFactory(new ChannelPipelineFactory {
    def getPipeline = {
      //Spin up a new player actor
      log.info("Spinning up new player actor")
      val ref = context.actorOf(Props[Player])

      val p = Channels.pipeline()
      p.addLast("FrameAssembler",new FrameAssembler())
      p.addLast("MessageDecoder",new MessageDecoder())
      p.addLast("MessageHandler", new MessageHandler(ref))

      p
    }
  })
  server.bind(new InetSocketAddress(25565))
  log.info("Listening on {}",25565)

  override val supervisorStrategy = OneForOneStrategy() {
    case _: Exception => Stop
  }

  protected def receive = {
    case _ =>
  }
}
