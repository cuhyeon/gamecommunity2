package org.masterjung.service.tipboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dao.tipboard.TipDAO;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardDetailDto;

public class TipboardEditOkAcion implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		BoardDao dao = new BoardDao();
		Actionforward forward = new Actionforward();

		try {
			String strid = request.getParameter("id");
			int id = Integer.parseInt(strid);
			String title = request.getParameter("title");
			String content = request.getParameter("editor1");

			BoardDto dto = new BoardDto(id, title, content, "resource/img/masterjung.jpg");
			dao.updateBoardContent(dto);

			forward.setRedirect(true);
			forward.setPath("tipboard.tb?id=" + id);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;
	}
}