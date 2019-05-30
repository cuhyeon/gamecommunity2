package org.masterjung.service.videoboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.ReplyDto;

public class VideoBaordReplyWriteAction implements Action{

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = new Actionforward();
		BoardDao dao = new BoardDao();
		ReplyDto dto = new ReplyDto();
		HttpSession session = request.getSession();
		
		dto.setReply_id(Integer.parseInt(request.getParameter("id")));
		dto.setReply_content(request.getParameter("content"));
		dto.setR_nick_name(""+session.getAttribute("nick_name"));
		dto.setRefer(1);
		dto.setStep(1);
		dto.setDepth(1);
		

		String str = "";

		int result = dao.createReply(dto);
		if(result>0) {
		}else {
		}
		forward.setPath("/newsDetail.nb?id="+request.getParameter("id"));
	
		return forward;
	}

}
