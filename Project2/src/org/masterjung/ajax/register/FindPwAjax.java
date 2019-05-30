package org.masterjung.ajax.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.dao.MainDao;
import org.masterjung.util.CreateRandomPassword;

@WebServlet("/pwfind.reg")
public class FindPwAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FindPwAjax() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		int result= 0;
		
		String email2 = request.getParameter("email2");
		int phone = Integer.parseInt(request.getParameter("phone_number"));
		System.out.println(email2);
		System.out.println(phone);
		MainDao md = new MainDao();
		result = md.findPw(phone, email2);
		
		System.out.println(result);
		
		if(result == 0) {
			out.print("false");
		} else {
			CreateRandomPassword cp = new CreateRandomPassword();
			String pw = cp.tempPassword();
			result = md.editPw(pw, email2);
			System.out.println("비밀번호 잘바꼇냐" + result);
			out.print(pw);
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
