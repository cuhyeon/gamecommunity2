package org.masterjung.service.admin;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.AdminDao;
import org.masterjung.dto.UserDto;
import org.masterjung.util.Paging;

public class WithdrawUserList implements Action{

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = new Actionforward();
		AdminDao dao = new AdminDao();
		HttpSession session = request.getSession();
		int totalCount=0;
		try {
			totalCount = dao.getWithdrawUserCount();
		} catch (SQLException e1) {

		}
		int page = request.getParameter("page")==null ? 1 : Integer.parseInt(request.getParameter("page"));
		
		Paging paging = new Paging();
	    paging.setPageNo(page); //get방식의 parameter값으로 반은 page변수, 현재 페이지 번호
	    paging.setPageSize(10); // 한페이지에 불러낼 게시물의 개수 지정
	    paging.setTotalCount(totalCount);

	    page = (page - 1) * 10;
		List<UserDto> dto = dao.getUserList(page,paging.getPageSize());
		List<UserDto> dto2 = dao.getWithdrawUserList(page,paging.getPageSize());
		


	    
		request.setAttribute("userList", dto);
		request.setAttribute("withdrawUserList", dto2);
		request.setAttribute("paging", paging);

		try {
			request.setAttribute("userCount",dao.getUserCount());
			request.setAttribute("withdrawUserCount",dao.getWithdrawUserCount());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	
		if(session.getAttribute("user_auth")==null || !(session.getAttribute("user_auth").equals(3))) {
			forward.setPath("/WEB-INF/page_404_error.jsp");
		}else {
			forward.setPath("/WEB-INF/admin/adminwithdrawuser.jsp?page="+page);
		}
		return forward;
	}
}
