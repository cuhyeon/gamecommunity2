package org.masterjung.service.qaboard;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;



public class QaWriteEditAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = new BoardDao();
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
		BoardDto dto = dao.getInfoEditById(id);
		request.setAttribute("result", dto);

		Actionforward forward = new Actionforward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/qaboard/qawrite_edit.jsp");

		return forward;
	}

}
