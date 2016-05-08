package com.edgar.network.start;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by edgar on 16-5-7.
 */
public class URIMain {
  public static void main(String[] args) throws URISyntaxException, MalformedURLException {
//    The	general	syntax	of	a	URI	consists	of	a	scheme	and	a	scheme-specific-part:
//    [scheme:]	scheme-specific-part
//    There	are	many	schemes	that	are	used	with	a	URI,	including:
//    file:	This	is	used	for	files	systems
//    FTP:	This	is	File	Transfer	Protocol
//    HTTP:	This	is	commonly	used	for	websites
//    mailto:	This	is	used	as	part	of	a	mail	service
//    urn:	This	is	used	to	identify	a	resource	by	name

    URL url = new URL("http://www.baidu.com");
    System.out.println("URL:	"	+	url);
    System.out.printf("		Protocol:	%-32s		Host:	%-32s\n",
            url.getProtocol(),url.getHost());
    System.out.printf("						Port:	%-32d		Path:	%-32s\n",
            url.getPort(),url.getPath());
    System.out.printf("	Reference:	%-32s		File:	%-32s\n",
            url.getRef(),url.getFile());
    System.out.printf("	Authority:	%-32s	Query:	%-32s\n",
            url.getAuthority(),url.getQuery());
    System.out.println("	User	Info:	"	+	url.getUserInfo());
  }
}
