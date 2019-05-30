package org.masterjung.service.tboard;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.join.BoardReplyDtoIndex;
import org.masterjung.util.Pagination;
import org.masterjung.util.QaPagination;

public class TboardAction implements Action {
	
	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		QaPagination pagination = new QaPagination();
		int totalPage=0;
		int startPageBlock=0;
		int endPageBlock=0;
		BoardDao dao = new BoardDao();
		int totalListCount = dao.getCountContent();
		List<BoardReplyDtoIndex> resultList = new ArrayList<BoardReplyDtoIndex>();
		
		if(request.getParameter("cp")!=null) {
			String cp = request.getParameter("cp");
			int curPage = Integer.parseInt(cp);
			pagination.setCurPage(curPage);
			pagination.setPageSize(10);
		}else {
			pagination.setPageSize(10);
			pagination.setCurPage(1);
		}
		int pageSize = pagination.getPageSize();
		if(request.getParameter("select")!=null) {
			String select = request.getParameter("select");
			pageSize = Integer.parseInt(select);
			pagination.setPageSize(pageSize);
			
			totalPage = pagination.totalPage(totalListCount, pageSize);
			startPageBlock = pagination.startPageBlock(pagination.getCurPage(), pageSize);
			endPageBlock = pagination.endPageBlock(pagination.getCurPage(), pageSize, totalPage);
		
		}else {
			totalPage = pagination.totalPage(totalListCount, pagination.getPageSize());
			startPageBlock = pagination.startPageBlock(pagination.getCurPage(), pageSize);
			endPageBlock = pagination.endPageBlock(startPageBlock, pageSize, totalPage);
		}
		
		int printStart = pagination.printStart(pagination.getCurPage(), pageSize);
		int printEnd = pagination.printEnd(pagination.getCurPage(), pageSize);
		if(printEnd>totalListCount) {
			printEnd=totalListCount;
		}
		
		resultList = dao.getContentList(printStart, pageSize);
		request.setAttribute("curPage", pagination.getCurPage());
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPageBlock", startPageBlock);
		request.setAttribute("endPageBlock", endPageBlock);
		request.setAttribute("totalListCount", totalListCount);
		request.setAttribute("result", resultList);
		request.setAttribute("pageSize", pageSize);
		
		Actionforward forward = new Actionforward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/totalboard/tboard.jsp");
	
		return forward;
	}

}
