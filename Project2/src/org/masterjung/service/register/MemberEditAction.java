package org.masterjung.service.register;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.MainDao;
import org.masterjung.dto.UserDto;

public class MemberEditAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Actionforward forward = null;
		String fileName = "";
		String fileName2 = "";
		String realFileName = "";
		int pathnum = 0;
		try {
			Part part = request.getPart("upload");
			if (part.getHeader("Content-Disposition").contains("filename=")) {
				realFileName = part.getSubmittedFileName();				 
				fileName2="upload/"+realFileName;
				fileName = "/upload/"+realFileName;

				if (part.getSize() > 0) {
					part.write(request.getSession().getServletContext().getRealPath(fileName));
					part.delete();
				}
			}	
			 
			forward = new Actionforward();
			MainDao md = new MainDao();
			UserDto ud = new UserDto();
			String path = request.getParameter("hpath");
			ud.setEmail(request.getParameter("email"));
			ud.setNick_name(request.getParameter("nick_name"));
			ud.setPassword(request.getParameter("password"));
			ud.setPhone_number(Integer.parseInt(request.getParameter("phone_number")));
			ud.setUser_name(request.getParameter("user_name"));
			
			if(request.getParameter("user_address") != null) {
				ud.setUser_address(request.getParameter("user_address"));
			}
			
			
			ud.setUser_auth(Integer.parseInt(request.getParameter("user_auth")));
			
			if (fileName2.equals("upload/")) {
				ud.setUser_image_path(path);
				pathnum = 1;
			} else if (fileName2.equals(path)) {
				ud.setUser_image_path(path);
				pathnum = 1;
			} else {
				ud.setUser_image_path(fileName2);
				pathnum = 2;
			}
			
			int result = md.editUser(ud, pathnum);
			
			
			session.setAttribute("email", ud.getEmail());
	   		session.setAttribute("nick_name", ud.getNick_name());
	   		
	   		session.setAttribute("user_auth", ud.getUser_auth());
	   		session.setAttribute("user_name", ud.getUser_name());
			
	   		if(result == 2) {
	   			session.setAttribute("user_image_path", ud.getUser_image_path());
	   		}
	   		
	   		if(session.getAttribute("user_auth")==null) {
				forward.setPath("/WEB-INF/page_404_error.jsp");
			}else {
				forward.setRedirect(false);
		   		forward.setPath("/index.reg");
			}
	   		
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;
	}

}
