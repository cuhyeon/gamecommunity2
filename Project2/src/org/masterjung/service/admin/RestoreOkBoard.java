package org.masterjung.service.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.AdminDao;

public class RestoreOkBoard implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = new Actionforward();
		AdminDao dao = new AdminDao();
		try {
			int result = dao.restoreBoard(Integer.parseInt(request.getParameter("id")));
			
			forward.setPath("/deleteBoardList.ad");
		} catch (NumberFormatException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return forward;
	}
	
}
