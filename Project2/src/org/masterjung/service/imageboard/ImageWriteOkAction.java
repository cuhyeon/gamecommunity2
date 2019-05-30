package org.masterjung.service.imageboard;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dao.newsboard.NewsDAO;
import org.masterjung.dto.BoardDto;
import org.masterjung.util.GetIp;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ImageWriteOkAction implements Action {
	
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
		realfolder = "C:\\FrontendBackend\\WebJSP\\JSPLab\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\GameCommunityMVC\\upload";
		
		 try {
			 request.setCharacterEncoding("UTF-8");
			 MultipartRequest multi = new MultipartRequest(request, realfolder, maxSize, utf8, 
				 				  new DefaultFileRenamePolicy()); //파일이름 변경
		 
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
			 
			 dao.addBoardContent(dto);		 
		
			 forward.setRedirect(true);
			 forward.setPath("imageboard.ib");
	 	        
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
		return forward;
	}
}


