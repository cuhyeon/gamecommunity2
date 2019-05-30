package org.masterjung.util;

public class IbPagination {
	public static int pageSize;
	public static int curPage;

	
	public IbPagination() {
		pageSize = 10;
		curPage =1;
	}
	
	public static int totalPage(int totalListCount, int pageSize) {
		int totalPage = totalListCount / pageSize;
		if(totalListCount % pageSize>0) {
			totalPage++;
		}
		return totalPage;
	}
	
	public static int startPageBlock(int currentPage, int pageSize) {
		int startPage = (currentPage / pageSize)*pageSize+1;
		return startPage;
	}
	
	public static int endPageBlock(int startPage, int pageSize, int totalPage) {
		int endPage = startPage + pageSize -1;
		if(endPage>totalPage) {
			endPage=totalPage;
		}
		return endPage;		
	}
	
	public static int printStart(int currentPage, int pageSize) {
		int printStart = currentPage * pageSize -(pageSize -1);
		return printStart-1;
	}
	
	public static int printEnd(int currentPage, int pageSize) {
		int printEnd = currentPage * pageSize;
		return printEnd-1;
	}
	
	
	
	
}
