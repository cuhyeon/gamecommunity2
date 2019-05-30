package org.masterjung.ajax.chart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gamerank.cht")
public class ChartAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ChartAjax() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String host = "http://steamspy.com/api.php?request=top100in2weeks";
		String host2 = "http://api.gevolution.co.kr/rank/xml/?aCode=VWXY134032&market=g&appType=game&rankType=1&rank=100";
		URL url;
		String inputLine;
	    String buffer = "";
		
		
		try {
			url = new URL(host);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("CONTENT-TYPE","text/html");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
		    
			while ((inputLine = in.readLine()) != null){
				buffer += inputLine.trim();
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
//		System.out.println(buffer);
		out.print(buffer);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
