package org.masterjung.service.newsboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardDetailDto;
import org.masterjung.dto.join.ReplyJoinReplyVoteDto;

public class NewsDetailAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = null;
		BoardDao dao = new BoardDao();
		BoardDetailDto boardDto = new BoardDetailDto();
		try {
			forward = new Actionforward();
			boardDto = dao.getBoardDetailById(Integer.parseInt(request.getParameter("id")));
			boardDto.setView_count(boardDto.getView_count()+1);
			dao.updateViewCount(Integer.parseInt(request.getParameter("id")), boardDto.getView_count());
			request.setAttribute("boardId", boardDto);
			request.setAttribute("count", boardDto.getCount());
			List<ReplyJoinReplyVoteDto> boardReplyList = dao.getReplyListById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("page",request.getParameter("page"));
			request.setAttribute("dto2", boardReplyList);

			forward.setPath("/WEB-INF/newsboard/newsDetail.jsp");
		} catch (Exception e) {
		}
		return forward;
	}

}
