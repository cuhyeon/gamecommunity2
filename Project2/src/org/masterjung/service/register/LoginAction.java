package org.masterjung.service.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.MainDao;
import org.masterjung.dto.UserDto;

public class LoginAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = new Actionforward();
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDto userdto = new UserDto(email, password);
		MainDao maindao = new MainDao();
		int result = maindao.isMember(userdto);
		PrintWriter  out;
		if (result == 1) {
			
			try {
				out = response.getWriter();		
				UserDto dto = maindao.userDetail(email);
		   		session.setAttribute("email", userdto.getEmail());
		   		session.setAttribute("nick_name", dto.getNick_name());
		   		session.setAttribute("user_image_path", dto.getUser_image_path());
		   		session.setAttribute("user_auth", dto.getUser_auth());
		   		session.setAttribute("user_name", dto.getUser_name());
			} catch (IOException e) {
				System.out.println("로그인 오류" + e.getMessage());
			}
		} else if (result == 0) {

			try {
				response.setContentType("text/html; charset = utf-8");
				out = response.getWriter();	
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸습니다'); location.href='index.reg'; ");
				out.println("</script>");
				out.flush();
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		} else if (result == -1) {
			try {
				out = response.getWriter();	
				out.println("<script>");
				out.println("alert('없는아이디 입니다'); location.href='index.reg'; ");
				out.println("</script>");
				out.flush();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		} else if (result == 2) {
			try {
				out = response.getWriter();	
				out.println("<script>");
				out.println("alert('DB에 문제가 있습니다'); location.href='index.reg'; ");
				out.println("</script>");
				out.flush();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		forward.setRedirect(true);
		forward.setPath("index.reg");
		return forward;
	}

}
