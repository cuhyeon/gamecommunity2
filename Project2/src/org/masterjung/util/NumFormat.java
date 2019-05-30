package org.masterjung.util;

import java.text.NumberFormat;

public class NumFormat {
	
	public String Format(int number) {
		String commaNum = NumberFormat.getInstance().format(number);
		return commaNum;
	}
}
