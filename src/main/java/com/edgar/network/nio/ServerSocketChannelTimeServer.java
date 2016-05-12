package com.edgar.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * Created by edgar on 16-5-12.
 */
public class ServerSocketChannelTimeServer {

  public static void main(String[] args) {
    System.out.println("Time	Server	started");
    try {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.socket().bind(new InetSocketAddress(5000));

      while (true) {
        System.out.println("Waiting	for	request...");
        SocketChannel channel = serverSocketChannel.accept();
        if (channel != null) {
          String dateAndTimeMessage = "Date:	"
                  + new Date(System.currentTimeMillis());
          ByteBuffer byteBuffer = ByteBuffer.allocate(64);
          byteBuffer.put(dateAndTimeMessage.getBytes());

          //We	need	to	invoke	the	 flip 	method	so	that	we	can	use	it	with	the	channel’s	write
//          operation.	This	has	the	effect	of	setting;	the	limit	is	set	to	the	current	position	and	the
//          position	to	zero
          //我们已经写满了缓冲区，现在我们必须准备将其清空。我们想把这个缓冲区传递给一个通道，以使内容能被全部写出。
          // 但如果通道现在在缓冲区上执行get()，那么它将从我们刚刚插入的有用数据之外取出未定义数据。如果我们将位置值重新设为0，通道就会从正确位置开始获取，但是它是怎样知道何时到达我们所插入数据末端的呢？这就是上界属性被引入的目的。
          // 上界属性指明了缓冲区有效内容的末端。我们需要将上界属性设置为当前位置，然后将位置重置为0。我们可以人工用下面的代码实现：

          byteBuffer.flip();
          while (byteBuffer.hasRemaining()) {
            channel.write(byteBuffer);
          }
          System.out.println("Sent:	"	+	dateAndTimeMessage);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
