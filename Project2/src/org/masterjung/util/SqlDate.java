package org.masterjung.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class SqlDate {
	private static class TIME_MAXIMUM {

		public static final int SEC = 60;
		public static final int MIN = 60;
		public static final int HOUR = 24;
		public static final int DAY = 30;
		public static final int MONTH = 12;
	}

	public String dateFormat(Date date, String format) {
		SimpleDateFormat sdfDate = new SimpleDateFormat(format);
		
		return sdfDate.format(date);
	}
	public String timeFormat(Date date) {
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
		return sdfTime.format(date);
	}
	
	public String dateToText(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = simpleDateFormat.format(date);
		return strDate;
	}
	
	public long stringDateToTimestamp(String stringDate) {
		long timestamp =0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		try {
			java.util.Date date = simpleDateFormat.parse(stringDate);
			timestamp = date.getTime();
		} catch (ParseException e) {
			System.out.println("timestamp 변환함수 오류"+e.getMessage());
			e.printStackTrace();
		}
		return timestamp;
	}
	
	public Date timestampToDate(Timestamp timestamp) {
		Date date = Date.valueOf(timestamp.toLocalDateTime().toLocalDate());
		
		return date;
	}
	
	public Date textToSqlDate(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date longDate;
		java.sql.Date sqlDate = null;
		
			try {
				longDate = simpleDateFormat.parse(date);
				sqlDate = new java.sql.Date(longDate.getTime());
			} catch (ParseException e) {
				System.out.println("DATE 변환함수 오류"+e.getMessage());
				e.printStackTrace();
			}
			return sqlDate;
	}
	
	public Date textToSqlDate(String date, String simpledateformat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(simpledateformat);
		java.util.Date longDate;
		java.sql.Date sqlDate = null;
			try {
				longDate = simpleDateFormat.parse(date);
				sqlDate = new java.sql.Date(longDate.getTime());
			} catch (ParseException e) {
				System.out.println("DATE 변환함수 오류"+e.getMessage());
				e.printStackTrace();
			}
			return sqlDate;
	}
	
	public Timestamp currentTime() {
		Calendar cal = new GregorianCalendar();
		Timestamp ts = new Timestamp(cal.getTimeInMillis());		
		return ts;
	}
	
	public String currentTimeString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = new GregorianCalendar();
		Timestamp ts = new Timestamp(cal.getTimeInMillis());		
		String currentTime = simpleDateFormat.format(ts);
		
		return currentTime;
	}
	
	
	public static String formatTimeString(Date tempDate) {
		long curTime = System.currentTimeMillis();
		long regTime = tempDate.getTime();
		long diffTime = (curTime - regTime) / 1000;

		String msg = null;

		if (diffTime < TIME_MAXIMUM.SEC) {
			// sec
			msg = "방금 전";

		} else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
			// min
			msg = diffTime + "분 전";

		} else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
			// hour
			msg = (diffTime) + "시간 전";

		} else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
			// day
			msg = (diffTime) + "일 전";

		} else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
			// day
			msg = (diffTime) + "달 전";
		} else {
			msg = (diffTime) + "년 전";
		}
		return msg;
	}
}

