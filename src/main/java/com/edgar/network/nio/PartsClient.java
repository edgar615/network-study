package com.edgar.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class PartsClient {
  public PartsClient() {
    System.out.println("PartsClient	Started");
    SocketAddress address =
            new InetSocketAddress("127.0.0.1", 5000);
    try (SocketChannel socketChannel =
                 SocketChannel.open(address)) {
      System.out.println("Connected	to	Parts	Server");
      Scanner scanner = new Scanner(System.in);
      while (true) {
        System.out.print("Enter	part	name:	");
        String partName = scanner.nextLine();
        if (partName.equalsIgnoreCase("quit")) {
          HelperMethods.sendFixedLengthMessage(socketChannel, "quit");
          break;
        } else {
          HelperMethods.sendFixedLengthMessage(socketChannel, partName);
          System.out.println("The	price	is	"
                  + HelperMethods.receiveFixedLengthMessage(socketChannel));
        }
      }
      System.out.println("PartsClient	Terminated");
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new PartsClient();
  }
}