package org.masterjung.service.qaboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;

public class QaBoardDeleteOkAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		String strBoardid = request.getParameter("id");
		int boardid = Integer.parseInt(strBoardid);
		int resultrow = new BoardDao().deleteFakeBoardContent(boardid);
		
		Actionforward forward = new Actionforward();
		forward.setRedirect(true);
		forward.setPath("qaboard.qb");
	
		return forward;
	}
}
