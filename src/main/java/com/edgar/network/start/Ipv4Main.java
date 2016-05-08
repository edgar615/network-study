package com.edgar.network.start;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by edgar on 16-5-7.
 */
public class Ipv4Main {
  public static void main(String[] args) throws UnknownHostException {
    Inet4Address address = (Inet4Address) InetAddress.getByName("www.baidu.com");
    System.out.println(address);
    address = (Inet4Address) Inet4Address.getByName("www.baidu.com");
    System.out.println(address);

//    Special	IPv4	addresses
//    There	are	several	special	IPv4	addresses,	including	these	two:
//    0.0.0.0:	This	is	called	an	unspecified	IPv4	address	(wildcard	address)	and	is	normally
//    used	when	a	network	interface	does	not	have	a	IP	address	and	is	attempting	to	obtain
//            one	using	DHCP.
//    127.0.0.1:	This	is	known	as	the	loopback	address.	It	provides	a	convenient	way	to
//    send	oneself	a	message,	often	for	testing	purposes.
  }
}
