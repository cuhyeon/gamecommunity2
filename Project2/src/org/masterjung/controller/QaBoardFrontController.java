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
import org.masterjung.service.qaboard.QaBoardDeleteOkAction;
import org.masterjung.service.qaboard.QaDetailAction;
import org.masterjung.service.qaboard.QaReplyDeleteOkAction;
import org.masterjung.service.qaboard.QaWriteEditAction;
import org.masterjung.service.qaboard.QaWriteEditOkAction;
import org.masterjung.service.qaboard.QaWriteOkAction;
import org.masterjung.service.qaboard.QaboardAction;


@WebServlet("*.qb")
public class QaBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public QaBoardFrontController() {}

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Actionforward forward = null;
    	Action action = null;
    	
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String uriCommand = requestURI.substring(contextPath.length());
    	
    	if(uriCommand.equals("/qaboard.qb")) {
       		action = new QaboardAction();
    		forward = action.execute(request, response);

    	}else if(uriCommand.equals("/qawrite.qb")){
    		forward = new Actionforward();
    		forward.setRedirect(false);
    		forward.setPath("//WEB-INF/qaboard/qawrite.jsp");
    		
    	}else if(uriCommand.equals("/qawriteok.qb")){
    		action = new QaWriteOkAction();
    		forward = action.execute(request, response);
    		
    	}else if(uriCommand.equals("/qadetail.qb")){
    		action = new QaDetailAction();
    		forward = action.execute(request, response);
    	
    	}else if(uriCommand.equals("/qawriteeditok.qb")){
    		action = new QaWriteEditOkAction();
    		forward = action.execute(request, response);
    		
    	}else if(uriCommand.equals("/qawritedit.qb")){
    		action = new QaWriteEditAction();
    		forward = action.execute(request, response);
    		
    	}else if(uriCommand.equals("/qadelteok.qb")){
    		action = new QaBoardDeleteOkAction();
    		forward = action.execute(request, response);
    		
    	}else if(uriCommand.equals("/qadeletereply.qb")){
    		action = new QaReplyDeleteOkAction();
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
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
}