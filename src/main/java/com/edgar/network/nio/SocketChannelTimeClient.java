package com.edgar.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by edgar on 16-5-12.
 */
public class SocketChannelTimeClient {
  public static void main(String[] args) {
    SocketAddress socketAddress = new InetSocketAddress("localhost", 5000);
    try (SocketChannel channel = SocketChannel.open(socketAddress)) {
      ByteBuffer byteBuffer = ByteBuffer.allocate(64);
      int byteRead = channel.read(byteBuffer);
      while (byteRead > 0) {
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
          System.out.print((char) byteBuffer.get());
        }
        System.out.println();
        byteRead = channel.read(byteBuffer);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
