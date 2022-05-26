package de.dbae.administration;

import de.dbae.sql.TagOperation;

/**
 * File Types that can be used:
 * 
 * <ul>
 *	 <li>{@link #PROBEKLAUSUR}</li>
 *	 <li>{@link #TUTORIUM}</li>
 *	 <li>{@link #UEBUNG}</li>
 *	 <li>{@link #HAUSAUFGABE}</li>
 *	 <li>{@link #LERNZETTEL}</li>
 *	 <li>{@link #WEITERES}</li>
 * </ul>
 * 
 * @author Maxim Shulyatev
 *
 */
public enum FileType {

	/**
	 * A Probeklausur File.
	 */
	PROBEKLAUSUR("Probeklausur"),

	/**
	 * A Tutorium File.
	 */
	TUTORIUM("Tutorium"),

	/**
	 * A Uebung File.
	 */
	UEBUNG("Uebung"),

	/**
	 * A Hausaufgabe File.
	 */
	HAUSAUFGABE("Hausaufgabe"),

	/**
	 * A Lernzettel File.
	 */
	LERNZETTEL("Lernzettel"),

	/**
	 * A Weiteres File.
	 */
	WEITERES("Weiteres");

	/**
	 * The String representation of the File Type.
	 */
	private String type;

	/**
	 * Constructor for the File Type.
	 * 
	 * @param type The File Type.
	 */
	private FileType(String type) {
		this.setType(type);
	}

	/**
	 * Getter for the File Type.
	 * 
	 * @return The File Type (String)
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter for the File Type.
	 * 
	 * @param type The File Type (String)
	 */
	public void setType(String type) {
		this.type = type;
	}

	
	/**
	 * Checks if a given String is a File Type.
	 * Only used in: {@link TagOperation#getSearch(String)}
	 * <br>
	 * <i>(Must be a easier way to do this)</i>
	 * 
	 * @param str The String.
	 * @return Boolean of the comparison.
	 */
	public static boolean checkForType(String str) {
		return (Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase()).equals(FileType.HAUSAUFGABE.getType())
                | (Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase()).equals(FileType.LERNZETTEL.getType())
                | (Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase()).equals(FileType.PROBEKLAUSUR.getType())
                | (Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase()).equals(FileType.TUTORIUM.getType())
                | (Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase()).equals(FileType.UEBUNG.getType())
                | (Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase()).equals(FileType.WEITERES.getType());
	}

}
