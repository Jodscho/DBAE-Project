package de.dbae.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.dbae.administration.Dokument;
import de.dbae.administration.Student;
import de.dbae.sql.DokumentOperation;
import de.dbae.sql.KursOperation;
import de.dbae.utilities.FileStorageInDatabase;

/**
 * This Servlet handles the Upload Request from the User.
 * 
 * @author Maxim Shulyatev
 * 
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * 
	 * The {@link Dokument} is created from the Information the User submitted in
	 * the "uploadModal.jsp". This happens in: {@link FileStorageInDatabase#createDokumentFromRequest(HttpServletRequest, String)}
	 * The created Document will then be inserted into the Database in: {@link DokumentOperation#insertDokumentIntoDatabase(Dokument)}.
	 * Documents uploaded by a normal {@link Student} have to be manually accepted by the admin (on the Website).
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Dokument dok = FileStorageInDatabase.createDokumentFromRequest(request, request.getParameter("beschreibung"));
		
		//Speichern nur, wenn Dokument korrekt erzeugt wurde
		if(dok != null){
			DokumentOperation.insertDokumentIntoDatabase(dok);
			
			// Dok in die Liste hinzufuegen (Referenz)
			((List<Dokument>)request.getSession().getAttribute("dokList")).add(dok);
			
		}
		// fuer displayCourse
		request.setAttribute("kurs", KursOperation.loadKursById(request.getParameter("kursId")));
		request.getRequestDispatcher("displayCourse.jsp").forward(request, response);
		
	}
	
	
}
