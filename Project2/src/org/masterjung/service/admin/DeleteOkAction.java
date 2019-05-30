package org.masterjung.service.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.AdminDao;

public class DeleteOkAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = new Actionforward();
		AdminDao dao = new AdminDao();
		int result = 0 ;
		try {
			result = dao.deleteUser(Integer.parseInt(request.getParameter("id")));
			forward.setPath("/admin.ad");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;
	}

}
