package org.masterjung.service.admin;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.AdminDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.util.PaginationAdmin;

public class DeleteBoardListAction implements Action{
	PaginationAdmin pagination = new PaginationAdmin();
	
	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = new Actionforward();
		AdminDao dao = new AdminDao();
		HttpSession session = request.getSession();
		int totalCount=0;
		int totalPage =0;
		int startPageBlock=0;
		int endPageBlock=0;
		List<BoardDto> dto;
		String spage = request.getParameter("cp");
		
		try {
			totalCount = dao.getDeleteBoardListCount();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if(request.getParameter("cp")!=null) {
			String cp = request.getParameter("cp");
			int curPage = Integer.parseInt(cp);
			pagination.setCurPage(curPage);
		}else {
			pagination.setPageSize(20);
			pagination.setCurPage(1);
		}
		
		if(request.getParameter("select")!=null) {
			String select = request.getParameter("select");
			int pageSize = Integer.parseInt(select);
			pagination.setPageSize(pageSize);
			
			totalPage = pagination.totalPage(totalCount, pagination.getPageSize());
			startPageBlock = pagination.startPageBlock(pagination.getCurPage(), pagination.getPageSize());
			endPageBlock = pagination.endPageBlock(pagination.getCurPage(), pagination.getPageSize(), totalPage);
		
		}else {
			pagination.setPageSize(20);
			totalPage = pagination.totalPage(totalCount, pagination.getPageSize());
			startPageBlock = pagination.startPageBlock(pagination.getCurPage(), pagination.getPageSize());
			endPageBlock = pagination.endPageBlock(startPageBlock, pagination.getPageSize(), totalPage);
		}
		
		int printStart = pagination.printStart(pagination.getCurPage(), pagination.getPageSize());
		int printEnd = pagination.printEnd(pagination.getCurPage(), pagination.getPageSize());
		if(printEnd>totalCount) {
			printEnd=totalCount;
		}
		dto = dao.getDeleteBoardList(printStart,pagination.getPageSize());
		
		request.setAttribute("curPage", pagination.getCurPage());
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPageBlock", startPageBlock);
		request.setAttribute("endPageBlock", endPageBlock);
		
		request.setAttribute("deleteboardCount",totalCount);
		request.setAttribute("deleteboardList", dto);
		
		if(session.getAttribute("user_auth")==null || !(session.getAttribute("user_auth").equals(3))) {
			forward.setPath("/WEB-INF/page_404_error.jsp");
		}else {
			forward.setPath("/WEB-INF/admin/adminDeleteBoardList.jsp");
		}
		return forward;
		
	}

}
