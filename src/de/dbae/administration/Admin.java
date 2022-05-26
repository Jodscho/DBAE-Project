package de.dbae.administration;

import java.util.Date;

/**
 * Contains the Additional Implementation for the Admin.<br>
 * Inherits from: {@link Nutzer}.
 * 
 * @author Maxim Shulyatev
 *
 */
public class Admin extends Nutzer{
	
	/**
	 * The date when the admin was created.
	 */
	private Date creation;

	/**
	 * Constructor for the admin class.
	 * 
	 * @param mail The mail (primary key)
	 * @param name The Username
	 * @param password The Hashed Password
	 */
	public Admin(String mail, String name, String password) {
		super(mail, name, password);
		this.creation = new Date();
	}

	/**
	 * Getter for Creation Date.
	 * 
	 * @return creation.
	 */
	public Date getCreation() {
		return creation;
	}

	/**
	 * Setter for Creation Date.
	 * 
	 * @param creation
	 */
	public void setCreation(Date creation) {
		this.creation = creation;
	}
}
