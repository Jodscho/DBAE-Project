package de.dbae.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

import de.dbae.administration.Admin;
import de.dbae.administration.Dokument;
import de.dbae.administration.FileFormat;
import de.dbae.administration.FileType;
import de.dbae.administration.Kurs;
import de.dbae.administration.Nutzer;
import de.dbae.sql.DokumentOperation;
import de.dbae.wui.Alert;
import de.dbae.wui.Message;

/**
 * This class is used to create the {@link Dokument} choosen by the User and to store it into the Database.
 * 
 * @author Maxim Shulyatev
 *
 */
public class FileStorageInDatabase {

	/**
	 * Creates the {@link Dokument} Object from the {@link HttpServletRequest}.
	 * 
	 * <p><b>NOTE:</b> For some Reason the description has to be parsed manually. It seems that it can't be 
	 * recognized after the Request has been parsed to a function.</p>
	 * 
	 * @param request The request of the Servlet.
	 * @param beschreibung The Description of the Document.
	 * @return Success of the Operation.
	 */
	public static Dokument createDokumentFromRequest(HttpServletRequest request, String beschreibung) {

		Dokument dok = null;

		try {

			boolean sichtbar = false;
			if( ((Nutzer)request.getSession().getAttribute("logedInPerson")) instanceof Admin){
				sichtbar = true;
			}
			Part filePart = request.getPart("uploadFile");

			//.getHeader --> form-data; name = "uploadFile"; filename= "C:\..."
			// replaceFirst --> filename= "C:\..."
			String name = (filePart.getHeader("Content-Disposition")).replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$",
					"$1");
			
			String dokId = SecurityUtilities.generateKey();
			InputStream stream = filePart.getInputStream();
			String kursId = request.getParameter("kursId");
			String freigeber = "admin@admin.de"; // Muss noch festgelegt werden
			String uploader = ((Nutzer) request.getSession().getAttribute("logedInPerson")).getMail();
			// Date date: erstellt im Konstruktor
			long groesse = filePart.getSize();
			String semester = request.getParameter("semester");
			// boolean sichtbar: default value ist false
			// z.B TXT; // getFileFormat unten definiert
			FileFormat format = getFileFormat(filePart.getContentType());
			
			// format = null <=> format nicht aus enum FileFormat
			if(format == null ){
				request.setAttribute("message", new Message(Alert.DANGER, "File Format is empty."));
				return null;
			}
			FileType type = null;
			try {
				type = FileType.valueOf(request.getParameter("filetype"));
			} catch (NullPointerException e) {
			    // Filetype nicht gewaelt (uploadModal)
				request.setAttribute("message", new Message(Alert.DANGER, "File Type is empty."));
				return null;
			}

			dok = new Dokument(name, beschreibung, dokId, stream, kursId, freigeber, uploader, groesse, semester,sichtbar,
					format, type);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}

