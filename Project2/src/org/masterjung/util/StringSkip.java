package org.masterjung.util;

public class StringSkip {
	
	public String stringSkip(String beforeString) {
		String stringSkip="";
		if(beforeString.length()>20) {
			stringSkip = (beforeString.substring(0, 20)).replace("\n", " ") + " ...";
			
			
		}else {
			stringSkip = beforeString.replace("\n", " ");
		}
		return stringSkip;
	}
	
	public String stringSkip(String beforeString, int location) {
		String stringSkip="";
		if(beforeString.length()>location) {
			stringSkip = (beforeString.substring(0, location)).replace("\n", " ") + " ...";
		}else {
			stringSkip = beforeString.replace("\n", " ");
		}
		return stringSkip;
	}
	
	public String stringSkip(String beforeString, int location, String separator) {
		String stringSkip="";
		if(beforeString.length()>location) {
			stringSkip = (beforeString.substring(0, location)).replace("\n", " ") + separator;
		}else {
			stringSkip = beforeString.replace("\n", " ");
		}
		return stringSkip;
	}
}
