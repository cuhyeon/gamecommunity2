package org.masterjung.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.masterjung.util.VisitGetIp;


@WebFilter("/*")
public class GetIpFilter implements Filter {

    public GetIpFilter() {}
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		VisitGetIp gc = new VisitGetIp();
//		String visitIp =  gc.getClientIP(req);
//		System.out.println("visitIp : " +visitIp);
//		System.out.println("request : " + Inet4Address.getLocalHost().getHostAddress());
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}
}
/*
 		HttpServletRequest req = (HttpServletRequest) request;
		VistGetIp gc = new VistGetIp();
		
		String visitIp =  gc.getClientIP(req);
		System.out.println(visitIp);
		chain.doFilter(request, response);
 */