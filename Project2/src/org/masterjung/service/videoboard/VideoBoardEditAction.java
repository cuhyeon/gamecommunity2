package org.masterjung.service.videoboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardDetailDto;

public class VideoBoardEditAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {

		
		
		Actionforward forward = null;
		HttpSession session = request.getSession();

		try {
			BoardDao dao = new BoardDao();
			String fid = request.getParameter("id");
			int id = Integer.parseInt(fid);
			BoardDetailDto boardDetailDto = dao.getBoardDetailById(id);

			forward = new Actionforward();
			forward.setRedirect(false);

			if (session.getAttribute("user_auth") == null) {
				forward.setPath("/video.vb");
			}
			if ((boardDetailDto.getNick_name() + "").equals((session.getAttribute("nick_name") + ""))
					|| session.getAttribute("user_auth").equals(3) || session.getAttribute("user_auth").equals(2)) {
				request.setAttribute("result", boardDetailDto);
				forward.setPath("/WEB-INF/videoboard/videoedit.jsp");

			} else {
				forward.setPath("/video.vb");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
		
	}

}
