package de.dbae.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.txw2.Document;

import de.dbae.administration.Dokument;
import de.dbae.utilities.FileStorageInDatabase;

/**
 * Handles the Users request to download a choosen {@link Document}.
 * 
 * @author Jonathan Lochmann
 * 
 */
@WebServlet("/DownloadDokument")
public class DownloadDokument extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadDokument() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * Downloads the specific {@link Dokument} from the Database.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dokid = request.getParameter("id");
		
		if(FileStorageInDatabase.downloadDokumentFromDatabase(dokid, response)){
			System.out.println("Download Successful.");
		} else{
			System.out.println("Download Not Successful");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
