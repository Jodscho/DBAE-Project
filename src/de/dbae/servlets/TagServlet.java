package de.dbae.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.dbae.administration.Dokument;
import de.dbae.connection.DatabaseConnection;
import de.dbae.sql.DokumentOperation;
import de.dbae.sql.KursOperation;

/**
 * This servlet handles the filtering process of documents.
 * 
 * @author Maxim Shulyatev
 */
@WebServlet("/TagServlet")
public class TagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
     * Gets the selected semester, uploader, {@link FileFormat} and 
     * {@link FileType} and starts the filtering process. Updates the session 
     * attribute "dokList" with documents which fullfill the selected criteria.  
     * 
	 * @see HttpServlet#doGet(HttpServletRequest request, 
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
	    HttpServletResponse response) throws ServletException, IOException {
		
		// von displayCourse/FilterDoc/TagOperation
	    String semester = request.getParameter("semester");
	    String type = request.getParameter("type");
	    String uploader = request.getParameter("uploader");
	    String format = request.getParameter("format");
	    String kursid = request.getParameter("kursid");
	    
	    String teilFormat = "";
	    // Erweitere SQL-Anfrage(unten), wenn Format != Alle ausgewaelt
	    if(!format.equals("")){
			teilFormat = " AND format = ?::FileFormat ";
		}

		String teiltype = "";
		// Erweitere SQL-Anfrage(unten), wenn Type != Alle ausgewaelt
		if (!type.equals("")) {
			teiltype = " AND type = ?::FileType ";
		}
	   
	    // SQL-Anfrage; enum funktioniert nicht mit iLIKE und %
	    String query = "SELECT dokid FROM dokumente WHERE semester iLIKE ?" 
	        + " AND uploader iLIKE ? AND kursid iLIKE ? AND sichtbar = true " 
	        + teiltype + " " + teilFormat + ";";

	    // Liste mit dokid's von Dokumenten, die SQL-Anfrage(oben) erfuellen
	    List<String> dokIdsFiltered = new ArrayList<>();
	    
	    Connection con = DatabaseConnection.getConnection();
	    PreparedStatement stmt;
		try {
		    stmt = con.prepareStatement(query);
		    // % - suchen auch, wenn "" steht(value von ALLE in Select)
		    stmt.setString(1, "%"+semester+"%");
		    stmt.setString(2, "%"+uploader+"%");
		    stmt.setString(3, "%"+kursid+"%");
		    int pos = 4;
		    
		    // Type != Alle
		    if(!teiltype.isEmpty()) {
		        stmt.setString(pos, type);
		        pos++;
		    }
		    // Format != Alle
		    if(!teilFormat.isEmpty()) {
		        stmt.setString(pos, format);
		    }
	    
		    ResultSet set = stmt.executeQuery();
		    while(set.next()){
		        dokIdsFiltered.add(set.getString(1));
		    }
	    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
            con.close();
        } catch (SQLException exc) {
            // TODO Auto-generated catch block
            exc.printStackTrace();
        }
		
		
		List<Dokument> doks = new ArrayList<>();
		for (String id : dokIdsFiltered) {
            doks.add(DokumentOperation.getDokumentFromDatabase(id));
        
        }
		
		// Session Attribut dokList wird durch die neue Liste doks ersetzt
		request.getSession().setAttribute("dokList", doks);
	    request.setAttribute("kurs", KursOperation.loadKursById(kursid));
	    // Attribut "filter" für die entspr. Textausgabe in displayCourse
	    // (wenn dokList leer)
	    request.setAttribute("filter", true);
        request.getRequestDispatcher("displayCourse.jsp").
            forward(request, response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 *  HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
	    HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}