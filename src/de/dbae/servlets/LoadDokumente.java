package de.dbae.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.txw2.Document;

import de.dbae.administration.Dokument;
import de.dbae.sql.DokumentOperation;
import de.dbae.sql.KursOperation;

/**
 * Loads all {@link Document}'s stored in the Database under a specific Course ID.
 * 
 * @author Maxim Shulyatev
 * 
 */
@WebServlet("/LoadDokumente")
public class LoadDokumente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadDokumente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * Loads all Documents in one specific Course and sets the Session Attribute "dokList" which is used to display 
	 * all the Documents in the jsp and to perform futher Operations.
	 * The specific Course is set as the request Attribute "kurs".
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("id");
		List<Dokument> dokList = DokumentOperation.getAllDoksByKurs(key);
		
		request.getSession().setAttribute("dokList", dokList);
		
		request.setAttribute("kurs", KursOperation.loadKursById(key));
		
		request.getRequestDispatcher("displayCourse.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
