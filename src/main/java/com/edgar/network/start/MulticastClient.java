package com.edgar.network.start;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by edgar on 16-5-5.
 */
public class MulticastClient {

  public static void main(String[] args) {
    System.out.println("Multicast		Time	Client");
    try(MulticastSocket socket = new MulticastSocket(8888)) {
      InetAddress address = InetAddress.getByName("224.0.0.0");
      socket.joinGroup(address);
      System.out.println("Multicast		Group	Joined");

      byte[] buffer = new byte[256];
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

      for (int i = 0; i < 5; i ++) {
        socket.receive(packet);
        String received = new String(packet.getData());
        System.out.println(received);
      }
      socket.leaveGroup(address);
      System.out.println("Multicast		Time	Client	Terminated");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
