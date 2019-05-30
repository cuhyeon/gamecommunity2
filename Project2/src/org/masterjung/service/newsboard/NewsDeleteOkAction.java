package org.masterjung.service.newsboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;

public class NewsDeleteOkAction implements Action{

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = new Actionforward();
		BoardDao dao = new BoardDao();
		int result = dao.deleteFakeBoardContent(Integer.parseInt(request.getParameter("id")));

		forward.setPath("/newboard.nb");
		return forward;
	}

}
