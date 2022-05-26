package de.dbae.administration;

/**
 * Contains the Additional Implementation for the Student.<br>
 * Inherits from: {@link Nutzer}.
 * 
 * @author Jonathan Lochmann
 *
 */
public class Student extends Nutzer{
	
	/**
	 * The Account status of the Student.
	 */
	private boolean premium;
	
	/**
	 * The Account enability of the Student.
	 */
	private boolean enabled;
	
	/**
	 * A unique key used during the registration process.
	 */
	private String key;

	/**
	 * A Constructor with the main attributes.
	 * 
	 * @param mail The Mail (primary key).
	 * @param name The Name.
	 * @param password The Hashed Pasword.
	 */
	public Student(String mail, String name, String password) {
		super(mail, name, password);
		setPremium(false);
	}

	/**
	 * <p>The Constructor used in the Registration Process.</p>
	 * 
	 *  {@link Student#Student(String, String, String)}
	 *  @param key A unique key.
	 */
	public Student(String mail, String name, String password, String key) {
		super(mail, name, password);
		setPremium(false);
		this.key = key;
	}

	/**
	 * <p>A Constructor including the premium attribute.</p>
	 * 
     *	{@link Student#Student(String, String, String)}
	 *  @param premium
	 */
	public Student(String mail, String name, String password, boolean premium) {
		super(mail, name, password);
		this.premium = premium;
	}

	/**
	 * Getter for the Premium boolean.
	 * 
	 * @return premium
	 */
	public boolean isPremium() {
		return premium;
	}

	/**
	 * Setter for the Premium attribute.
	 * 
	 * @param premium The premium boolean.
	 */
	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	/**
	 * Getter for the Key.
	 * 
	 * @return The Key.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Setter for the Key.
	 * 
	 * @param key The Key.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Getter for the enabled boolean.
	 * 
	 * @return enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Setter for the enabled boolean.
	 * 
	 * @param enabled The enability.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
