package com.edgar.network.start;

import sun.security.ssl.SSLServerSocketFactoryImpl;

import javax.net.ssl.SSLServerSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by edgar on 16-5-5.
 */
public class SSLServerSocket {
  //-Djavax.net.ssl.keyStore=/home/edgar/keystore.jceks -Djavax.net.ssl.keyStorePassword=secret
  public static void main(String[] args) {
    SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
    try {
      ServerSocket serverSocket = factory.createServerSocket(8088);
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
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
