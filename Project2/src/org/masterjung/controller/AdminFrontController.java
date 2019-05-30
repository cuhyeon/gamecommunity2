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
import org.masterjung.service.admin.AdminAction;
import org.masterjung.service.admin.DeleteAction;
import org.masterjung.service.admin.DeleteBoardListAction;
import org.masterjung.service.admin.DeleteOkAction;
import org.masterjung.service.admin.RestoreBoard;
import org.masterjung.service.admin.RestoreOkBoard;
import org.masterjung.service.admin.UpdateAction;
import org.masterjung.service.admin.UpdateOkAction;
import org.masterjung.service.admin.WithdrawUserList;

/**
 * Servlet implementation class AdminFrontController
 */
@WebServlet("*.ad")
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminFrontController() {
		super();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url_Command = requestURI.substring(contextPath.length());
		Actionforward forward = new Actionforward();
		Action action = null;

		try {
			if (url_Command.equals("/admin.ad")) {
				action = new AdminAction();
				forward = action.execute(request, response);
			}else if(url_Command.equals("/deleteUser.ad")) {
				action = new DeleteAction();
				forward = action.execute(request, response);
			}else if(url_Command.equals("/updateUser.ad")) {
				action = new UpdateAction();
				forward = action.execute(request, response);
			}else if(url_Command.equals("/usereditok.ad")) {
				action = new UpdateOkAction();
				forward = action.execute(request, response);
			}else if(url_Command.equals("/userdeleteok.ad")) {
				action = new DeleteOkAction();
				forward = action.execute(request, response);
			}else if(url_Command.equals("/deleteBoardList.ad")) {
				action = new DeleteBoardListAction();
				forward = action.execute(request, response);
			}else if(url_Command.equals("/withdraw.ad")) {
				action = new WithdrawUserList();
				forward = action.execute(request, response);
			}else if(url_Command.equals("/restore.ad")) {
				action = new RestoreBoard();
				forward = action.execute(request, response);
			}else if(url_Command.equals("/restoreOk.ad")) {
				action = new RestoreOkBoard();
				forward = action.execute(request, response);				
			}
			
			
			
			if (forward != null) {
				if (forward.isRedirect()) { // true
					response.sendRedirect(forward.getPath());
				} else {
					RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
					dis.forward(request, response);
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
