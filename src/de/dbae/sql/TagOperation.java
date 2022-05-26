package de.dbae.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import de.dbae.administration.Dokument;
import de.dbae.administration.FileFormat;
import de.dbae.administration.FileType;

/**
 * This class is used to create the select boxes as a HTML-source for the 
 * {@link FilterDoc}. Secondly, this class implements the method to get the 
 * user search results.
 * 
 * @author Maxim Shulyatev
 *
 */
public class TagOperation{
   
    /**
     * Creates the select box as a HTML-source for the {@link FileType}'s.
     * 
     * @param kursid The id from course for which the select box is created.
     * @return HTML-source for the select box.
     */
    public static String getType(String kursid) {

        return (String) GeneralStatement.createObjectFromDatabase(
            SQL_Statement.LOAD_TYPE, new SQLObjectFromSet() {
            
                @Override
                public void prepareTheStatement(PreparedStatement stmt) 
                    throws SQLException {
                        stmt.setString(1, kursid);
                }

                @Override
                public Object createObjectFromResultSet(ResultSet set) 
                     throws SQLException {
                        
                         String typeTeil = "";
                         // Liste fuer verschiedene Types von Dokumenten,
                         // die im Kurs mit kursid vorhanden sind
                         ArrayList<String> typeList = new ArrayList<String>();
                         while(set.next()) {
                             typeList.add(set.getString(1));
                         }
                        
                         // Konstruktion von Select-Box (+ Type = Alle)
                         typeTeil += "<select class='form-control' id='type'"
                             + " name = 'type' size = '1'><option value =''>" 
                             + "Alle</option>";
                         for (int i = 0; i < typeList.size(); i++) {
                             typeTeil += "<option value = '"+ typeList.get(i)
                                 +"'>" + typeList.get(i) + "</option>";
                         }
                         typeTeil += "</select>";
                        
                         return typeTeil;
                }
        });
    }
    
    /**
     * Creates the select box as a HTML-source for the {@link FileFormat}'s.
     * 
     * @param kursid The id from course for which the select box is created.
     * @return HTML-source for the select box.
     */
    public static String getFormat(String kursid) {

        return (String) GeneralStatement.createObjectFromDatabase(
            SQL_Statement.LOAD_FORMAT,new SQLObjectFromSet() {
            
                @Override
                public void prepareTheStatement(PreparedStatement stmt) 
                    throws SQLException {
                        stmt.setString(1, kursid);
                }

                @Override
                public Object createObjectFromResultSet(ResultSet set) 
                    throws SQLException {
                        String formatTeil = "";
                        
                        // Liste fuer verschiedene Formate von Dokumenten,
                        // die im Kurs mit kursid vorhanden sind
                        ArrayList<String> formatList = new ArrayList<String>();
                        while(set.next()) {
                            formatList.add(set.getString(1));
                        }
                        
                        // Konstruktion von Select-Box (+ Type = Alle)
                        formatTeil += "<select class='form-control' id='format'"
                            + " name = 'format' size = '1'><option value =''>"
                            + "Alle</option>";
                        for (int i = 0; i < formatList.size(); i++) {
                            formatTeil += "<option value = '"+ formatList.get(i) 
                                +"'>" + formatList.get(i) + "</option>";
                        }
                        formatTeil += "</select>";
                        
                        return formatTeil;
                }
        });
    }
    
    /**
     * Creates the select box as a HTML-source for the semesters of documents.
     * 
     * @param kursid The id from course for which the select box is created.
     * @return HTML-source for the select box.
     */
    public static String getSemester(String kursid) {

        return (String) GeneralStatement.createObjectFromDatabase(
            SQL_Statement.LOAD_SEMESTER, new SQLObjectFromSet() {
            
                @Override
                public void prepareTheStatement(PreparedStatement stmt) 
                    throws SQLException {
                        stmt.setString(1, kursid);
                }

                @Override
                public Object createObjectFromResultSet(ResultSet set) 
                    throws SQLException {
                        String semesterTeil = "";
                        
                        // Liste fuer verschiedene Semesterauspr. von Doks,
                        // die im Kurs mit kursid vorhanden sind
                       ArrayList<String> semesterList = new ArrayList<String>();
                        while(set.next()) {
                            semesterList.add(set.getString(1));
                        }
                        
                        // Konstruktion von Select-Box (+ Type = Alle)
                        semesterTeil += "<select class='form-control'"
                            + " id='semester' name = 'semester' size = '1'>"
                            + "<option value =''>Alle</option>";
                        for (int i = 0; i < semesterList.size(); i++) {   
                        semesterTeil += "<option value = '"+ semesterList.get(i) 
                            +"'>" + semesterList.get(i) + "</option>";
                        }
                        semesterTeil += "</select>";
                        
                        return semesterTeil;
                }
        });
    }
    
