package de.dbae.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.dbae.administration.Dokument;
import de.dbae.sql.DokumentOperation;
import de.dbae.sql.TagOperation;

/**

 * This Servlet handles the user search request.
 * 
 * @author Maxim Shulyatev
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Gets the user search query and uses the {@link TagOperation} class
	 * to get the documents which was found. Updates the session attribute 
	 * "dokList".
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 *  HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
	    HttpServletResponse response) throws ServletException, IOException {
		
	    //von Search, gesuchter String
	    String query = request.getParameter("searchStr");
	    
	    List<Dokument> gefundeneDokumente = TagOperation.getSearch(query);
	    
	    // damit displayCourse includiert wird
	    request.setAttribute("searchStr", query);
	    
	    // die neue dokListe wird fuer displayCourse gebraucht
	    request.getSession().setAttribute("dokList", gefundeneDokumente);
	    request.getRequestDispatcher("suche.jsp").forward(request, response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 *  HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
	    HttpServletResponse response) throws ServletException, IOException {
		
	}

}
