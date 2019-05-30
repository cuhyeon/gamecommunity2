package org.masterjung.service.tipboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.util.GetIp;

public class TipBoardWriteAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("editor1");
		HttpSession httpSession = request.getSession(true);
		String email = (String)httpSession.getAttribute("email");
		BoardDao dao = new BoardDao();
		String nick_name=dao.findNickNameByEmail(email);
		GetIp getip = new GetIp();
		String ip = getip.getIp();
		
		BoardDto dto = new BoardDto(2,nick_name,ip,title,content);
		dao.addBoardContent(dto);
		
		Actionforward forward = new Actionforward();
		forward.setRedirect(true);
		forward.setPath("tipboard.tb");
	
		return forward;
	}

}
