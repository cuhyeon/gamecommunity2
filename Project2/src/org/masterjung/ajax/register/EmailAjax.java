package org.masterjung.ajax.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.dao.MainDao;

@WebServlet("/email.reg")
public class EmailAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EmailAjax() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String isemailckecked= null;
		
		String emailchk = request.getParameter("emailchk");
		
		MainDao md = new MainDao();
		isemailckecked = md.isCheckByEmail (emailchk);
		
		System.out.println(isemailckecked);
		out.print(isemailckecked); //true or false
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
