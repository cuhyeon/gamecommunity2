package org.masterjung.service.videoboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;

import org.masterjung.dto.join.BoardDetailDto;
import org.masterjung.dto.join.ReplyJoinReplyVoteDto;

public class VideoBoardReadAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {

		Actionforward forward = null;
		BoardDao dao = new BoardDao();


		int id = Integer.parseInt(request.getParameter("id"));
		int view_Count = dao.view_Count(id);
		dao.updateViewCount(id, ++view_Count);
		
		BoardDetailDto dto = dao.getBoardDetailById(id);
		List<ReplyJoinReplyVoteDto> dtoList = dao.getReplyListById(id);

		request.setAttribute("result", dto);
		request.setAttribute("resultList", dtoList);

		forward = new Actionforward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/videoboard/videocontent.jsp");

		return forward;

	}

}
