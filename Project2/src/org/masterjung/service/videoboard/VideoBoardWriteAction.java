package org.masterjung.service.videoboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dao.videoboard.VideoDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.util.GetIp;

public class VideoBoardWriteAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
    	
    	Actionforward forward = null;
		String fileName ="";
		String fileName2="";
		String title = request.getParameter("title");
		String content = request.getParameter("editor1");
		
		HttpSession httpSession = request.getSession(true);
		String email = (String)httpSession.getAttribute("email");		
		
	   	try {
				Part part = request.getPart("upload");
				if (part.getHeader("Content-Disposition").contains("filename=")) {
					 fileName = part.getSubmittedFileName();
					fileName2="upload/"+fileName;
					fileName = "/upload/"+fileName;
					if (part.getSize() > 0) {
						part.write(request.getSession().getServletContext().getRealPath(fileName));
						part.delete();
					}
				}	
	    		
				BoardDao dao = new BoardDao();
				
				String nick_name = dao.findNickNameByEmail(email);
				GetIp getip = new GetIp();
				String ip = getip.getIp();
				
				BoardDto dto = new BoardDto();
				
				dto.setBoard_list_id(5);
				dto.setNick_name(nick_name);
				dto.setCreate_ip(ip);
				dto.setTitle(title);
				dto.setContent(content);
	    		dto.setFile_path(fileName2);
				

	    		VideoDao dao2 = new VideoDao();

				forward = new Actionforward();
				
				
				int result = dao2.InsertBoard(dto);
				
				if(result>0) {
				}else {
				}
				
				forward.setRedirect(false);
				forward.setPath("video.vb");
				
	    	}catch(Exception e){
	    		System.out.println(e.getMessage());	
	    	}
			return forward;

		

	}

}





