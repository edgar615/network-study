package com.edgar.network.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SimpleEchoServer {

  /**
   * @param args
   */
  public static void main(String[] args) {
    try(ServerSocket serverSocket = new ServerSocket(8080)) {
      System.out.println("Waiting for connection....");
      Socket client = serverSocket.accept();
      System.out.println("Connected to client");
      try (BufferedReader reader = new BufferedReader(
              new InputStreamReader(client.getInputStream()))) {
        PrintWriter out = new PrintWriter(
                client.getOutputStream(), true);
        String line;
        while ((line = reader.readLine()) != null) {
          System.out.println("Server: " + line);
          out.println(line);
        }
        reader.close();
        System.out.println("SSLServerSocket	Terminated");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
