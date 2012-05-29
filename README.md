lightblock
==========

Light Block is a highly scalable Minecraft server, written in Scala.

The maximum number of players on a modern Minecraft server is limited to 256. Due to protocol and architecture 
limitations, it would be impractical to attempt to host more than this. This server is being written to 
determine if it is possible to extend this, by using a more distributed architecture and several protocol 
enhancements.

This server is designed with concurrency in mind, and can be split over several cores or event distinct computers.

Libraries used:
* Scala
* Akka
* Netty
* ScalaQuery