package com.cognis.app.web.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognis.app.web.utils.Utils;

public class ResponseFilter implements Filter {
	private static final String ACCESS_CONTROL_ALLOW_ORIGIN="Access-Control-Allow-Origin";
	private static final String ACCESS_CONTROL_ALLOW_HEADERS="Access-Control-Allow-Headers";
	private static final String ACCESS_CONTROL_EXPOSE_HEADERS="Access-Control-Expose-Headers";
	private static final String ASTRIC="*";
	private static final String ORIGIN="Origin, Content-Type,Accept";
	private static final String UTF="UTF-8";
	public static String REUQEST_URL=null;
    public ResponseFilter() {}

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("inside ResponseFilter");
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req=(HttpServletRequest) request;
        HttpSession session=req.getSession();
        //resp.setContentType("application/json");
        //System.out.println("inside Response: session="+session.getId());
       
        REUQEST_URL = request.getScheme() + "://" + // "http" + "://
        		request.getServerName() + // "myhost"
        		":" + // ":"
        		request.getServerPort() + // "8080"
        		((HttpServletRequest)request).getContextPath()
        ; // "lastname=Fox&age=30"
        
        resp.setCharacterEncoding(UTF);
	    resp.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, ASTRIC);
	    resp.addHeader(ACCESS_CONTROL_EXPOSE_HEADERS,ACCESS_CONTROL_ALLOW_ORIGIN);
	    resp.addHeader(ACCESS_CONTROL_ALLOW_HEADERS,ORIGIN);
//TODO below code is for development testing
      
        chain.doFilter(request, resp);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
