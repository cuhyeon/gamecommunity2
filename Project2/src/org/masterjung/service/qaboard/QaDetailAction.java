package org.masterjung.service.qaboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.join.BoardDetailDto;
import org.masterjung.dto.join.ReplyJoinReplyVoteDto;


public class QaDetailAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = new BoardDao();
		String StrId = request.getParameter("id");
		int id = Integer.parseInt(StrId);
		int view_Count = dao.view_Count(id);
		dao.updateViewCount(id, ++view_Count);
		
		BoardDetailDto dto = dao.getBoardDetailById(id);
		List<ReplyJoinReplyVoteDto> dtoList = dao.getReplyListById(id); 
				
		request.setAttribute("result", dto);
		request.setAttribute("resultList", dtoList);
		Actionforward forward = new Actionforward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/qaboard/qaboard_detail.jsp");
	
		return forward;
	}

}
