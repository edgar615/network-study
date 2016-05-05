package com.edgar.network.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class URLConnChannel {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.baidu.com");
			URLConnection urlConnection = url.openConnection();
			InputStream in = urlConnection.getInputStream();
//			The ReadableByteChannel instance allows us to read from the site using its read method. A
			ReadableByteChannel channel = Channels.newChannel(in);
//			ByteBuffer instance receives data from the channel and is used as the argument of the
//			read method. The buffer created holds 64 bytes at a time.
			ByteBuffer buffer = ByteBuffer.allocate(64);
			
			while(channel.read(buffer) > 0 ) {
				System.out.println(new String(buffer.array()));
				//清空整个缓冲区
				buffer.clear();
			}
			channel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
