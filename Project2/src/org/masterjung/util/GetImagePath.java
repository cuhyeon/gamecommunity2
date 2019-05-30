package org.masterjung.util;


public class GetImagePath {
	public String imagePath(String path) {
		
		String iname = "default-img";
		int idx = path.indexOf("-img");
		
		String endPath = path.substring(path.length()-4, path.length());
		String startPath = path.substring(0, idx+4);
		
		
		String fullPath = startPath + endPath ;
		
		// /resource/img/default-imgㅌㅌ.gif
		return fullPath;
	}
}
