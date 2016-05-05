package com.edgar.network.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLConn {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.baidu.com");
			URLConnection urlConnection = url.openConnection();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(urlConnection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
