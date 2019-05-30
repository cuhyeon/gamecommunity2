package org.masterjung.util;

public class QaPagination {
	private int pageSize;
	private int curPage;

	
	public QaPagination() {
		pageSize = 5;
		curPage = 1;
	}
	
	public int totalPage(int totalListCount, int pageSize) {
		int totalPage = totalListCount / pageSize;
		if(totalListCount % pageSize>0) {
			totalPage++;
		}
		return totalPage;
	}
	// 1 5 1     1 10 1
	// 5 5 6     10 10 11
	// 10 5 11   20 10 21
	// 15 5 16
	public int startPageBlock(int currentPage, int pageSize) {
		int startPage =(currentPage / pageSize)*pageSize+1;
		if(currentPage!=1 && currentPage%pageSize ==0) {
			startPage =((currentPage-1) / pageSize)*pageSize+1;
		}
		return startPage;
	}
	
	public int endPageBlock(int startPage, int pageSize, int totalPage) {
		int endPage = startPage + pageSize -1;
		if(endPage>totalPage) {
			endPage=totalPage;
		}
		return endPage;		
	}
	
	public int printStart(int currentPage, int pageSize) {
		int printStart = currentPage * pageSize -(pageSize -1);
		return printStart-1;
	}
	
	public int printEnd(int currentPage, int pageSize) {
		int printEnd = currentPage * pageSize;
		return printEnd-1;
	}
	
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	
}
