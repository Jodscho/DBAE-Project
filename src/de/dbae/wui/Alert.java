package de.dbae.wui;

/**
 * Alerts that are used to display a specific {@link Message} type, by adding the proper Bootstrap class.
 * 
 * <p>Alerts that can be used:</p>
 * 
 * <ul>
 * 	<li>{@link #SUCCESS}</li>
 * 	<li>{@link #INFO}</li>
 * 	<li>{@link #WARNING}</li>
 * 	<li>{@link #DANGER}</li>
 * </ul>
 * 
 * @author Jonathan Lochmann
 *
 */
public enum Alert {

	/**
	 * A green Success alert.
	 */
	SUCCESS("success"),

	/**
	 * A blue Info alert.
	 */
	INFO("info"),

	/**
	 * A yellow Warning alert.
	 */
	WARNING("warning"),

	/**
	 * A red Danger alert.
	 */
	DANGER("danger");

	/**
	 * The Bootstrap css Class.
	 */
	private final String cssClass;

	/**
	 * The Constructor of the Class.
	 * 
	 * @param cssClass The Bootstrap css Class.
	 */
	Alert(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * Getter for the css Class.
	 * 
	 * @return The css Class.
	 */
	public String getCssClass() {
		return cssClass;
	}

}