package org.masterjung.service.newsboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardReplyDto;
import org.masterjung.util.Paging;
import org.masterjung.dao.BoardDao;
import org.masterjung.dao.newsboard.NewsDAO;



public class MoveNewsBoardAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		Actionforward forward = null;
    	try {
    		forward = new Actionforward();
    		BoardDao dao = new BoardDao();
    		NewsDAO dao2 = new NewsDAO();
    		int totalCount = dao2.getTotalCount();
    		int page = request.getParameter("page")==null ? 1 : Integer.parseInt(request.getParameter("page"));
    		
    		Paging paging = new Paging();
    	    paging.setPageNo(page); //get방식의 parameter값으로 반은 page변수, 현재 페이지 번호
    	    paging.setPageSize(10); // 한페이지에 불러낼 게시물의 개수 지정
    	    paging.setTotalCount(totalCount);

    	    page = (page - 1) * 10;
    	    List<BoardReplyDto> boardList = null;
  		  	boardList = dao.getContentList(3, page, paging.getPageSize());
  		  	request.setAttribute("boardList",boardList);
  		  	request.setAttribute("paging", paging);
  		  	forward.setRedirect(false);
  		  	forward.setPath("/WEB-INF/newsboard/newsMain.jsp?page="+request.getParameter("page"));
    	}catch(Exception e){
    	}
		return forward;
	}

}
