package org.masterjung.service.imageboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardDetailDto;
import org.masterjung.dto.join.ReplyJoinReplyVoteDto;


public class ImageDetailAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Actionforward forward = new Actionforward();
		BoardDao dao = new BoardDao();
		BoardDetailDto boardDto = new BoardDetailDto();
		
		try {			
			request.setCharacterEncoding("UTF-8");			
			
			boardDto = dao.getBoardDetailById(Integer.parseInt(request.getParameter("id")));
			boardDto.setView_count(boardDto.getView_count()+1);
			dao.updateViewCount(Integer.parseInt(request.getParameter("id")), boardDto.getView_count());
			request.setAttribute("imageList", boardDto);			
			
			int id = Integer.parseInt(request.getParameter("id"));
			BoardDetailDto dto = dao.getBoardDetailById(id);
			
			request.setAttribute("imageList", dto);
			
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/imageboard/imagedetail.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
 