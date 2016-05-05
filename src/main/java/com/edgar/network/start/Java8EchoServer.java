package com.edgar.network.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Java8EchoServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(8080)) {
      System.out.println("Waiting for connection....");
      Socket client = serverSocket.accept();
      System.out.println("Connected to client");
      try (BufferedReader reader = new BufferedReader(
              new InputStreamReader(client.getInputStream()))) {
        PrintWriter out = new PrintWriter(
                client.getOutputStream(), true);
        String line;
        //We	can	use	the	 Supplier 	interface	in	conjunction	with	a	 Stream 	object	to	perform	the same	operation.
        Supplier<String> supplier = () -> {
          try {
            return reader.readLine();
          } catch (IOException e) {
            return null;
          }
        };
        Stream.generate(supplier)
                .map(s -> {
                  System.out.println("Server: " + s);
                  out.println(s);
                  return s;
                }).allMatch(s -> s!= null);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
	}

}
