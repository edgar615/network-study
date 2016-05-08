package com.edgar.network.start;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by edgar on 16-5-7.
 */
public class NetworkInterfaceMain {

  //  getByInetAddress :	This	is	used	if	the	IP	address	is	known
//  getByName :	This	is	used	if	the	interface	name	is	known
//  getNetworkInterfaces :	This	provides	an	enumeration	of	available	interfaces
  public static void main(String[] args) {

    try {
      Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
      System.out.printf("Name						Display	name\n");
      while (enumeration.hasMoreElements()) {
        NetworkInterface element = enumeration.nextElement();
        System.out.printf("%-8s		%-32s\n",
                element.getName(), element.getDisplayName());
        //A	 getSubInterfaces 	method	will	return	an	enumeration	of	subinterfaces	if	any	exist
        Enumeration<NetworkInterface> subInters = element.getSubInterfaces();
        for (NetworkInterface sub : Collections.list(subInters)) {
          System.out.println(sub.getName());
        }
        //Each	network	interface	will	have	one	or	more	IP	addresses	associated	with	it.
        Enumeration<InetAddress> iNetadds = element.getInetAddresses();
        for (InetAddress address : Collections.list(iNetadds)) {
          System.out.printf("				InetAddress:	%s\n",
                  address);
        }

        //The	following	method	returns	a	string	containing	the	IP
//        address	and	its	MAC	address	for	a	 NetworkInterface 	instance.	The	 getHardwareAddress
//        method	returns	an	array	of	bytes	holding	the	number.	This	array	is	then	displayed	as	a
//        MAC	address.
        System.out.println(getMACIdentifier(element));

        //localhost
//        InetAddress	address	=	InetAddress.getLocalHost();
//        System.out.println("IP	address:	"	+	address.getHostAddress());
//        NetworkInterface	network	=
//                NetworkInterface.getByInetAddress(address);
//        System.out.println("MAC	address:	"	+
//                getMACIdentifier(network));
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
  }

  public static String getMACIdentifier(NetworkInterface network) {
    StringBuilder identifier = new StringBuilder();
    try {
      byte[] macBuffer = network.getHardwareAddress();
      if (macBuffer != null) {
        for (int i = 0; i < macBuffer.length; i++) {
          identifier.append(
                  String.format("%02X%s", macBuffer[i],
                          (i < macBuffer.length - 1) ? "-" : ""));
        }
      } else {
        return "---";
      }
    } catch (SocketException ex) {
      ex.printStackTrace();
    }
    return identifier.toString();
  }
}
