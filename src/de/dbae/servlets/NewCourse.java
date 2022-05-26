package de.dbae.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.dbae.administration.Kurs;
import de.dbae.sql.KursOperation;
import de.dbae.utilities.SecurityUtilities;
import de.dbae.wui.Alert;
import de.dbae.wui.Message;

/**
 * This Servlet handles the Creation of a new {@link Kurs}.
 * 
 * @author Jonathan Lochmann
 * 
 */
@WebServlet("/NewCourse")
public class NewCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * The Servlet receives the name of the new {@link Kurs}, generates a Random ID and uses both to create
	 * a new Course Object which is then inserted into the Database. The new Course is add to the "courseList" 
	 * session Attribute and a message is also set accordingly.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("course");
		
		String kursId = SecurityUtilities.generateKeyID();
		
		Kurs kurs = new Kurs(name, kursId);
		
		if(KursOperation.insertKursIntoDatabase(kurs)){
			request.setAttribute("message", new Message(Alert.SUCCESS, "Der Kurs wurde erstellt."));
		} else{
			request.setAttribute("message", new Message(Alert.WARNING, "Der Kurs konnte nicht erstellt werden."));
		}
		
		((List<Kurs>)request.getSession().getAttribute("courseList")).add(kurs);
		request.getRequestDispatcher("courseOverview.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
