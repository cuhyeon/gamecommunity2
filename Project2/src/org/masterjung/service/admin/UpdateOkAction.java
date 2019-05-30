package org.masterjung.service.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.AdminDao;
import org.masterjung.dto.UserDto;

public class UpdateOkAction implements Action{

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = new Actionforward();
		UserDto dto = new UserDto();
		AdminDao dao = new AdminDao();
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setUser_auth(Integer.parseInt(request.getParameter("user_auth")));
		int result= 0;
		try {
			result = dao.updateUser(dto);

			forward.setPath("/admin.ad");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return forward;
	}

}
