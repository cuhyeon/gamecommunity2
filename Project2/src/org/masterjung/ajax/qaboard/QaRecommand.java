package org.masterjung.ajax.qaboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.dao.BoardDao;


@WebServlet("/QaRecommand.qajax")
public class QaRecommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public QaRecommand() {}
    
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	BoardDao dao = new BoardDao();
    	PrintWriter out = response.getWriter();
    	Boolean bool = null;
    	int result = 0;
    	HttpSession sesstion = request.getSession(true);
    	String content_voter = (String) sesstion.getAttribute("nick_name");
    	int boardid =  Integer.parseInt(request.getParameter("reply_id"));
    	String like = request.getParameter("like");
    	
    	
    	bool = dao.isCheckVoter(content_voter, boardid);
    	if(bool) {
    		out.print(result); //0
    	}else {
    		int totalCount = dao.count_ContentVote(boardid);
    		dao.insertVoteCount(boardid, content_voter);
    		result = like.equals("like")?  totalCount +1 : totalCount-1;
    		out.print(result);
    		
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
