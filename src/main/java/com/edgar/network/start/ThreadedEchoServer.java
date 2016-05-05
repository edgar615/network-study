package com.edgar.network.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by edgar on 16-5-5.
 */
public class ThreadedEchoServer implements Runnable {

  private final Socket socket;

  public ThreadedEchoServer(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    System.out.println("Connected	to	client	using	["
            +	Thread.currentThread()	+	"]");
    try	(BufferedReader br	=	new	BufferedReader(
            new InputStreamReader(
                    socket.getInputStream()));
          PrintWriter out	=	new	PrintWriter(
                  socket.getOutputStream(),	true))	{
      String	inputLine;
      while	((inputLine	=	br.readLine())	!=	null)	{
        System.out.println("Client	request	["
                +	Thread.currentThread()	+	"]:	"	+	inputLine);
        out.println(inputLine);
      }
      System.out.println("Client	["	+	Thread.currentThread()
              +	"	connection	terminated");
    }	catch	(IOException ex)	{
      //	Handle	exceptions
    }
  }

  public	static	void	main(String[]	args)	{
    System.out.println("Threaded	Echo	Server");
    try	(ServerSocket serverSocket	=	new	ServerSocket(8080))	{
      while	(true)	{
        System.out.println("Waiting	for	connection.....");
        Socket clientSocket	=	serverSocket.accept();
        ThreadedEchoServer	tes	=
                new	ThreadedEchoServer(clientSocket);
        new	Thread(tes).start();
      }
    }	catch	(IOException	ex)	{
      //	Handle	exceptions
    }
    System.out.println("Threaded	Echo	Server	Terminating");
  }
}
