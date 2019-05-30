package org.masterjung.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.service.qaboard.QaWriteOkAction;
import org.masterjung.service.tipboard.TipBoardAction;
import org.masterjung.service.tipboard.TipBoardDeleteOkAction;
import org.masterjung.service.tipboard.TipBoardDetailAction;
import org.masterjung.service.tipboard.TipBoardEditAction;
import org.masterjung.service.tipboard.TipBoardWriteAction;
import org.masterjung.service.tipboard.TipboardEditOkAcion;

@WebServlet("*.tb")
public class TipBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public TipBoardFrontController() {}

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Actionforward forward = null;
    	Action action = null;
    	
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String uriCommand = requestURI.substring(contextPath.length());
    	 	
    	if(uriCommand.equals("/tipboard.tb")) {
       		action = new TipBoardAction();
    		forward = action.execute(request, response);
    		
    	} else if(uriCommand.equals("/tipwrite.tb")){
    		forward = new Actionforward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/tipboard/tipboardwrite.jsp");
    	
    	} else if(uriCommand.equals("/tipboardwriteok.tb")){
    		action = new TipBoardWriteAction();
    		forward = action.execute(request, response);
        	 	
    	} else if(uriCommand.equals("/tipboarddetail.tb")){
    		action = new TipBoardDetailAction();
    		forward = action.execute(request, response);
    		
    	} else if(uriCommand.equals("/tipboardedit.tb")) {
    		action = new TipBoardEditAction();
    		forward = action.execute(request, response);
    		
    	} else if(uriCommand.equals("/tipboardeditok.tb")) {
    		action = new TipboardEditOkAcion();
    		forward = action.execute(request, response);
    		
    	} else if(uriCommand.equals("/tipdeleteok.tb")){
    		action = new TipBoardDeleteOkAction();
    		forward = action.execute(request, response);
    		
    	} else {
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