package com.cg.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public LoginServlets() {
        super();
    }
    public void init(ServletConfig config) throws ServletException {
	}
	public void destroy() {
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<form>");
		out.print(" <label for=\"uname\"><b>Username</b></label>");
		out.print(" <input type=\"text\" placeholder=\"Enter Username\" name=\"uname\" required><br>");
		out.print("<label for=\"psw\"><b>Password</b></label>");
		out.print("<input type=\"password\" placeholder=\"Enter Password\" name=\"psw\" required>");
		out.print("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String psw = request.getParameter("psw");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		if(uname.equals("Dog") && psw.equals("God"))
			out.print("<font color=\"green\">Access granted</font>");
		else
			out.print("<font color=\"red\">Access denied</font>");
		out.print("</body></html>");
	}
}