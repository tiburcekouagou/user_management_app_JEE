package bj.highfive.usermanagement.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bj.highfive.usermanagement.bean.User;
import bj.highfive.usermanagement.dao.UserDAO;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("uname");
		String email = request.getParameter("email");
		String country = request.getParameter("country");

		User user = new User(); // instanciation de la JavaBean
		
		user.setName(name);
		user.setEmail(email);
		user.setCountry(country);
		
		boolean iscreated = UserDAO.createUser(user);
		
		request.setAttribute("iscreated", iscreated);
		
		// Redirige sur la page d'accueil (index.jsp)
		RequestDispatcher rd = request.getRequestDispatcher("users");
		rd.forward(request, response);
	}

}
