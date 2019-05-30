package org.masterjung.service.imageboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardDetailDto;


public class ImageBoardReadAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Actionforward forward = null;
		BoardDao dao = new BoardDao();
		
		try {			  
			  request.setCharacterEncoding("UTF-8");
			  
			  int id = Integer.parseInt(request.getParameter("id"));
			  int count = Integer.parseInt(request.getParameter("count"));
			  BoardDetailDto dto = dao.getBoardDetailById(id);
  
			  request.setAttribute("result", dto);
			  request.setAttribute("count", count);
			  
			  forward = new Actionforward();
			  forward.setRedirect(false);
			  forward.setPath("/WEB-INF/imageboard/imagecontent.jsp");
		  	  
	  }catch (Exception e) {
		e.printStackTrace();
	  }	
	  
	
		return forward;
		
	}

}
