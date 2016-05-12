package com.edgar.network.nio;

import java.nio.IntBuffer;

/**
 * Created by edgar on 16-5-12.
 */
public class BufferExample {

  public static void main(String[] args) {
    int[] arr = {12, 51, 79, 54};
    IntBuffer buffer = IntBuffer.allocate(6);
    buffer.put(arr);
    System.out.println(buffer);
    displayBuffer(buffer);

    int	length	=	buffer.remaining();
    buffer.put(arr,	0,	length);

    System.out.println(buffer);
    displayBuffer(buffer);

    length	=	buffer.remaining();
    buffer.put(arr,	0,	length);

    System.out.println(buffer);
    displayBuffer(buffer);

  }

  public static void displayBuffer(IntBuffer buffer) {
    for (int i = 0; i < buffer.position(); i++) {
      System.out.print(buffer.get(i) + "	");
    }
    System.out.println();
  }

  public static	void	displayBuffer2(IntBuffer	buffer)	{
    int	arr[]	=	new	int[buffer.position()];
    buffer.rewind();
    buffer.get(arr);
    for(int	element	:	arr)	{
      System.out.print(element	+	"	");
    }
  }
}
