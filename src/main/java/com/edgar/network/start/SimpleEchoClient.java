package com.edgar.network.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleEchoClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Waiting for connection....");
			InetAddress inetAddress = InetAddress.getLocalHost();
			try (Socket socket = new Socket(inetAddress, 8080)) {
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
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
