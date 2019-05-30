package org.masterjung.service.qaboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;

public class QaWriteEditOkAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = new BoardDao();
		String strid = request.getParameter("id");
		int id = Integer.parseInt(strid);
		String title = request.getParameter("title");
		String content = request.getParameter("editor1");
				
		BoardDto dto = new BoardDto(id, title, content, "resource/img/masterjung.jpg");
		dao.updateBoardContent(dto);
		
		Actionforward forward = new Actionforward();
		forward.setRedirect(true);
		forward.setPath("qadetail.qb?id="+id);
	
		return forward;
	}

}
