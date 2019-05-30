package org.masterjung.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.service.register.JoinAction;
import org.masterjung.service.register.LoginAction;



@WebServlet("*.cht")
public class ChartFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChartFrontController() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) {
    	response.setContentType("text/html;charset=UTF-8");
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	Actionforward forward = null;
    	Action action = null;
    	
    	try {
    		if(url_Command.equals("/gochart.cht")){
    			forward = new Actionforward();
        		forward.setRedirect(false);
        		forward.setPath("/WEB-INF/statistics/chart.jsp");
    		}
    		
        	if(forward != null) {
        		if(forward.isRedirect()) { //true
        			response.sendRedirect(forward.getPath());
        		}else {
        			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
        			dis.forward(request, response);
        			}
        	}
    	}catch(Exception e) {
    		
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
