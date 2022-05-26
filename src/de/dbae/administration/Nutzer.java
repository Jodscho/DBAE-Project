package de.dbae.administration;

/**
 * 
 * Contains all the recurring Information on a specifc Person.<br>
 * Abstract Base Class of {@link Student} and {@link Admin}.<br>
 * <ul>
 * 	<li>{@link #name}</li>
 * 	<li>{@link #password} (hashed)</li>
 * 	<li>{@link #mail}</li>
 * </ul>
 * 
 * @author Jonathan Lochmann
 *
 */
public abstract class Nutzer {

	/**
	 * The name.
	 */
	private String name;
	
	/**
	 * The Hashed Password of the User.
	 */
	private String password;
	
	/**
	 * The mail of the User (primary key).
	 */
	private String mail;
	
	
	/**
	 * Constructor for this abstract Class.
	 * 
	 * @param mail The Mail.
	 * @param name The Name.
	 * @param password The Hashed Password.
	 */
	public Nutzer(String mail, String name, String password) {
		super();
		this.name = name;
		this.password = password;
		this.mail = mail;
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
	 * Getter for the Hashed Password.
	 * 
	 * @return The Hashed Password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for the Hashed Password.
	 * 
	 * @param password The Hashed Password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter for the Mail
	 * 
	 * @return The Mail.
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Setter for the Mail.
	 * 
	 * @param mail The Mail.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
