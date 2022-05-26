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
import de.dbae.wui.Alert;
import de.dbae.wui.Message;

/**
 * Handles the Rename Process of a specific {@link Kurs}.
 * 
 * @author Maxim Shulyatev
 * 
 */
@WebServlet("/RenameCourse")
public class RenameCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RenameCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Replaces the name of the specific {@link Kurs} in the Database. The {@link Kurs} is identified 
	 * by its unique ID. The {@link Kurs} Object is equally renamed in the "courseList" session Attribute.
	 * A message opens up accordingly.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("course");
		
		if(KursOperation.updateKursNameInDatabase(name, id)){
			request.setAttribute("message", new  Message(Alert.SUCCESS, "Kurs wurde umbennant."));
		} else{
			request.setAttribute("message", new  Message(Alert.DANGER, "Es is ein Fehler aufgetreten."));
		}
		// Name aendern (Referenz)
		List<Kurs> kurse = (List<Kurs>) request.getSession().getAttribute("courseList");
		
		for (Kurs kurs : kurse) {
			if(kurs.getKursId().equals(id)){
				kurs.setName(name);
			}
		}
		
		request.getRequestDispatcher("courseOverview.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
