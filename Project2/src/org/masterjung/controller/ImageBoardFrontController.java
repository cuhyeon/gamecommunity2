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
import org.masterjung.service.imageboard.ImageBoardAction;
import org.masterjung.service.imageboard.ImageBoardDeleteAction;
import org.masterjung.service.imageboard.ImageDetailAction;
import org.masterjung.service.imageboard.ImageEditAction;
import org.masterjung.service.imageboard.ImageEditOkAction;
import org.masterjung.service.imageboard.ImageWriteOkAction;

@WebServlet("*.ib")
public class ImageBoardFrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public ImageBoardFrontController() {
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		Actionforward forward = null; // view 페이지
		Action action = null; // 액션처리

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String uriCommand = requestURI.substring(contextPath.length());

		if (uriCommand.equals("/imageboard.ib")) { // 이미지 카테고리로 이동
			action = new ImageBoardAction();
			forward = action.execute(request, response);

		}else if(uriCommand.equals("/imagewrite.ib")){
		           forward = new Actionforward();
		           forward.setRedirect(false);
		           HttpSession session = request.getSession();
		           System.out.println("/////"+session.getAttribute("user_auth"));
		           if(session.getAttribute("user_auth") != null) {
		                if((session.getAttribute("user_auth").equals(1)) || (session.getAttribute("user_auth").equals(3))) {
		                    forward.setPath("/WEB-INF/imageboard/imagewrite.jsp");
		                    
		                }else {              
		                    forward.setPath("/imageboard.ib");
		                }
		           }else {
		               forward.setPath("/imageboard.ib");
		           }

		} else if (uriCommand.equals("/imagewriteok.ib")) { // 글쓰기 버튼 클릭
			action = new ImageWriteOkAction();
			forward = action.execute(request, response);

		} else if (uriCommand.equals("/imageedit.ib")) { // 게시물 수정 화면으로 이동
			action = new ImageEditAction();
			forward = action.execute(request, response);

		} else if (uriCommand.equals("/imageeditok.ib")) { // 게시물 수정 완료 버튼 클릭
			action = new ImageEditOkAction();
			forward = action.execute(request, response);
			
		} else if (uriCommand.equals("/imagedetail.ib")) { // 게시물 상세보기(클릭)
			action = new ImageDetailAction();
			forward = action.execute(request, response);

		} else if (uriCommand.equals("/imagedelete.ib")) { // 게시물 삭제 버튼 클릭
			action = new ImageBoardDeleteAction();
			forward = action.execute(request, response);
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
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