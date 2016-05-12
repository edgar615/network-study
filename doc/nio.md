

Channel: This represents a stream of data between applications
Channel用于在字节缓冲区和位于通道另一侧的实体（通常是一个文件或套接字）之间有效地传输数据。

Buffer: This works with a channel to process data
Java NIO中的Buffer用于和NIO通道进行交互。 写：Buffer -> Channel 读：Channel -> Buffer

Selector: This is a technology that allows a single thread to handle multiple channels
Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。

A channel and a buffer are typically associated with each other. Data may be transferred from a channel to a buffer or from a buffer to a channel.
The buffer, as its name implies, is a temporary repository for information. The selector is useful in supporting application scalability,


使用Buffer读写数据的步骤： 1. Write data into the Buffer 2. Call buffer.flip() 3. Read data out of the Buffer 4. Call buffer.clear() or buffer.compact()

clear()方法会清空整个缓冲区。 compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。


Channel用于在字节缓冲区和位于通道另一侧的实体（通常是一个文件或套接字）之间有效地传输数据。

Java NIO的通道类似流，但又有些不同：

    既可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的。
    通道可以异步地读写。
    通道中的数据总是要先读到一个Buffer，或者总是要从一个Buffer中写入。

Channel的实现

这些是Java NIO中最重要的通道的实现：

    FileChannel
    DatagramChannel
    SocketChannel
    ServerSocketChannel

FileChannel 从文件中读写数据。

DatagramChannel 能通过UDP读写网络中的数据。

SocketChannel 能通过TCP读写网络中的数据。

ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。

NetworkChannel This	supports	a	network	socket

AsynchronousSocketChannel This	supports	asynchronous	streaming	sockets
