package com.cg.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String dateOfBirth = request.getParameter("dob");
		String hobbies[] = request.getParameterValues("hobbies");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<table align=\"center\" style=\"border: 1px solid black;\">");
		out.print("<tr><td>First Name: </td><td>"+firstName+"</td></tr>");
		out.print("<tr><td>Last Name: </td><td>"+lastName+"</td></tr>");
		out.print("<tr><td>Email: </td><td>"+email+"</td></tr>");
		out.print("<tr><td>Date of Birth: </td><td>"+dateOfBirth+"</td></tr>");
		out.print("<tr><td>Hobbies: </td><td>");
		for(String s: hobbies)
			out.print(s+"<br>");
		out.print("</td></tr>");
		out.print("</table>");
		out.print("<div align=\"center\"><font size=5>Registration successful</font></div>");
		out.print("</body></html>");
	}
}