		return dok;
	}

	/**
	 * 
	 * Handles the Download Process of a specifc {@link Dokument}.
	 * 
	 * @param dokid The ID of the Document.
	 * @param response The {@link HttpServletResponse}.
	 * @return Success of the Operation.
	 */
	public static boolean downloadDokumentFromDatabase(String dokid, HttpServletResponse response) {

		try {

			Dokument dok = DokumentOperation.getDokumentFromDatabase(dokid);

			InputStream inputStream = dok.getStream();
			
			// findContentType unten definiert
			response.setContentType(findContentFormat(dok.getFormat()));

			// Download window
			String headerValue = String.format("attachment; filename=\"%s\"", dok.getName());
			response.setHeader("Content-Disposition", headerValue);

			OutputStream outStream = response.getOutputStream();

			// Bytes fuer dok werden in DokumentOperation gesetzt
			byte[] buffer = new byte[1024];
			int bytesRead = -1;
			
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			outStream.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Returns the {@link ContentType} for a specific {@link FileFormat}.
	 * 
	 * @param format The File Format.
	 * @return The String representation of the Content Type.
	 */
	private static String findContentFormat(FileFormat format) {
		if (format.equals(FileFormat.PDF)) {
			return "application/pdf";
		} else if (format.equals(FileFormat.ZIP)) {
			return "application/x-zip-compressed";
		} else if (format.equals(FileFormat.ODT)) {
			return "application/vnd.oasis.opendocument.text";
		} else if (format.equals(FileFormat.DOC)) {
			return "application/msword";
		} else if (format.equals(FileFormat.TXT)) {
			return "text/plain";
		}
		return null;
	}

	/**
	 * Returns the {@link FileFormat} for a specifc {@link ContentType}.
	 * 
	 * @param contentType The String representation of the Content Type.
	 * @return The File Format.
	 */
	private static FileFormat getFileFormat(String contentType) {

		FileFormat format = null;

		if (contentType.equals("application/pdf")) {
			format = FileFormat.PDF;
		} else if (contentType.equals("application/vnd.oasis.opendocument.text")) {
			format = FileFormat.ODT;
		} else if (contentType.equals("application/msword")) {
			format = FileFormat.DOC;
		} else if (contentType.equals("application/x-zip-compressed")) {
			format = FileFormat.ZIP;
		} else if (contentType.equals("text/plain")) {
			format = FileFormat.TXT;
		}

		return format;
	}
	
	/**
	 * Downloads an entire {@link Kurs} from the Database, by storing each file in a Zip Archiv entitled with
	 * the name of the Course.
	 * 
	 * @param kursId The ID of the Course.
	 * @param kursName The Name of the Course.
	 * @param response The {@link HttpServletResponse}.
	 * @return Success of the Operation.
	 */
	public static boolean downloadCourseFromDatabase(String kursId, String kursName, HttpServletResponse response){
		
		String[] forbidChars = { ":", "<", ">", "|", "*", "?", "\\", "/", "\"", " " };
		for (String string : forbidChars) {
			kursName = kursName.replace(string, "_");
		}
		
		try{
		List<Dokument> doks = DokumentOperation.getAllDoksByKurs(kursId);
		List<File> doksAsFiles = new ArrayList<>();
		
		for (Dokument dokument : doks) {
			
			int lastIndexOfDot = dokument.getName().lastIndexOf(".");
			File temp = File.createTempFile(dokument.getName().substring(0,lastIndexOfDot), dokument.getFormat().getDotFormat());
			
			try {
				FileUtils.copyInputStreamToFile(dokument.getStream(), temp);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			doksAsFiles.add(temp);
			
		}
		
		File zipArchiv = zip(doksAsFiles, kursName);
		InputStream inputStream = FileUtils.openInputStream(zipArchiv);

		response.setContentType("application/zip");

		response.setHeader("Content-Disposition", "attachment; filename="+kursName+".zip");

		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[1024];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		outStream.close();
		inputStream.close();
		
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Creates a Zip-File from a {@link List} of {@link File}'s.<br>
	 * Copied from: <a href="https://stackoverflow.com/a/16547340">Stackoverflow Post</a><br>
	 * <i>Some small changes were made to adjust the code to our task.</i>
	 * 
	 * @param files The List containing the Files.
	 * @param filename The Name of the Zip-File.
	 * @return The Zip-File.
	 */
	public static File zip(List<File> files, String filename) {
		try {
	    File zipfile = File.createTempFile(filename, ".zip");
	    // Create a buffer for reading the files
	    byte[] buf = new byte[1024];
	    
	        // create the ZIP file
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
	        // compress the files
	        for(int i=0; i<files.size(); i++) {
	            FileInputStream in = new FileInputStream(files.get(i).getCanonicalPath());
	            
	            // remove long from temp file name
	            String fileName = files.get(i).getName();
	            String firstPart = fileName.substring(0,fileName.length()-23);
	            String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
	            
	            // add ZIP entry to output stream
	            out.putNextEntry(new ZipEntry(firstPart+extension));
	            // transfer bytes from the file to the ZIP file
	            int len;
	            while((len = in.read(buf)) > 0) {
	                out.write(buf, 0, len);
	            }
	            // complete the entry
	            out.closeEntry();
	            in.close();
	        }
	        // complete the ZIP file
	        out.close();
	        return zipfile;
	    }
		catch (IOException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
}
