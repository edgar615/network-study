package com.edgar.network.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by edgar on 16-5-12.
 */
public class HelperMethods {

  public	static	void	sendFixedLengthMessage(
          SocketChannel socketChannel,	String	message) {
    try {
      ByteBuffer byteBuffer = ByteBuffer.allocate(64);
      byteBuffer.put(message.getBytes());
      byteBuffer.flip();
      while (byteBuffer.hasRemaining()) {
        socketChannel.write(byteBuffer);
      }
      System.out.println("Sent:	"	+	message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public	static	String	receiveFixedLengthMessage
          (SocketChannel	socketChannel) {
    String message = "";
    try {
      ByteBuffer byteBuffer = ByteBuffer.allocate(64);
      socketChannel.read(byteBuffer);
      byteBuffer.flip();
      while (byteBuffer.hasRemaining()) {
        message += (char) byteBuffer.get();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return message;
  }
}
