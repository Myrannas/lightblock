lightblock
==========

Light Block is a highly scalable Minecraft server, written in Scala.

The limit of players on a modern Minecraft server is set to 250, this server is being written to determine if it is possible
to extend this, by using a more distributed architecture.

This server is designed with concurrency in mind, and can be split over several cores or event distinct computers.

Libraries used:
* Scala
* Akka
* Netty
* ScalaQuery