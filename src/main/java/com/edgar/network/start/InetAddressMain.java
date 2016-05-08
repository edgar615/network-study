package com.edgar.network.start;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by edgar on 16-5-7.
 */
public class InetAddressMain {
  public static void main(String[] args) throws UnknownHostException {
    //The	 InetAddress 	class	represents	an	IP	address.	The	IP	protocol	is	a	low-level	protocol
//    used	by	the	UDP	and	TCP	protocols.	An	IP	address	is	either	a	32-bit	or	a	128-bit	unsigned
//    number	that	is	assigned	to	a	device.
    InetAddress names[] =
            InetAddress.getAllByName("www.163.com");
    for (InetAddress element : names) {
      System.out.println(element);
    }
//    www.163.com/221.235.205.207
//    www.163.com/111.178.233.88
//    www.163.com/61.153.56.182
//    www.163.com/58.50.31.33

    for (InetAddress element : names) {
      displayInetAddressInformation(element);
    }

//    Link-local:	This	is	used	within	a	single	local	subnet	that	is	not	connected	to	the
//    Internet.	No	routers	are	present.	Allocation	of	link-local	addresses	is	done
//    automatically	when	the	computer	does	not	have	a	static	IP-address	and	cannot	find	a
//    DHCP	server.
//    Site-local:	This	is	used	when	the	address	does	not	require	a	global	prefix	and	is
//    unique	within	a	site.	It	cannot	be	reached	directly	from	the	Internet	and	requires	a
//    mapping	service	such	as	NAT.
//            Global:	As	its	name	implies,	the	address	is	unique	throughout	the	Internet.
    for (InetAddress elment : names) {
      //This	is	an	address	that	matches	any	local	address.	It	is	a	wildcard	address.
      System.out.println(elment.isAnyLocalAddress());
      //This	is	a	loopback	address.	For	IPv4,	it	is	 127.0.0.1 ,	and	for	IPv6,	it	is
//      0:0:0:0:0:0:0:1
      System.out.println(elment.isLoopbackAddress());

      //This	is	a	link-local	address.
      System.out.println(elment.isLinkLocalAddress());

      //This	is	local	to	a	site.	They	can	be	reached	by	other	nodes	on	different	networks	but
//      within	the	same	site.
      System.out.println(elment.isSiteLocalAddress());

//This	is	a	multicast	address.
      System.out.println(elment.isMulticastAddress());
      //This	is	a	link-local	multicast	address.
      System.out.println(elment.isMCLinkLocal());
      //This	is	a	node-local	multicast	address.
      System.out.println(elment.isMCNodeLocal());
      //This	is	a	site-local	multicast	address.
      System.out.println(elment.isMCSiteLocal());
      //This	is	an	organization-local	multicast	address.
      System.out.println(elment.isMCOrgLocal());
      //This	is	a	global	multicast	address.
      System.out.println(elment.isMCGlobal());
    }

    //Testing	reachability
    String	URLAddress	=	"www.baidu.com";
    InetAddress[]	addresses	=
            InetAddress.getAllByName(URLAddress);
    for	(InetAddress	inetAddress	:	addresses)	{
      try	{
        if	(inetAddress.isReachable(10000000))	{
          System.out.println(inetAddress	+	"	is	reachable");
        }	else	{
          System.out.println(inetAddress	+
                  "	is	not	reachable");
        }
      }	catch	(IOException ex)	{
        //	Handle	exceptions
      }
    }
  }

  private static void displayInetAddressInformation(
          InetAddress address) {
    System.out.println(address);
    System.out.println("CanonicalHostName:	" +
            address.getCanonicalHostName());
    System.out.println("HostName:	" + address.getHostName());
    System.out.println("HostAddress:	" +
            address.getHostAddress());
  }
}
