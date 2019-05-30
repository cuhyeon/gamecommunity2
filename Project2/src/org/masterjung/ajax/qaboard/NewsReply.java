package org.masterjung.ajax.qaboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.dao.BoardDao;
import org.masterjung.dao.newsboard.NewsDAO;
import org.masterjung.dto.ReplyDto;
import org.masterjung.util.SqlDate;


@WebServlet("*.najax")
public class NewsReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NewsReply() {}
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NamingException {  
    	response.setContentType("text/html;charset=UTF-8");
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	
    	String url_Command = requestURI.substring(contextPath.length());
    	if(url_Command.equals("/newsreply.najax")) {
	    	Timestamp currentTime = new SqlDate().currentTime();
	    	HttpSession sesstion = request.getSession(true);
	    	String reply_content = request.getParameter("content");
	    	String r_nick_name = (String) sesstion.getAttribute("nick_name");
	    	int board_id= Integer.parseInt(request.getParameter("board_id"));
	    	String nick_name = request.getParameter("id");
	    	ReplyDto replydto = new ReplyDto(board_id, reply_content, r_nick_name, 0, 0, 0);
	    	int resultrow = new BoardDao().createReply(replydto);
	    	NewsDAO dao = null;
	    	int reply_id = 0;

	    	String page = request.getParameter("page");
	    		dao = new NewsDAO();
	    		reply_id=dao.ReturnReplyId();
	    		System.out.println(reply_id);

	    	PrintWriter out = response.getWriter();
	    	StringBuffer buffer = new StringBuffer();
	    	buffer.append("<b>"+r_nick_name+"</b>&nbsp;<i>"+currentTime+"</i><br>");
	    	buffer.append("<p id='reply-content'>"+reply_content+"<c:if test='"+sesstion.getAttribute("nick_name")+" == "+nick_name+"}'><p>");
	    	buffer.append("<a class ='update' href='deletereply.nb?id="+ reply_id +"&boardid="+board_id+"&page="+page+"'>[삭제]</a></p></c:if></p>");
	   
	    	out.print(buffer.toString());
    	}
    	else if(url_Command.equals("/newsreplydelete.najax")) {
    		
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			doProcess(request, response);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
