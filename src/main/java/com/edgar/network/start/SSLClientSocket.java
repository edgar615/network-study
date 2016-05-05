package com.edgar.network.start;

import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by edgar on 16-5-5.
 */
public class SSLClientSocket {

  //-Djavax.net.ssl.trustStore=/home/edgar/keystore.jceks -Djavax.net.ssl.trustStorePassword=secret
  public static void main(String[] args) {
    SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
    System.out.println("SSLClientSocket	Started");
    try (Socket socket = new Socket("localhost", 8088)) {
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader reader = new BufferedReader(
              new InputStreamReader(socket.getInputStream()));
      System.out.println("Connected to server");
      Scanner scanner = new Scanner(System.in);
      while (true) {
        System.out.print("Enter text: ");
        String inputLine = scanner.nextLine();
        if ("quit".equalsIgnoreCase(inputLine)) {
          break;
        }
        out.println(inputLine);
        String response = reader.readLine();
        System.out.println("Server response: " + response);
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

