package org.masterjung.service.tipboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.join.BoardDetailDto;
import org.masterjung.dto.join.ReplyJoinReplyVoteDto;

public class TipBoardDetailAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
		response.setHeader("Content-Type", "application/json");
		response.setHeader("Accept", "application/json");
		BoardDao dao = new BoardDao();
		String StrId = request.getParameter("id");
		int id = Integer.parseInt(StrId);
		int view_Count = dao.view_Count(id);
		dao.updateViewCount(id, ++view_Count);
		
		BoardDetailDto dto = dao.getBoardDetailById(id);
		List<ReplyJoinReplyVoteDto> dtoList = dao.getReplyListById(id); 
				
		request.setAttribute("board", dto);
		request.setAttribute("boardList", dtoList);
		Actionforward forward = new Actionforward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/tipboard/tipboarddetail.jsp");
	
		return forward;
		}
	
	}
