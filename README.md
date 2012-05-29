lightblock
==========

Light Block is a highly scalable Minecraft server, written in Scala.

The maximum number of players on a modern Minecraft server is limited to 256. Due to protocol and architecture 
limitations, it would be impractical to attempt to host more than this. This server is being written to 
determine if it is possible to extend this, by using a more distributed architecture and several protocol 
enhancements.

This server is designed with concurrency in mind, and can be split over several cores or event distinct computers.

### Libraries Used
* Scala
* Akka
* Netty
* ScalaQuery


### Licence and Copyright
These sources are provided under the MIT licence.

Copyright (C) 2012 Michael Oates

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.