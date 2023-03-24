package bj.highfive.usermanagement.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bj.highfive.usermanagement.bean.User;
import bj.highfive.usermanagement.dao.UserDAO;

/**
 * Servlet implementation class EditServlet
 */
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isPut = request.getParameter("_hidden") != null;
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("put méthode =>" + isPut);
		if(!isPut) {
			
			User u = UserDAO.getUserById(id);
			
			request.setAttribute("user", u);
			
			request.getServletContext().getRequestDispatcher("/edituser.jsp").forward(request, response);
		}
		else {
			String name = request.getParameter("uname");
			String email = request.getParameter("email");
			String country = request.getParameter("country");
			
			User user = new User(); // instanciation de la JavaBean
			
			user.setId(id);
			user.setName(name);
			user.setEmail(email);
			user.setCountry(country);
			
			boolean isupdated = UserDAO.updateUser(user);
			
			request.setAttribute("isupdated", isupdated);
			
			// Redirige sur la page d'accueil (index.jsp)
			RequestDispatcher rd = request.getRequestDispatcher("users");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Mise à jour de la requête");

	}

}
