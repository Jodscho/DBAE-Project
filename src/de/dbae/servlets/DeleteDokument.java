package de.dbae.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.dbae.administration.Dokument;
import de.dbae.administration.Kurs;
import de.dbae.sql.DokumentOperation;
import de.dbae.sql.KursOperation;

/**
 * This Servlet handles the Deletion Process of a specific {@link Dokument} from the Database.
 * 
 * @author Maxim Shulyatev
 * 
 */
@WebServlet("/DeleteDokument")
public class DeleteDokument extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDokument() {
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
	 * Deletes the Dokument by its unique ID from the Database.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String dokId = request.getParameter("id");
		
		String kursId = DokumentOperation.getKursIdOfDokument(dokId);

		DokumentOperation.deleteDokumentFromDatabase(dokId);
		
		List<Dokument> doks = (List<Dokument>)request.getSession().getAttribute("dokList");
		// fuer displayCourse
		request.setAttribute("kurs", KursOperation.loadKursById(kursId));
	
		Dokument dokToRemove = null;
		
		for (Dokument dokument : doks) {
			if(dokument.getDokId().equals(dokId)){
				dokToRemove = dokument;
			}
		}
		// Referenz
		doks.remove(dokToRemove);
		
		request.getRequestDispatcher("displayCourse.jsp").forward(request, response);
	
	}

}
