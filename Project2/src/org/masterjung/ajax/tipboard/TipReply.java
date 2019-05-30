package org.masterjung.ajax.tipboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.masterjung.dao.BoardDao;
import org.masterjung.dto.ReplyDto;
import org.masterjung.util.SqlDate;


@WebServlet("/tipreply.tipax")
public class TipReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public TipReply() {}
    
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	Timestamp currentTime = new SqlDate().currentTime();
    	HttpSession sesstion = request.getSession(true);
    	String reply_content = request.getParameter("editor1");
    	String r_nick_name = (String) sesstion.getAttribute("nick_name");
    	int reply_id =  Integer.parseInt(request.getParameter("reply_id"));
    	
    	ReplyDto replydto = new ReplyDto(reply_id, reply_content, r_nick_name, 0, 0, 0);
    	int resultrow = new BoardDao().createReply(replydto);
    	   	
    	PrintWriter out = response.getWriter();
    	StringBuffer buffer = new StringBuffer();
    	 
    	buffer.append("<div class='card-body text-dark'>");
    	buffer.append("<div class='row'>");
    	buffer.append("<div class='col-10'>");
    	buffer.append("&nbsp;&nbsp;&nbsp;<i><b>작성자:</b>"+r_nick_name+"</i>");
    	buffer.append("&nbsp;&nbsp;&nbsp; <i></i>");
    	buffer.append("</div>");
    	buffer.append("<div class='col-2'>");
    	buffer.append("<i><b>작성일:"+currentTime+"</b></i>");
    	buffer.append("</div>");
    	buffer.append("</div>");
    	buffer.append("<div class='row'>");
    	buffer.append("<div class='col-12'>");
    	buffer.append("<br>"+reply_content+"</div>");
    	buffer.append("</div>");
    	buffer.append("<hr>");
    	buffer.append("</div>");    	   
    	out.print(buffer.toString());
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
