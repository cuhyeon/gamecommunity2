package org.masterjung.service.imageboard;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.util.GetIp;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ImageEditOkAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Actionforward forward = new Actionforward();
		BoardDto dto = new BoardDto();	
		BoardDao dao = new BoardDao();
		
		String realfolder; 				//프로젝트 내에 만들어질 폴더를 저장할 변수 선언 
		String savefolder ="/upload";   //실제 만들어질 폴더명을 선언
		String utf8 = "utf-8";		    //한글설정 
		int maxSize = 1024*1024*10; 	//저장될 파일 사이즈 설정 -- 10메가
		
		String title="";
		String content="";		
		
		ServletContext context = request.getSession().getServletContext();//파일이 저장될 경로값을 읽어오는 객체 
		realfolder = context.getRealPath(savefolder);
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			MultipartRequest multi = new MultipartRequest(request, realfolder, maxSize, utf8, 
					 				 new DefaultFileRenamePolicy()); //파일이름 변경
			
			String strid = multi.getParameter("id");
			int id = Integer.parseInt(strid);
			
			HttpSession httpSession = request.getSession(true);
			String email = (String)httpSession.getAttribute("email");			
			String	nick_name = dao.findNickNameByEmail(email);
			GetIp getip = new GetIp();
			String ip = getip.getIp();
			
			title = multi.getParameter("title");
			content = multi.getParameter("editor1");
			String filename = multi.getFilesystemName("fileup");
			String fullfilepath = ("upload/"+filename);
			
			dto.setBoard_list_id(4);
			dto.setNick_name(nick_name);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setCreate_ip(ip);
			dto.setFile_path(fullfilepath);
			dto.setId(id);
			 
			dao.updateBoardContent(dto);		 
		
			forward.setRedirect(true);
			forward.setPath("imagedetail.ib?id="+id);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
