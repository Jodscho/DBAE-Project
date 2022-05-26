package de.dbae.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.dbae.administration.Nutzer;
import de.dbae.sql.KursOperation;
import de.dbae.sql.NutzerOperation;
import de.dbae.wui.Alert;
import de.dbae.wui.Message;

/**
 * Handles the {@link Nutzer} Login.
 * 
 * @author Jonathan Lochmann
 * 
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	/**
	 * 
	 * Creates a {@link Nutzer} Object from the Database with the given password and username.
	 * If the User chooses a wrong password or a wrong username a message pops up accordingly.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//create Nutzer Object from the users request, is null if the username and the
		//password do not match or if the username doesent exit
		Nutzer person = NutzerOperation.createNutzerObjectFromLogin(username, password);
		
		if(person != null){
			
			// set all necessary Attributes for the session
			session = request.getSession();
			// the logedInPerson Object which is used to identify the User in the Programm
			session.setAttribute("logedInPerson", person);
			session.setAttribute("username", username);
			// The List of available Courses, which is loaded from the Database
			session.setAttribute("courseList", KursOperation.loadAlleKurseFromDatabase());
			request.getRequestDispatcher("courseOverview.jsp").forward(request, response);
		} else{
			
			//show message if the Login failed
			request.setAttribute("message", new Message(Alert.DANGER, "Your Login Failed. Try again."));
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}
}
