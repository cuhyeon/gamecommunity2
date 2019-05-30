package org.masterjung.service.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.AdminDao;
import org.masterjung.dto.UserDto;

public class UpdateAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = new Actionforward();
		HttpSession session = request.getSession();
		
		AdminDao dao = new AdminDao();
		UserDto dto = dao.getUserDetailById(Integer.parseInt(request.getParameter("id")));
		if(session.getAttribute("user_auth")!=null && session.getAttribute("user_auth").equals(3)) {
			request.setAttribute("userdto", dto);
			forward.setPath("/WEB-INF/admin/adminedit.jsp");
		}else {
			forward.setPath("/WEB-INF/page_404_error.jsp");
		}
		return forward;
	}

}
