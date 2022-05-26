package de.dbae.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.dbae.administration.Dokument;
import de.dbae.sql.DokumentOperation;

/**
 * Handles the Process by which a Document is made visible.
 * 
 * @author Jonathan Lochmann
 * 
 */
@WebServlet("/DokumenteFreigeben")
public class DokumenteFreigeben extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DokumenteFreigeben() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * The Get method is used to load all {@link Dokument}'s from the Database and converting the Information 
	 * into a Json Object, which then is send back to the original jsp. 
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Dokument> freizugebendeDokumente = DokumentOperation.getFreizugebendeDokumente();
		
		String json = new Gson().toJson(freizugebendeDokumente);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}

	/**
	 * Removes the Document from the Database by its unique ID. The unique ID is then send back to
	 * the original jsp.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String dokId = request.getParameter("id");
		String btnAction = request.getParameter("btnAction");
		if(btnAction.equals("neinBtn")){
			if(DokumentOperation.deleteDokumentFromDatabase(dokId)){
				System.out.println("Dokument wurde entfernt.");
			}else{
				System.out.println("Dokument konnte nicht entfernt werden.");
			}
			
		}else{
			if(DokumentOperation.dokumentSichtbarMachen(dokId)){
				System.out.println("Dokument ist sichtbar.");
			}else{
				System.out.println("Dokument konnte nicht sichtbar gemacht werden.");
			}
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(dokId);
	
	}

}
