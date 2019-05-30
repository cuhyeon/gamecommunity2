package org.masterjung.ajax.qaboard;

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


@WebServlet("/vbreply.vbajax")
public class VbReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public VbReply() {}
    
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	Timestamp currentTime = new SqlDate().currentTime();
    	HttpSession sesstion = request.getSession(true);
    	String user_image_path = (String) sesstion.getAttribute("user_image_path");
    	String reply_content = request.getParameter("editor1");
    	String r_nick_name = (String) sesstion.getAttribute("nick_name");
    	int reply_id =  Integer.parseInt(request.getParameter("reply_id"));
    	
    	ReplyDto replydto = new ReplyDto(reply_id, reply_content, r_nick_name, 0, 0, 0);
    	int resultrow = new BoardDao().createReply(replydto);
    	   	
    	
    	PrintWriter out = response.getWriter();
    	StringBuffer buffer2 = new StringBuffer();

    	buffer2.append("<div class='row'><div class='col-1'><div class='text-left'><div>");
    	buffer2.append("<img src='"+user_image_path+"' style=\"border-radius: 100px;\"width=\"61\" height=\"61\" alt=' '></div>");
    	buffer2.append("</div></div><div class=\"col-11\"><div class = \"row\">");
    	buffer2.append("<div style=\"font-family: '맑은고딕'; font-size: 15px;\">&emsp;"+r_nick_name+"</div>");
    	buffer2.append("<div style=\"font-family: '맑은고딕'; font-size: 13px; color: #505050;\">&emsp;"+currentTime+"</div></div>");
    	buffer2.append("<br><div>&emsp;"+reply_content+"</div></div></div><br>");

    	out.print(buffer2.toString());
 

    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
