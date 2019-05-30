package org.masterjung.util;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class GetIp {

	public String getIp() {
		String ip="";
		try {
			ip = Inet4Address.getLocalHost().getHostAddress();
			System.out.println(ip);
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		
		return ip;
	}
}