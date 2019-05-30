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


@WebServlet("/qareply.qajax")
public class QaReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public QaReply() {}
    
    
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
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("<div id='qa_boarder'></div>");
    	buffer.append("<div class='card'>");
    	buffer.append("<div class='row no-gutters' id='qa_no_gutters'>");
    	buffer.append("<div class='col-2 clearfix' id='qa_detail_reply_div'>");
    	buffer.append("<img src='"+user_image_path+"' class='card-img rounded-circle border' alt=' ' id='qa_detail_reply_img'></div>");
    	buffer.append("<div class='col-10' id='qa_detail_reply_div_content'>");
    	buffer.append("<div class='text-left'>&nbsp;<i class='far fa-user'></i> "+r_nick_name);
    	buffer.append("<c:if test=‘${qa.r_nick_name eq login_name}’>&nbsp;<span style='color:blue'><b>[글쓴이]</b></span></c:if></div>");
    	buffer.append("<div class='text-left'>&nbsp;<i class='far fa-clock'></i> "+currentTime+" </div>");
    	buffer.append("&nbsp;<button type='button' class='btn btn-primary btn-xs'>수정</button>&nbsp;");
    	buffer.append("<button type='button' class='btn btn-secondary btn-xs'>삭제</button></div></div>");
    	buffer.append("<div id='qa_boarder'></div><div class='row'>");
    	buffer.append("<div class='card-body text-dark'>"+reply_content+"</div></div></div>");   
    	out.print(buffer.toString());
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
