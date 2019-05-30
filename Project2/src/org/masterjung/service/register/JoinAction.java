package org.masterjung.service.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.MainDao;
import org.masterjung.dto.UserDto;
import org.masterjung.util.GetImagePath;



public class JoinAction implements Action{
	

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		int total = 1;
		Actionforward forward = new Actionforward();
		HttpSession session = request.getSession();
		MainDao maindao = new MainDao();
		UserDto userdto = new UserDto();
		
		try {
			total = maindao.getTotalCount() + 1;
		} catch (SQLException e) {
			System.out.println("join 액션 오류"+e.getMessage());
		}
		
		boolean result;
		
		String path = "resource/img/default-img" + total + ".gif";
		userdto.setEmail(request.getParameter("email"));
		userdto.setPassword(request.getParameter("password"));
		userdto.setNick_name(request.getParameter("nick_name"));
		userdto.setPhone_number(Integer.parseInt(request.getParameter("phone_number")));
		userdto.setUser_name(request.getParameter("user_name"));
		userdto.setUser_image_path(path);
		String a = userdto.getEmail();
		
		
		
		result = maindao.insertMember(userdto);
		session.setAttribute("email", userdto.getEmail());
   		session.setAttribute("nick_name", userdto.getNick_name());
   		session.setAttribute("user_name", userdto.getUser_name());
   		session.setAttribute("user_auth", userdto.getUser_auth());
   		
   		GetImagePath gp = new GetImagePath();
   		String path2 = gp.imagePath(path);
   		session.setAttribute("user_image_path", path2);
		
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset = utf-8");
			out.println("<script>");
			out.println("alert('가입을 축하드립니다'); location.href='index.reg'; ");
			out.println("</script>");
			out.flush();
		} catch (IOException e) {
			System.out.println("JoinAction 오류"+e.getMessage());
		}
		
		
		//회원가입 성공.
   		forward.setRedirect(false);
   		forward.setPath("index.reg");
		return forward;
	}
	
}
