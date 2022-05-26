package de.dbae.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.dbae.administration.Kurs;
import de.dbae.administration.Nutzer;
import de.dbae.administration.Student;
import de.dbae.sql.KursOperation;
import de.dbae.wui.Alert;
import de.dbae.wui.Message;

/**
 * This Servlet handles the Deletion Process of a specific {@link Kurs} from the Database.
 * 
 * @author Jonathan Lochmann
 * 
 */
@WebServlet("/DeleteCourse")
public class DeleteCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * Deletes the Course by its unique ID <i>(And all its Documents {@link KursOperation#deleteEntireKursFromDatabase(String)})</i> and sets the message accordingly.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String kursId = request.getParameter("id");
		
		if(KursOperation.deleteEntireKursFromDatabase(kursId)){
			List<Kurs> courseList = ((List<Kurs>) request.getSession().getAttribute("courseList"));
			Kurs kursToRemove = null;
			for (Kurs kurs : courseList) {
				if(kurs.getKursId().equals(kursId)){
					kursToRemove = kurs;
					break;
				}
			}
			courseList.remove(kursToRemove);
			request.setAttribute("message", new Message(Alert.SUCCESS, "Der Kurs wurde gelöscht."));
		} else{
			request.setAttribute("message", new Message(Alert.WARNING, "Ein Fehler wurde abgefangen."));
		}
		
		request.getRequestDispatcher("courseOverview.jsp").forward(request, response);
	
	}

}
