package com.edgar.network.start;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by edgar on 16-5-7.
 */
public class URLMain {
  public static void main(String[] args) throws URISyntaxException {
//    The	general	syntax	of	a	URI	consists	of	a	scheme	and	a	scheme-specific-part:
//    [scheme:]	scheme-specific-part
//    There	are	many	schemes	that	are	used	with	a	URI,	including:
//    file:	This	is	used	for	files	systems
//    FTP:	This	is	File	Transfer	Protocol
//    HTTP:	This	is	commonly	used	for	websites
//    mailto:	This	is	used	as	part	of	a	mail	service
//    urn:	This	is	used	to	identify	a	resource	by	name

    URI uri = new URI("http://www.baidu.com");
    //This	is	the	entity	responsible	for	resolving	the	URI
    System.out.println(uri.getAuthority());
    //Scheme
    System.out.println(uri.getScheme());
    //getSchemeSpecificPart
    System.out.println("getSchemeSpecificPart:	"
            +	uri.getSchemeSpecificPart());
    //Host
    System.out.println(uri.getHost());
    //Path
    System.out.println(uri.getPath());
    //Query
    System.out.println(uri.getQuery());

    //Scheme
    System.out.println(uri.getFragment());

    //UserInfo
    System.out.println(uri.getUserInfo());

    //normalize
    System.out.println(uri.normalize());
  }
}
