package com.edgar.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Created by edgar on 16-5-12.
 */
public class ChatClient {
  public static void main(String[] args) {
    InetSocketAddress address = new InetSocketAddress("localhost", 5000);
    try (SocketChannel socketChannel = SocketChannel.open(address)) {
      System.out.println("Connected	to	Chat	Server");
      String	message;
      Scanner scanner	=	new	Scanner(System.in);
      while	(true)	{
        System.out.println("Waiting	for	message	from	the	server...");
        System.out.println("Message:	"
                +	HelperMethods.receiveFixedLengthMessage(
                socketChannel));
        System.out.print(">	");
        message	=	scanner.nextLine();
        if	(message.equalsIgnoreCase("quit"))	{
          HelperMethods.sendFixedLengthMessage(
                  socketChannel,	"Client	terminating");
          break;
        }
        HelperMethods.sendFixedLengthMessage(socketChannel,	message);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
