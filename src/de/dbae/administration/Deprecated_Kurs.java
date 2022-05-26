package de.dbae.administration;

/**
 * Contains the Additional Information for a Course that was marked as deprecated.<br>
 * Inherits from: {@link Kurs}.
 * 
 * @author Jonathan Lochmann
 *
 */
public class Deprecated_Kurs extends Kurs{
	
	/**
	 * Mail of the person who marked the course as deprecated.
	 */
	private String markierer;
	
	/**
	 * The Reason given for the Deprecation.
	 */
	private String grund;

	/**
	 * Constructor used for adding course to a courseList session Attribute.
	 * (Thus the extra parameter name)
	 * 
	 * @param name The Name for Deprecated course.
	 * @param kursId The Id.
	 * @param markierer The mail of the person who marked the course
	 * @param grund The Reason given for the Deprecation.
	 */
	public Deprecated_Kurs(String name, String kursId, String markierer, String grund) {
		super(name, kursId);
		this.setGrund(grund);
		this.setMarkierer(markierer);
	}

	/**
	 * Constructor used in Test Utilities.
	 * 
	 * @param kursId The Id.
	 * @param markierer The mail of the person who marked the course
	 * @param grund The Reason given for the Deprecation.
	 */
	public Deprecated_Kurs(String kursId, String markierer, String grund) {
		super(kursId);
		this.setGrund(grund);
		this.setMarkierer(markierer);
	}

	/**
	 * The Getter for the Reason.
	 * 
	 * @return reason
	 */
	public String getGrund() {
		return grund;
	}

	/**
	 * The Setter for the Reason.
	 * 
	 * @param grund the reason
	 */
	public void setGrund(String grund) {
		this.grund = grund;
	}

	/**
	 * The Getter for the Person who marked the Course.
	 * 
	 * @return persons mail
	 */
	public String getMarkierer() {
		return markierer;
	}

	/**
	 *  The Setter for the Person who marked the Course.
	 * 
	 * @param markierer the person mail
	 */
	public void setMarkierer(String markierer) {
		this.markierer = markierer;
	}

}
