package org.masterjung.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Actionforward;


@WebServlet("*.home")
public class HomeFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HomeFrontController() {}

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Actionforward forward = null;
    	
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String uriCommand = requestURI.substring(contextPath.length());
    	System.out.println(uriCommand);
  
    	if(uriCommand.equals("/index.home")) {
    		forward = new Actionforward();
    		forward.setRedirect(false);
    		forward.setPath("/index.reg");
    		
    	}else {
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
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
}