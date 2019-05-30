package org.masterjung.ajax.chart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.dao.BoardDao;

@WebServlet("/ChartIndexAjax.reg")
public class ChartIndexAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ChartIndexAjax() {super();}

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	BoardDao dao = new BoardDao(); 
		String json = dao.getCountRegister();
		out.print(json);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request, response);}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request, response);}

}
