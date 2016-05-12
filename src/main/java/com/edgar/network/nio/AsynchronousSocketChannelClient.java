package com.edgar.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsynchronousSocketChannelClient {
  public static void main(String[] args) {
    System.out.println("Asynchronous	Client	Started");
    try (AsynchronousSocketChannel client =
                 AsynchronousSocketChannel.open()) {
      InetSocketAddress hostAddress	=
              new	InetSocketAddress("localhost",	5000);
      Future future	=	client.connect(hostAddress);
      future.get();
      System.out.println("Client	is	started:	"	+	client.isOpen());
      System.out.println("Sending	messages	to	server:	");
      Scanner scanner	=	new	Scanner(System.in);
      String	message;
      while	(true)	{
        System.out.print(">	");
        message	=	scanner.nextLine();
        ByteBuffer buffer	=	ByteBuffer.wrap(message.getBytes());
        Future	result	=	client.write(buffer);
        while	(!result.isDone())	{
          //	Wait
        }
        if	(message.equalsIgnoreCase("quit"))	{
          break;
        }
      }

    } catch (IOException ex) {
      //	Handle	exception
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}