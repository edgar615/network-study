package com.edgar.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Created by edgar on 16-5-12.
 */
public class ChatServer {
  public static void main(String[] args) {
    try {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.socket().bind(new InetSocketAddress(5000));

      boolean running = true;
      while (running) {
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("Connected	to	Client");
        String message;
        Scanner scanner = new Scanner(System.in);
        while (true) {
          System.out.print(">	");
          message = scanner.nextLine();
          if (message.equalsIgnoreCase("quit")) {
            HelperMethods.sendFixedLengthMessage(
                    socketChannel, "Server	terminating");
            running = false;
            break;
          } else {
            HelperMethods.sendFixedLengthMessage(
                    socketChannel, message);
            System.out.println(
                    "Waiting	for	message	from	client...");
            System.out.println("Message:	" + HelperMethods
                    .receiveFixedLengthMessage(socketChannel));
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
