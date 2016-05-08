package com.edgar.network.start;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by edgar on 16-5-7.
 */
public class Ipv6Main {
  public static void main(String[] args) throws UnknownHostException {
    try	{
      InetAddress	names[]	=
              InetAddress.getAllByName("www.163.com");
      for	(InetAddress	address	:	names)	{
        if	((address	instanceof	Inet6Address)	&&
                ((Inet6Address)	address)
                        .isIPv4CompatibleAddress())	{
          System.out.println(address
                  +	"	is	IPv6	Compatible	Address");
        }	else	{
          System.out.println(address
                  +	"	is	not	a	IPv6	Compatible	Address");
        }
      }
    }	catch	(UnknownHostException	ex)	{
      //	Handle	exceptions
    }

  }

//  -Djava.net.preferIPv4Stack=false
//  This	is	the	default	setting.	If	IPv6	is	available,	then	the	application	can	use	either	IPv4	or
//  IPv6	hosts.	If	set	to	 true ,	it	will	use	IPv4	hosts.	IPv6	hosts	will	not	be	used.
//  The	second	setting	deals	with	the	type	of	addresses	used:
//          -Djava.net.preferIPv6Addresses=false
//  This	is	the	default	setting.	If	IPv6	is	available,	it	will	prefer	IPv4	addresses	over	IPv6
//  addresses.	This	is	preferred	because	it	allows	backward	compatibility	for	IPv4	services.	If
//  set	to	 true ,	it	will	use	IPv6	addresses	whenever	possible.
}
