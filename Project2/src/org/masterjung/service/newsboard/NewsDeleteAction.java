package org.masterjung.service.newsboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.join.BoardDetailDto;

public class NewsDeleteAction implements Action{

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = null;
		HttpSession session = request.getSession();
		try {
			forward = new Actionforward();
			BoardDao dao = new BoardDao();
			BoardDetailDto boardDetailDto = dao.getBoardDetailById(Integer.parseInt(request.getParameter("id")));
			

			if(session.getAttribute("user_auth") == null ) {
				forward.setPath("/newboard.nb");
			}
			if((boardDetailDto.getNick_name()+"").equals((session.getAttribute("nick_name")+"")) || session.getAttribute("user_auth").equals(3) || session.getAttribute("user_auth").equals(2)) {
				request.setAttribute("dto", boardDetailDto);
				forward.setPath("/WEB-INF/newsboard/newsDelete.jsp");
				
			}else {
				forward.setPath("/newboard.nb");
			}
			


		} catch (Exception e) {
		}
		return forward;
	}

}
