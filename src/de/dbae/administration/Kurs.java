package de.dbae.administration;

/**
 * 
 * Contains the Information on a specific Course.<br>
 * 
 * <ul>
 * 	<li>{@link #name}</li>
 * 	<li>{@link #kursId}</li>
 * </ul>
 * 
 * @author Jonathan Lochmann
 *
 */
public class Kurs {

	/**
	 * The name of the Course.
	 */
	private String name;
	
	/**
	 * The unique Course ID.
	 */
	private String kursId;

	/**
	 * Constructor for the Course.
	 * 
	 * @param name The name.
	 * @param kursId The ID.
	 */
	public Kurs(String name, String kursId) {
		this.name = name;
		this.setKursId(kursId);
	}

	/**
	 * Constructor for the Course Class using only the ID.
	 * 
	 * @param kursId The ID.
	 */
	public Kurs(String kursId) {
		this.kursId = kursId;
	}

	/**
	 * Getter for the Course Name.
	 *  
	 * @return The Name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for the Course Name.
	 * 
	 * @param name The Name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the Course ID.
	 * 
	 * @return The ID.
	 */
	public String getKursId() {
		return kursId;
	}

	/**
	 * Setter for the Course ID.
	 * 
	 * @param kursId The ID.
	 */
	public void setKursId(String kursId) {
		this.kursId = kursId;
	}

}
