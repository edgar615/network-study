package com.edgar.network.start;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Address {

	public static void main(String[] args) throws IOException {
		InetAddress address = InetAddress.getByName("www.baidu.com");
		System.out.println(address);

		System.out.println("CanonicalHostName: "
				+ address.getCanonicalHostName());
		System.out.println("HostAddress: " + address.getHostAddress());
		System.out.println("HostName: " + address.getHostName());
		
//		To test to see whether this address is reachable, use the isReachable method as shown
//		next. Its argument specifies how long to wait before deciding that the address cannot be
//		reached. The argument is the number of milliseconds to wait
		System.out.println(address.isReachable(1000));
	}
}
