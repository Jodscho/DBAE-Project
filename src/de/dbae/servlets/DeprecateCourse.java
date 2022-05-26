package de.dbae.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.dbae.administration.Deprecated_Kurs;
import de.dbae.administration.Kurs;
import de.dbae.administration.Nutzer;
import de.dbae.sql.KursOperation;
import de.dbae.wui.Alert;
import de.dbae.wui.Message;

/**
 * This Servlet changes a specifc {@link Kurs} to a {@link Deprecated_Kurs} or removes the Deprecation
 * from the Course.
 * 
 * @author Jonathan Lochmann
 * 
 */
@WebServlet("/DeprecateCourse")
public class DeprecateCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeprecateCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * Marks Course as Deprecated by adding a Reason and the Person who marked it into the Database under the specific 
	 * Course ID. Updates the session Attribute "courseList" and sets the message Attribute accordingly.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String grund = request.getParameter("grund");
		String marker = ((Nutzer) request.getSession().getAttribute("logedInPerson")).getMail();
		
		
		if(KursOperation.insertDeprecatedKursIntoDatabase(id, marker, grund)){
			
			List<Kurs> kurse = (List<Kurs>) request.getSession().getAttribute("courseList");
			Kurs kursChange = null;
			int index = 0;
			
			for (Kurs kurs : kurse) {
				if(kurs.getKursId().equals(id)){
					index = kurse.indexOf(kurs);
					kursChange = kurs;
				}
			}
			
			kurse.remove(kursChange);
			kurse.add(index, new Deprecated_Kurs(kursChange.getName(), id, marker, grund));
			
			request.setAttribute("message", new  Message(Alert.SUCCESS, "Kurs wurde markiert."));
		} else{
			request.setAttribute("message", new  Message(Alert.DANGER, "Es is ein Fehler aufgetreten."));
		}
	
		request.getRequestDispatcher("courseOverview.jsp").forward(request, response);
	}

	/**
	 * 
	 * Removes the Deprecation from the specific Course and updates the session Attribute
	 * "courseList" and sets the message Attribute accordingly.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
	
		if(KursOperation.removeDeprecatedKursFromDatabase(id)){
			
			List<Kurs> kurse = (List<Kurs>) request.getSession().getAttribute("courseList");
			Kurs kursChange = null;
			int index = 0;
			
			for (Kurs kurs : kurse) {
				if(kurs.getKursId().equals(id)){
					index = kurse.indexOf(kurs);
					kursChange = kurs;
				}
			}
			
			kurse.remove(kursChange);
			kurse.add(index, new Kurs(kursChange.getName(),id));
			
			
			request.setAttribute("message", new  Message(Alert.SUCCESS, "Markierung wurde entfernt."));
		} else{
			request.setAttribute("message", new  Message(Alert.DANGER, "Es is ein Fehler aufgetreten."));
		}
		
		request.getRequestDispatcher("courseOverview.jsp").forward(request, response);
	
	
	}

}
