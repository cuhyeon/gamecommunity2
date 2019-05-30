package org.masterjung.service.register;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.join.BoardDto2;
import org.masterjung.dto.join.BoardReplyDtoIndex;

public class IndexAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = new BoardDao();
		List<BoardDto2> boardList = dao.getBoardListAll(7);
		List<BoardDto2> boardListView = dao.getBoardListAllByView(7);
		List<BoardDto2> newsList = dao.getBoardMain(3, 7);
		List<BoardDto2> tipsList = dao.getBoardMain(2, 7);
		List<BoardReplyDtoIndex> qaboardList = dao.getContentListOrderByReply(1, 0, 7);
		
		request.setAttribute("boardListView", boardListView);
		request.setAttribute("boardList", boardList);
		request.setAttribute("newsList", newsList);
		request.setAttribute("qaboardList", qaboardList);
		request.setAttribute("tipsList", tipsList);
		
		Actionforward forward = new Actionforward();
   		forward.setRedirect(false);
   		forward.setPath("/WEB-INF/index.jsp");
		return forward;
	}
}
