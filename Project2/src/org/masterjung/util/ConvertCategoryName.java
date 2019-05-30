package org.masterjung.util;

public class ConvertCategoryName {
	
	public String convertCategoryName(int boardListId) {
		String result ="";
		switch (boardListId) {
		case 1: result="질문게시판"; break;
		case 2: result="공략게시판";break;
		case 3: result="뉴스게시판";break;
		case 4: result="이미지게시판";break;
		case 5: result="동영상게시판";break;
		case 6: result="게임게시판";break;
		default:
			break;
		}
		
		return result;
	}
}
