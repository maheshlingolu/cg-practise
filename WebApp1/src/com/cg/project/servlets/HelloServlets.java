package com.cg.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloServlets() {
        super();
    }
	public void init(ServletConfig config) throws ServletException {
	}
	public void destroy() {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.print("<html><body>");
		out.print("<div><font color=red size=8>Hello there! General Kenobi!</font></div>");
		out.print("</body></html>");
	}
}