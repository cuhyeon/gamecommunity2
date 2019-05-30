package org.masterjung.service.imageboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.join.BoardDetailDto;

public class ImageBoardDeleteAction implements Action{

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Actionforward forward = new Actionforward();
		HttpSession session = request.getSession();
		BoardDao dao = new BoardDao();
		BoardDetailDto boardDetailDto = dao.getBoardDetailById(Integer.parseInt(request.getParameter("id")));
		
		try {
			request.setCharacterEncoding("UTF-8");			
			
			if(session.getAttribute("user_auth") == null ) {
				forward.setPath("/imageboard.ib");
			}
			if((boardDetailDto.getNick_name()+"").equals((session.getAttribute("nick_name")+"")) || session.getAttribute("user_auth").equals(3)) {
				String strid = request.getParameter("id");
				int id = Integer.parseInt(strid);
				
				int strBoardid = Integer.parseInt(request.getParameter("id"));			
				int boardid = strBoardid;
				int resultrow = new BoardDao().deleteFakeBoardContent(boardid);
				
				dao.deleteFakeBoardContent(id);
				forward.setRedirect(true);
				forward.setPath("imageboard.ib?id=" + id);
				
			}else {
				forward.setPath("/imageboard.ib");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return forward;
	}

}
