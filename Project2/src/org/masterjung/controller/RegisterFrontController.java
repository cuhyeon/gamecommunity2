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
import org.masterjung.service.register.IndexAction;
import org.masterjung.service.register.JoinAction;
import org.masterjung.service.register.LoginAction;
import org.masterjung.service.register.LogoutAction;
import org.masterjung.service.register.MemberEditAction;
import org.masterjung.service.register.MemberEditViewAction;
import org.masterjung.service.register.MemberinfoAction;

@MultipartConfig(
        location = "C:\\FrontendBackend\\WebJSP\\JSPLab\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\GameCommunityMVC\\upload",
        maxFileSize = -1,
        maxRequestSize = -1,
        fileSizeThreshold = -1
)

@WebServlet("*.reg")
public class RegisterFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RegisterFrontController() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	Actionforward forward = null;
    	Action action = null;
    	
    	try {
    		if(url_Command.equals("/login.reg")){
        		action = new LoginAction();
        		forward = action.execute(request, response);
        		
    		}else if(url_Command.equals("/registerform.reg")) {
        		forward = new Actionforward();
        		forward.setRedirect(false);
        		forward.setPath("/WEB-INF/register/register.jsp");
        		
    		}else if(url_Command.equals("/index.reg")) {
        		action = new IndexAction();
        		forward = action.execute(request, response);
        		
    		}else if(url_Command.equals("/register.reg")) {
    			action = new JoinAction();
        		forward = action.execute(request, response);
        		
    		}else if(url_Command.equals("/memberinfo.reg")) {
    			action = new MemberinfoAction();
        		forward = action.execute(request, response);
        		
    		}else if(url_Command.equals("/membereditview.reg")) {
    			action = new MemberEditViewAction();
        		forward = action.execute(request, response);
        		
    		}else if(url_Command.equals("/memberedit.reg")) {
    			action = new MemberEditAction();
        		forward = action.execute(request, response);
        		
    		}else if(url_Command.equals("/logout.reg")) {
    			action = new LogoutAction();
        		forward = action.execute(request, response);
        		
    		}else{
           		forward = new Actionforward();
        		forward.setRedirect(false);
        		forward.setPath("/WEB-INF/page_404_error.jsp");
        	}
    		
        	if(forward != null) {
        		if(forward.isRedirect()) {
        			response.sendRedirect(forward.getPath());
        		}else {
        			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
        			dis.forward(request, response);
        			}
        	}
    	}catch(Exception e) {
    		System.out.println("레지스터 컨트롤러 오류:" + e.getMessage());
    	}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
