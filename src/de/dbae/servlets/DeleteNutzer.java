package de.dbae.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.dbae.administration.Nutzer;
import de.dbae.administration.Student;
import de.dbae.sql.NutzerOperation;
import de.dbae.wui.Alert;
import de.dbae.wui.Message;

/**
 * Handles the Deletion Process a specific {@link Nutzer}.
 * 
 * @author Jonathan Lochmann
 * 
 */
@WebServlet("/DeleteNutzer")
public class DeleteNutzer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteNutzer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * The Get method is used to load all {@link Student}'s from the Database and converting the Information 
	 * into a Json Object, which then is send back to the original jsp. 
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Student> studenten = NutzerOperation.loadAlleStudentenFromDatabase();

		String json = new Gson().toJson(studenten);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * The Post method Deletes the {@link Student} from the Database by the unique ID.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail = request.getParameter("id");
		
		if(NutzerOperation.deleteNutzerFromDatabase(mail, true)){
			System.out.println("Nutzer wurde geloescht.");
		} else{ 
			System.out.println("Nutzer konnte nicht geloescht werden.");
		}
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(mail);

	}

}
