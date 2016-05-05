package com.edgar.network.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Java8EchoClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Waiting for connection...");
			InetAddress inetAddress = InetAddress.getLocalHost();
			try (Socket socket = new Socket(inetAddress, 8080)) {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				System.out.println("Connected to server");
				Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text: ");
        Supplier<String> input = () -> scanner.next();
        Stream.generate(input)
                .map(s -> {
                  out.println(s);
                  System.out.println("Server	response:	"	+	s);
                  System.out.print("Enter	text:	");
                  return s;
                }).allMatch(s -> !"quit".equalsIgnoreCase(s));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
