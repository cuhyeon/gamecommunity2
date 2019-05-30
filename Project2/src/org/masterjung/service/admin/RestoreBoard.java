package org.masterjung.service.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.AdminDao;
import org.masterjung.dto.join.BoardDetailDto;

public class RestoreBoard implements Action{

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = new Actionforward();
		AdminDao dao = new AdminDao();
		BoardDetailDto dto = dao.getDeleteBoardById(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("result", dto);
		
		forward.setPath("/WEB-INF/admin/adminDeleteBoardDetail.jsp");
		
		return forward;
	}

}