    /**
     * Creates the select box as a HTML-source for the uploaders of documents.
     * 
     * @param kursid The id from course for which the select box is created.
     * @return HTML-source for the select box.
     */
    public static String getUploader(String kursid) {

        return (String) GeneralStatement.createObjectFromDatabase(
            SQL_Statement.LOAD_UPLOADER, new SQLObjectFromSet() {
            
                @Override
                public void prepareTheStatement(PreparedStatement stmt) 
                    throws SQLException {
                        stmt.setString(1, kursid);
                }

                @Override
                public Object createObjectFromResultSet(ResultSet set) 
                    throws SQLException {
                        String uploaderTeil = "";
                        
                        // Liste fuer verschiedene Uploader von Dokumenten,
                        // die im Kurs mit kursid vorhanden sind
                       ArrayList<String> uploaderList = new ArrayList<String>();
                        while(set.next()) {
                            uploaderList.add(set.getString(1));
                        }
                        
                        // Konstruktion von Select-Box (+ Type = Alle)
                        uploaderTeil += "<select class='form-control'"
                            + " id='uploader' name = 'uploader' size = '1'>"
                            + "<option value =''>Alle</option>";
                        for (int i = 0; i < uploaderList.size(); i++) {
                            uploaderTeil += "<option value = '"
                                + uploaderList.get(i) +"'>" 
                                + uploaderList.get(i) + "</option>";
                        }
                        uploaderTeil += "</select>";
                        
                        return uploaderTeil;
                }
        });
    }
    
    /**
     * Creates the ArrayList of documents which was found in the database for
     * the user search request.
     * 
     * @param str The user search request.
     * @return List of documents.
     */
	public static ArrayList<Dokument> getSearch(String str) {

    	SQL_Statement sqlStmt = null;
    	
    	// In der Abhaengigkeit von dem eingegebenen String, wird ein 
    	// Statement gewaehlt
    	if(FileFormat.checkForFormat(str)){
    	    // Suchwort = Format
    		sqlStmt = SQL_Statement.LOAD_SEARCH_FORMAT;
    	} else if(FileType.checkForType(str)){
    	    // Suchwort = Type
    		sqlStmt = SQL_Statement.LOAD_SEARCH_TYPE;
    	} else{
    	    // Sonst
    		sqlStmt = SQL_Statement.LOAD_SEARCH;
    	}
    	
    	return (ArrayList<Dokument>) GeneralStatement.createObjectFromDatabase(
    	    sqlStmt, new SQLObjectFromSet() {
			
    	        @Override
    	        public void prepareTheStatement(PreparedStatement stmt) 
    	            throws SQLException {
    	                // Es wird nach substrings von dem Suchwort
    	                // gesucht (case insensitive; SQL: UPPER -> toUpperCase)
    	                stmt.setBoolean(1, true);
    	                stmt.setString(2, "%"+str.toUpperCase()+"%");
    	                stmt.setString(3, "%"+str.toUpperCase()+"%");
    	                stmt.setString(4, "%"+str.toUpperCase()+"%");
    	                stmt.setString(5, "%"+str.toUpperCase()+"%");
				
    	                // Format oder Type setzen
    	                if(FileFormat.checkForFormat(str)){
    	                    stmt.setString(6, str.toUpperCase());
    	                } else if(FileType.checkForType(str)){
    	                    stmt.setString(6, Character.toUpperCase(
    	                       str.charAt(0)) + str.substring(1).toLowerCase());
    	                }
    	        }
			
    	        @Override
    	        public Object createObjectFromResultSet(ResultSet set) 
    	            throws SQLException {
    	                // Liste fuer Dokumente, die die Suchkriterien erf.
    	                ArrayList<Dokument> dokumente = 
    	                    new ArrayList<Dokument>();
    	                while (set.next()) {
    	                    dokumente.add(DokumentOperation.
    	                        getDokumentFromDatabase(set.getString(3)));
    	                }
				return dokumente;
    	        }
		});
    }
 
}
