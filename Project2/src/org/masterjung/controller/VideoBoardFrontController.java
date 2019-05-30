package org.masterjung.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.service.videoboard.VideoBoardAction;
import org.masterjung.service.videoboard.VideoBoardDeleteAction;
import org.masterjung.service.videoboard.VideoBoardEditAction;
import org.masterjung.service.videoboard.VideoBoardEditOkAction;
import org.masterjung.service.videoboard.VideoBoardReadAction;
import org.masterjung.service.videoboard.VideoBoardWriteAction;


@MultipartConfig(
		location = "C:\\FrontendBackend\\WebJSP\\JSPLab\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\GameCommunityMVC\\upload",
		maxFileSize = -1,
		maxRequestSize = -1,
		fileSizeThreshold = -1
)
@WebServlet("*.vb")
public class VideoBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public VideoBoardFrontController() {}
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Actionforward forward = null;
    	Action action = null;
    	
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String uriCommand = requestURI.substring(contextPath.length());
    	
    	if(uriCommand.equals("/video.vb")) { //비디오 게시판 카테고리로 이동
       		action = new VideoBoardAction();
    		forward = action.execute(request, response);
    	}else if(uriCommand.equals("/videowrite.vb")){ // 글쓰기 화면으로 이동
    		forward = new Actionforward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/videoboard/videowrite.jsp");
    		
    	}else if(uriCommand.equals("/videowriteok.vb")){ // 글쓰기 완료 버튼 클릭
       		action = new VideoBoardWriteAction();
    		forward = action.execute(request, response);
    		
    	}else if(uriCommand.equals("/videoread.vb")){ // 게시물 상세내용 확인
       		action = new VideoBoardReadAction();
    		forward = action.execute(request, response);
    		
    	}else if(uriCommand.equals("/videoedit.vb")){ // 게시물 수정 화면으로 이동
       		action = new VideoBoardEditAction();
    		forward = action.execute(request, response);
    		
    	}else if(uriCommand.equals("/videoeditok.vb")){ // 게시물 수정 완료 버튼 클릭
       		action = new VideoBoardEditOkAction();
    		forward = action.execute(request, response);
    		
    	}else if(uriCommand.equals("/videodelete.vb")){ // 게시물 삭제 버튼 클릭
       		action = new VideoBoardDeleteAction();
    		forward = action.execute(request, response);
    		
    	}
    	
    	
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
