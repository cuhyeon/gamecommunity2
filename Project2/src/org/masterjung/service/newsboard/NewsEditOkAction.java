package org.masterjung.service.newsboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dao.newsboard.NewsDAO;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardDetailDto;

public class NewsEditOkAction implements Action{

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
	   	response.setContentType("text/html;charset=UTF-8");
	   	BoardDao boardDao=new BoardDao();
	   	BoardDetailDto boardDto = new BoardDetailDto();
	   	boardDto = boardDao.getBoardDetailById(Integer.parseInt(request.getParameter("id")));

	   		Actionforward forward = null;
			String realFileName = "";
			String fileName2 = "";
			String fileName = "";
	    	try {
				Part part = request.getPart("upload");
				if (part.getHeader("Content-Disposition").contains("filename=")) {
					realFileName = part.getSubmittedFileName();
					 
					fileName2="uploads/"+realFileName;
					fileName = "/uploads/"+realFileName;

					if (part.getSize() > 0) {
						part.write(request.getSession().getServletContext().getRealPath(fileName));
						part.delete();
					}
				}	
	    		
				forward=new Actionforward();
				NewsDAO dao = new NewsDAO();
				
				boardDto.setBoard_list_id(3);
				boardDto.setNick_name(request.getParameter("nick_name"));
				boardDto.setTitle(request.getParameter("title"));
				boardDto.setContent(request.getParameter("editor1"));
				if (realFileName != null && !realFileName.equals("")) {
		    		boardDto.setFile_path(fileName2);
				}
				int result = dao.UpdateBoard(boardDto);
				
				forward.setPath("/newboard.nb");
				
				
	    	}catch(Exception e){

	    	}
			return forward;
	}
	
}
