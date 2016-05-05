A server is installed on a machine with an IP address. It is possible for more than one
server to be running on a machine at any given time. When the operating system receives
a request for a service on a machine, it will also receive a port number. The port number
will identify the server to where the request should be forwarded. A server is, thus,
identified by its combination of IP address and port number.

Typically, a client will issue a request to a server. The server will receive the request and
send back a response. The nature of the request/response and the protocol used for
communication is dependent on the client/server. Sometimes a well-documented protocol,
such as the Hypertext Transfer Protocol (HTTP), is used. For simpler architectures, a
series of text messages are sent back and forth.

For the server to communicate with an application making a request, specialized software
is used to send and receive messages. This software is called a socket. One socket is found
on the client side, and the other socket is located on the server side. When they connect,
communication is possible. There are several different types of sockets. These include
datagram sockets; stream sockets, which frequently use TCP; and raw sockets, which
normally work at the IP level.