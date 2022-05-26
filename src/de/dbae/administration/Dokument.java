package de.dbae.administration;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Document that is stored in the Database in Form of a Byte String <i>(Yes, not the best solution)</i> along with 
 * the corresponding Information:
 * <ul>
 * 	<li>{@link #name}</li>
 * 	<li>{@link #beschreibung}</li>
 * 	<li>{@link #dokId}</li>
 * 	<li>{@link #kursId}</li>
 * 	<li>{@link #freigeber}</li>
 * 	<li>{@link #uploader}</li>
 * 	<li>{@link #datum}</li>
 * 	<li>{@link #groesse}</li>
 * 	<li>{@link #semester}</li>
 * 	<li>{@link #sichtbar}</li>
 *	<li>{@link #format}</li>
 * 	<li>{@link #type}</li>
 * </ul>
 * 
 * @author Maxim Shulyatev
 *
 */
public class Dokument {

	/**
	 * The name.
	 */
	private String name;
	
	/**
	 * The short discription.
	 */
	private String beschreibung;
	
	/**
	 * The unique id.
	 */
	private String dokId;
	
	/**
	 * The InputStream of the Dokument (optional)
	 */
	private InputStream stream;
	
	/**
	 * The id of the course this document belongs to.
	 */
	private String kursId;
	
	/**
	 * The person publishing the document (mail).
	 */
	private String freigeber;
	
	/**
	 * The person uploading the document (mail).
	 */
	private String uploader;
	
	/**
	 * The creation date.
	 */
	private Date datum;
	
	/**
	 * The size in bytes.
	 */
	private long groesse;
	
	/**
	 * The semester attributed to this document.
	 */
	private String semester;
	
	/**
	 * The visibility.
	 */
	private boolean sichtbar;
	
	/**
	 * The FileFormat of this document. {@link FileFormat}
	 */
	private FileFormat format;
	
	/**
	 * The FileType of this document. {@link FileType}
	 */
	private FileType type;
	
	
	/**
	 * The InputStream as bytes (only used for downloading the document).
	 */
	private byte[] bytes;
	
	/**
	 * Only used so that the size of this Document can be printed in a presentable Form
	 * inside the jsp using the JSTL.
	 */
	private String presentableSize;
	
	/**
	 *  Only used so that the date of this Document can be printed in a presentable Form
	 *  inside the jsp using the JSTL.
	 */
	private String presentableDate;

	/**
	 * Constructor without the date.
	 * 
	 * @param name The name of this document.
	 * @param beschreibung The description.
	 * @param dokId The unique Id for this document.
	 * @param stream The Input Stream.
	 * @param kursId The Course Id to which this doc belongs to.
	 * @param freigeber The publisher (mail).
	 * @param uploader The uploader (mail).
	 * @param groesse The file size. 
	 * @param semester The semester.
	 * @param format The FileFormat {@link FileFormat}
	 * @param type The FileType {@link FileType}
	 */
	public Dokument(String name,String beschreibung, String dokId, InputStream stream, String kursId,String freigeber, String uploader, long groesse,
			String semester, FileFormat format, FileType type) {
		super();
		this.name = name;
		this.beschreibung = beschreibung;
		this.dokId = dokId;
		this.stream = stream;
		this.kursId = kursId;
		this.freigeber = freigeber;
		this.uploader = uploader;
		setDatum();
		this.groesse = groesse;
		this.semester = semester;
		this.sichtbar = false;
		this.format = format;
		this.type = type;
	}
	
	/**
	 * <p>Constructor with the date.</p>
	 * 
	 * {@link Dokument#Dokument(String, String, String, InputStream, String, String, String, long, String, FileFormat, FileType)}
	 */
	public Dokument(String name,String beschreibung, String dokId, InputStream stream, String kursId,String freigeber, String uploader, Date datum, long groesse,
			String semester, boolean sichtbar, FileFormat format, FileType type) {
		super();
		this.name = name;
		this.beschreibung = beschreibung;
		this.dokId = dokId;
		this.stream = stream;
		this.kursId = kursId;
		this.freigeber = freigeber;
		this.uploader = uploader;
		this.datum = datum;
		this.groesse = groesse;
		this.semester = semester;
		this.sichtbar = sichtbar;
		this.format = format;
		this.type = type;
	}


	/**
	 * <p>Constructor without date and with visibility.</p>
	 * 
	 * {@link Dokument#Dokument(String, String, String, InputStream, String, String, String, long, String, FileFormat, FileType)}
	 * 
	 */
	public Dokument(String name, String beschreibung, String dokId, InputStream stream, String kursId,
			String freigeber, String uploader, long groesse, String semester, boolean sichtbar,FileFormat format, FileType type) {
		super();
		this.name = name;
		this.beschreibung = beschreibung;
		this.dokId = dokId;
		this.stream = stream;
		this.kursId = kursId;
		this.freigeber = freigeber;
		this.uploader = uploader;
		setDatum();
		this.groesse = groesse;
		this.semester = semester;
		this.sichtbar = sichtbar;
		this.format = format;
		this.type = type;
	}

	/**
	 * Getter for the Name.
	 * 
	 * @return The Name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the Name.
	 * 
	 * @param name The Name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the doc id.
	 * 
	 * @return The id.
	 */
	public String getDokId() {
		return dokId;
	}

	/**
	 * Setter for the doc id.
	 * 
	 * @param dokId The id.
	 */
	public void setDokId(String dokId) {
		this.dokId = dokId;
	}

	/**
	 * Getter for the Input Stream.
	 * 
	 * @return The input stream.
	 */
	public InputStream getStream() {
		return stream;
	}

	/**
	 * Setter for the Input Stream.
	 * 
	 * @param stream The input stream.
	 */
	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	/**
	 * Getter fot the Course Id.
	 * 
	 * @return The course id.
	 */
	public String getKursId() {
		return kursId;
	}

	/**
	 * Setter for the Course Id.
	 * 
	 * @param kursId The course id.
	 */
	public void setKursId(String kursId) {
		this.kursId = kursId;
	}

	/**
	 * Getter for the publisher.
	 * 
	 * @return The publisher (mail).
	 */
	public String getFreigeber() {
		return freigeber;
	}

	/**
	 * Setter for the publisher.
	 * 
	 * @param freigeber The publisher (mail).
	 */
	public void setFreigeber(String freigeber) {
		this.freigeber = freigeber;
	}

	/**
	 * Getter for the Uploader (mail).
	 * 
	 * @return The uploader.
	 */
	public String getUploader() {
		return uploader;
	}

	/**
	 * Setter for the Uploader (mail). 
	 * 
	 * @param uploader The uploader.
	 */
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	/**
	 * Getter for the upload Date.
	 * 
	 * @return The date.
	 */
	public Date getDatum() {
		return datum;
	}

	/**
	 * Setter for the upload Date.
	 */
	public void setDatum() {
		this.datum = new Date();
	}

	/**
	 * Getter for the file size.
	 * 
	 * @return The file size in byte.
	 */
	public long getGroesse() {
		return groesse;
	}

	/**
	 * Setter for the file size.
	 * 
	 * @param groesse The file size.
	 */
	public void setGroesse(long groesse) {
		this.groesse = groesse;
	}

	/**
	 * Getter for the semester.
	 * 
	 * @return The semester.
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * Setter for the Semester.
	 * 
	 * @param semester The semester.
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * Getter for the visibility.
	 * @return The visibility.
	 */
	public boolean getSichtbar() {
		return sichtbar;
	}

	/**
	 * Setter for the visibility.
	 * 
	 * @param sichtbar The doc is visible == true.
	 */
	public void setSichtbar(boolean sichtbar) {
		this.sichtbar = sichtbar;
	}

	/**
	 * The Getter for the FileFormat. {@link FileFormat}
	 * 
	 * @return The format.
	 */
	public FileFormat getFormat() {
		return format;
	}

	/**
	 * The Setter for the FileFormat. {@link FileFormat}
	 * 
	 * @param format The format.
	 */
	public void setFormat(FileFormat format) {
		this.format = format;
	}

	/**
	 * The Getter for the FileType. {@link FileType}
	 * 
	 * @return The type.
	 */
	public FileType getType() {
		return type;
	}

	/**
	 * The Setter for the FileType. {@link FileType}
	 * 
	 * @param type The type.
	 */
	public void setType(FileType type) {
		this.type = type;
	}

	/**
	 * Getter for the description of this document.
	 * 
	 * @return The description.
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Setter for the description of this document.
	 * 
	 * @param beschreibung The description
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * Getter for the bytes[] of this document.
	 * 
	 * @return The bytes.
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * Setter for the bytes[] of this document.
	 * 
	 * @param bytes The bytes.
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	/**
	 * Return the date in presentable form.
	 * 
	 * @return The Date.
	 */
	public String getPresentableDate(){
		return new SimpleDateFormat("dd.MM.yyyy").format(datum);
	}
	
	/**
	 * Returns the File Size in a presentable form.
	 * 
	 * @return The File Size.
	 */
	public String getPresentableSize() {
	    double dezimalSize = 0.0;
	    String name = "";
	    
	    if(groesse < 1000) {
	        dezimalSize = groesse;
	        name = "Byte";
	    } else if (1000 <= groesse & groesse < (1000 * 1000)) {
	        dezimalSize = groesse/1000.0;
	        name = "kB";
	    } else {
	        dezimalSize = groesse/(1000.0*1000.0);
	        name = "MB";
	    }
	   
	    DecimalFormat dFormat = new DecimalFormat("#.0");
	    String benutzerfrSize = "";
	    benutzerfrSize = dFormat.format(dezimalSize) + " " + name;
	   
	    return benutzerfrSize;
	
	}
}
