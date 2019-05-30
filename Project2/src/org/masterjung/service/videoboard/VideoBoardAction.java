package org.masterjung.service.videoboard;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dao.newsboard.NewsDAO;
import org.masterjung.dao.videoboard.VideoDao;
import org.masterjung.dto.BoardDto;
import org.masterjung.util.Paging;

public class VideoBoardAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {

		VideoDao dao = new VideoDao(); 
		NewsDAO dao2=null;
		try {
			dao2 = new NewsDAO();
		} catch (NamingException e2) {
		}
		List<BoardDto> videolist;
		Actionforward forward = new Actionforward();
		int totalCount=0;
		try {
			totalCount = dao2.getTotalCountVideo();
		} catch (SQLException e1) {
		}
		int page = request.getParameter("page")==null ? 1 : Integer.parseInt(request.getParameter("page"));
		
		Paging paging = new Paging();
	    paging.setPageNo(page); //get방식의 parameter값으로 반은 page변수, 현재 페이지 번호
	    paging.setPageSize(5); // 한페이지에 불러낼 게시물의 개수 지정
	    paging.setTotalCount(totalCount);
	    page = (page - 1) * 5;

		try {
			
			videolist = dao.getVideoList(page, paging.getPageSize());
			
			request.setAttribute("videolist", videolist);
			request.setAttribute("paging", paging);

			forward.setRedirect(false);
			forward.setPath("/WEB-INF/videoboard/videoboard.jsp");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return forward;

	}
}
