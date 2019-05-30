package org.masterjung.service.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.MainDao;
import org.masterjung.dto.UserDto;

public class MemberEditViewAction implements Action{

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = new Actionforward();
	 	
	 	HttpSession session=request.getSession();
	 	MainDao maindao = new MainDao();
		UserDto userdto = new UserDto();
   		
   		String email = (String)session.getAttribute("email");
   		
   		userdto = maindao.userDetail(email);
   		
   		request.setAttribute("userdto", userdto);
   		
   		if(session.getAttribute("user_auth")==null) {
			forward.setPath("/WEB-INF/page_404_error.jsp");
		}else {
			forward.setRedirect(false);
	   		forward.setPath("/WEB-INF/register/memberedit.jsp");
		}
   		
   		
   		return forward;
	}

}
