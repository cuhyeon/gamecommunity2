package org.masterjung.service.imageboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardDetailDto;
import org.masterjung.dto.join.ReplyJoinReplyVoteDto;


public class ImageEditAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Actionforward forward = new Actionforward();
		HttpSession session = request.getSession();
		BoardDao dao = new BoardDao();
		BoardDetailDto boardDetailDto = dao.getBoardDetailById(Integer.parseInt(request.getParameter("id")));
					
		try {
			
			request.setCharacterEncoding("UTF-8");
			
			int id = Integer.parseInt(request.getParameter("id"));
			BoardDetailDto dto = dao.getBoardDetailById(id);
			
			request.setAttribute("imageList", dto);
			
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/imageboard/imageedit.jsp");
			
			if(session.getAttribute("user_auth") == null ) {
				forward.setPath("/imageboard.ib");
			}
			if((boardDetailDto.getNick_name()+"").equals((session.getAttribute("nick_name")+"")) || session.getAttribute("user_auth").equals(3)) {
				forward.setPath("/WEB-INF/imageboard/imageedit.jsp");
				
			}else {
				forward.setPath("/imageboard.ib");
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
 