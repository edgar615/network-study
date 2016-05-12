package com.edgar.network.nio;

import java.nio.ByteBuffer;

/**
 * Created by edgar on 16-5-12.
 */
public class BufferViewExample {

  public static void main(String[] args) {
    String	contents	=	"Book";
    ByteBuffer buffer	=	ByteBuffer.allocate(32);
    buffer.put(contents.getBytes());
    ByteBuffer	duplicateBuffer	=	buffer.duplicate();
    duplicateBuffer.put(0,(byte)0x4c);	//	'L'
    System.out.println("buffer:	"	+	buffer.get(0));
    System.out.println("duplicateBuffer:	"	+
            duplicateBuffer.get(0));

    buffer	=	ByteBuffer.allocate(32);
    ByteBuffer	readOnlyBuffer	=	buffer.asReadOnlyBuffer();

    System.out.println("Read-only:	"	+
            readOnlyBuffer.isReadOnly());
  }
}
