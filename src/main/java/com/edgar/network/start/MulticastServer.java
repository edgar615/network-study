package com.edgar.network.start;

import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by edgar on 16-5-5.
 */
public class MulticastServer {
  public static void main(String[] args) {
    System.out.println("Multicast		Time	Server");
    try {
      DatagramSocket serverSocket = new DatagramSocket();
      while (true) {
        String	dateText	=	new Date().toString();
        byte[]	buffer	=	new	byte[256];
        buffer	=	dateText.getBytes();
        InetAddress group = InetAddress.getByName("224.0.0.0");

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 8888);
        serverSocket.send(packet);
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    } catch (SocketException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
