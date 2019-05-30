package org.masterjung.service.imageboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardDto2;
import org.masterjung.dto.join.BoardReplyDto;
import org.masterjung.util.ImagePagination;


public class ImageBoardAction implements Action {
	
	ImagePagination pagination = new ImagePagination();	

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
				
		Actionforward forward = new Actionforward();
		BoardDao dao = new BoardDao();
		List<BoardDto2> imageList;
		List<BoardReplyDto> resultList = null;
		
		int totalPage=0;
		int startPageBlock=0;
		int endPageBlock=0;		
		int totalListCount = dao.getCountContent(4);		
		
		if(request.getParameter("cp")!=null) {
			String cp = request.getParameter("cp");
			int curPage = Integer.parseInt(cp);
			pagination.setCurPage(curPage);
		}else {
			pagination.setPageSize(9);
			pagination.setCurPage(1);
		}
		
		if(request.getParameter("select")!=null) {
			String select = request.getParameter("select");
			int pageSize = Integer.parseInt(select);
			pagination.setPageSize(pageSize);
			
			totalPage = pagination.totalPage(totalListCount, pagination.getPageSize());
			startPageBlock = pagination.startPageBlock(pagination.getCurPage(), pagination.getPageSize());
			endPageBlock = pagination.endPageBlock(pagination.getCurPage(), pagination.getPageSize(), totalPage);
		
		}else {
			totalPage = pagination.totalPage(totalListCount, pagination.getPageSize());
			startPageBlock = pagination.startPageBlock(pagination.getCurPage(), pagination.getPageSize());
			endPageBlock = pagination.endPageBlock(startPageBlock, pagination.getPageSize(), totalPage);
		}
		
		int printStart = pagination.printStart(pagination.getCurPage(), pagination.getPageSize());
		int printEnd = pagination.printEnd(pagination.getCurPage(), pagination.getPageSize());
		if(printEnd>totalListCount) {
			printEnd=totalListCount;
		}				
		
		try {			
			request.setCharacterEncoding("UTF-8");
						
			resultList = dao.getContentList(4, printStart, pagination.getPageSize());
			
			request.setAttribute("curPage", pagination.getCurPage());
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPageBlock", startPageBlock);
			request.setAttribute("endPageBlock", endPageBlock);
			
			request.setAttribute("result", resultList);			
			
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/imageboard/imageboard.jsp");
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return forward;
	}
}
