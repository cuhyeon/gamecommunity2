package org.masterjung.service.tipboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardDetailDto;

public class TipBoardDeleteOkAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		BoardDao dao = new BoardDao();
		BoardDetailDto boardDetailDto = dao.getBoardDetailById(Integer.parseInt(request.getParameter("id")));
		Actionforward forward = new Actionforward();

		try {
			
			
			if(session.getAttribute("user_auth") == null ) {
				forward.setPath("/tipboard.tb");
			}
			if((boardDetailDto.getNick_name()+"").equals((session.getAttribute("nick_name")+"")) || session.getAttribute("user_auth").equals(3)) {
				String strid = request.getParameter("id");
				int id = Integer.parseInt(strid);
				dao.deleteFakeBoardContent(id);
				forward.setRedirect(true);
				forward.setPath("tipboard.tb?id=" + id);
				
			}else {
				forward.setPath("/tipboard.tb");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;
}
}