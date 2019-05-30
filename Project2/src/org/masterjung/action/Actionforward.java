package org.masterjung.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.dao.BoardDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardDto2;
import org.masterjung.util.ConvertCategoryName;
import org.masterjung.util.GetIp;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//로직 제어와 화면제어 전달 클래스
public class Actionforward implements Action {
	
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Actionforward forward = new Actionforward();
		BoardDto dto = new BoardDto();	
		BoardDao dao = new BoardDao();
		
		System.out.println("이미지 글쓰기 액션 도착");
		
		String realfolder; 				//프로젝트 내에 만들어질 폴더를 저장할 변수 선언 
		String savefolder ="/upload";   //실제 만들어질 폴더명을 선언
		String utf8 = "utf-8";		    //한글설정 
		int maxSize = 1024*1024*10; 	//저장될 파일 사이즈 설정 -- 10메가
		
		String title="";
		String content="";
		String nick_name2 ="";
		
		ServletContext context = request.getSession().getServletContext();//파일이 저장될 경로값을 읽어오는 객체 
		realfolder = context.getRealPath(savefolder);
		
		 try {			 
			 request.setCharacterEncoding("UTF-8");
			 MultipartRequest multi = new MultipartRequest(request, realfolder, maxSize, utf8, 
				 				  new DefaultFileRenamePolicy()); //파일이름 변경
		 
			 HttpSession httpSession = request.getSession(true);
			 String email = (String)httpSession.getAttribute("email");			
			 String	nick_name = dao.findNickNameByEmail(email);
			 GetIp getip = new GetIp();
			 String ip = getip.getIp();
		 		 
			 nick_name2 = multi.getParameter(nick_name);
			
			 System.out.println("제목은" + title);
			 System.out.println("작성자는" + nick_name);
			 System.out.println("내용은" + content);
			 			 
			 
			 dto.setNick_name(nick_name);
			 dto.setTitle(title);
			 dto.setContent(content);
			 dto.setCreate_ip(ip);
			 
			 dao.getBoardMain(4, 100);
			 dao.addBoardContent(dto);			
			 
			 forward.setRedirect(false);
			 forward.setPath("/imagewrite.ib");
	 	        
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
		return forward;
	}
	
	
	private boolean isRedirect = false; //화면단 제어
	private String path = null; //이동 경로
		
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}	
	
}
