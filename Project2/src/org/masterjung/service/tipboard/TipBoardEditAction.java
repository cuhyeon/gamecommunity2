package org.masterjung.service.tipboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardDetailDto;

public class TipBoardEditAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = new Actionforward();
		HttpSession session = request.getSession();
		BoardDao dao = new BoardDao();
		BoardDetailDto boardDetailDto = dao.getBoardDetailById(Integer.parseInt(request.getParameter("id")));
		try {
			String strId = request.getParameter("id");
			int id = Integer.parseInt(strId);
			BoardDto board = dao.getInfoEditById(id);
			request.setAttribute("board", board);

			if(session.getAttribute("user_auth") == null ) {
				forward.setPath("/tipboard.tb");
			}
			if((boardDetailDto.getNick_name()+"").equals((session.getAttribute("nick_name")+"")) || session.getAttribute("user_auth").equals(3)) {
				forward.setPath("/WEB-INF/tipboard/tipboardedit.jsp");
				
			}else {
				forward.setPath("/tipboard.tb");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}

}
