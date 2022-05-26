package de.dbae.administration;

import de.dbae.sql.TagOperation;

/**
 * File Formats that can be used:
 * 
 * <ul>
 *	 <li>{@link #PDF}</li>
 *	 <li>{@link #TXT}</li>
 *	 <li>{@link #ZIP}</li>
 *	 <li>{@link #XLS}</li>
 *	 <li>{@link #ODT}</li>
 * </ul>
 * 
 * @author Jonathan Lochmann
 */
public enum FileFormat {
	/**
	 * A .pdf Document.
	 */
	PDF("PDF"),
	
	/**
	 * A .txt Document.
	 */
	TXT("TXT"),
	
	/**
	 * A .zip Document.
	 */
	ZIP("ZIP"),
	
	/**
	 * A .xls Document.
	 */
	XLS("XLS"),
	
	/**
	 * A .odt Document.
	 */
	ODT("ODT"),
	
	/**
	 * A .doc Document.
	 */
	DOC("DOC");
	
	/**
	 * The String representation.
	 */
	private String format;

	/**
	 * Constructor for a specific FileFormat.
	 * 
	 * @param format The String representation.
	 */
	private FileFormat(String format){
		this.setFormat(format);
	}

	/**
	 * Getter for the File Format.
	 *  
	 * @return The Format (String).
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Setter for the File Format. 
	 * 
	 * @param format The format (String).
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
	/**
	 * Gets the Format in its dot notation.
	 * 
	 * @return Dot notation of Format.
	 */
	public String getDotFormat() {
		return ("." + this.format).toLowerCase();
	}
	

	/**
	 * Checks if a given String is a File Format.
	 * Only used in: {@link TagOperation#getSearch(String)}
	 * <br>
	 * <i>(Must be a easier way to do this)</i>
	 * 
	 * @param str The String.
	 * @return Boolean of the comparison.
	 */
	public static boolean checkForFormat(String str) {
		return str.toUpperCase().equals(FileFormat.DOC.getFormat()) 
	            | str.toUpperCase().equals(FileFormat.ODT.getFormat())
	            | str.toUpperCase().equals(FileFormat.PDF.getFormat())
	            | str.toUpperCase().equals(FileFormat.TXT.getFormat())
	            | str.toUpperCase().equals(FileFormat.XLS.getFormat())
	            | str.toUpperCase().equals(FileFormat.ZIP.getFormat());
	}

}

