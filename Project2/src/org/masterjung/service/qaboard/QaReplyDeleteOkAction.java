package org.masterjung.service.qaboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;

public class QaReplyDeleteOkAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		String strReply = request.getParameter("replyid");
		String boardid = request.getParameter("boardid");
		int replyid = Integer.parseInt(strReply);
		int resultrow = new BoardDao().deleteFakeBoardreply(replyid);
		Actionforward forward = new Actionforward();
		forward.setRedirect(true);
		forward.setPath("qadetail.qb?id="+boardid);
	
		return forward;
	}
}
