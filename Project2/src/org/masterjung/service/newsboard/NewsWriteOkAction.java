package org.masterjung.service.newsboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.newsboard.NewsDAO;
import org.masterjung.dto.BoardDto;

public class NewsWriteOkAction implements Action {

	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
    	response.setContentType("text/html;charset=UTF-8");
    	
		Actionforward forward = null;
		String fileName ="";
		String fileName2="";
		
    	try {
			Part part = request.getPart("upload");
			if (part.getHeader("Content-Disposition").contains("filename=")) {
				 fileName = part.getSubmittedFileName();
				 
				fileName2="uploads/"+fileName;
				fileName = "/uploads/"+fileName;
				if (part.getSize() > 0) {
					part.write(request.getSession().getServletContext().getRealPath(fileName));
					part.delete();
				}
			}	
    		
			forward=new Actionforward();
			NewsDAO dao = new NewsDAO();
			BoardDto boardDto = new BoardDto();
			boardDto.setBoard_list_id(3);
			boardDto.setNick_name(request.getParameter("nick_name"));
			boardDto.setTitle(request.getParameter("title"));
			boardDto.setContent(request.getParameter("editor1"));
    		boardDto.setFile_path(fileName2);
			int result = dao.InsertBoard(boardDto);

			forward.setRedirect(true);
			forward.setPath("newboard.nb");
    	}catch(Exception e){
    	}
		return forward;
	}
}
