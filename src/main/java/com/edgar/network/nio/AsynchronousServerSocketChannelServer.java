package com.edgar.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by edgar on 16-5-12.
 */
public class AsynchronousServerSocketChannelServer {
  public	AsynchronousServerSocketChannelServer()	{
    System.out.println("Asynchronous	Server	Started");
    try	(AsynchronousServerSocketChannel serverChannel
                  =	AsynchronousServerSocketChannel.open())	{
      InetSocketAddress hostAddress
              =	new	InetSocketAddress("localhost",	5000);
      serverChannel.bind(hostAddress);
      System.out.println("Waiting	for	client	to	connect...	");
      Future acceptResult	=	serverChannel.accept();
      try	(AsynchronousSocketChannel clientChannel
                    =	(AsynchronousSocketChannel)	acceptResult.get())	{
        System.out.println("Messages	from	client:	");
        while	((clientChannel	!=	null)	&&	(clientChannel.isOpen()))	{
          ByteBuffer buffer	=	ByteBuffer.allocate(32);
          Future	result	=	clientChannel.read(buffer);
          while (!result.isDone()) {
            //do nothing
          }
//          result.get()
          //	Wait	until	buffer	is	ready	using
          //	one	of	three	techniques	to	be	discussed
          buffer.flip();
          String	message	=	new	String(buffer.array()).trim();
          System.out.println(message);
          if	(message.equals("quit"))	{
            break;
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }	catch	(IOException  ex)	{
      ex.printStackTrace();
    }
  }

  public	static	void	main(String[]	args)	{
    new	AsynchronousServerSocketChannelServer();
  }
}
