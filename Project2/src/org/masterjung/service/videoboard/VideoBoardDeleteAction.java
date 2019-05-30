package org.masterjung.service.videoboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;

public class VideoBoardDeleteAction implements Action{

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = null;
		BoardDao dao = new BoardDao();
		
		
		String fid = request.getParameter("id");
		int id = Integer.parseInt(fid);
		dao.deleteFakeBoardContent(id);
		
	    forward = new Actionforward();
	    forward.setRedirect(false);
	    forward.setPath("video.vb");

		return forward;
	}

}
