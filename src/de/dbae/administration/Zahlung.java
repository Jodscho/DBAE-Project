package de.dbae.administration;

/**
 * 
 * Class holding all the Information on a outstanding/payed Payment.
 * <ul>
 * 	<li>{@link #kunde}</li>
 * 	<li>{@link #zahlungid}</li>
 * 	<li>{@link #iban}</li>
 * 	<li>{@link #gebuehr}</li>
 * 	<li>{@link #erhalten}</li>
 * </ul>
 * 
 * 
 * @author Jonathan Lochmann
 *
 */
public class Zahlung {
	
	/**
	 * The User associated with the Payment (mail).
	 */
	private String kunde;
	
	/**
	 * The unique ID of the Payment.
	 */
	private String zahlungid;
	
	/**
	 * The IBAN of the Payment<br>
	 * (Used also for Identification Purposes)
	 */
	private String iban;
	
	/**
	 * The amount of the Payment.<br>
	 * (Can be used at some point to dynamical compute a user specifc Payment)
	 */
	private double gebuehr;
	
	/**
	 * The status of the Payment.
	 */
	private boolean erhalten;
	
	/**
	 * The Constructor for the Payment.
	 * 
	 * @param kunde The Customer (mail).
	 * @param zahlungid The ID.
	 * @param iban The IBAN.
	 * @param gebuehr The amount.
	 * @param erhalten The status.
	 */
	public Zahlung(String kunde, String zahlungid, String iban, double gebuehr, boolean erhalten) {
		super();
		this.kunde = kunde;
		this.zahlungid = zahlungid;
		this.iban = iban;
		this.gebuehr = gebuehr;
		this.erhalten = erhalten;
	}
	
	/**
	 * Getter for the Customer.
	 * 
	 * @return The Customer (Mail).
	 */
	public String getKunde() {
		return kunde;
	}
	
	/**
	 * The Setter for the Customer.
	 * 
	 * @param kunde The Customer (mail).
	 */
	public void setKunde(String kunde) {
		this.kunde = kunde;
	}
	
	/**
	 * The Getter for the ID.
	 * 
	 * @return The ID.
	 */
	public String getZahlungid() {
		return zahlungid;
	}
	
	/**
	 * The Setter for the ID.
	 * 
	 * @param zahlungid The ID.
	 */
	public void setZahlungid(String zahlungid) {
		this.zahlungid = zahlungid;
	}
	
	/**
	 * The Getter for the IBAN.
	 * 
	 * @return The IBAN.
	 */
	public String getIban() {
		return iban;
	}
	
	/**
	 * The Setter for the IBAN.
	 * 
	 * @param iban The IBAN.
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	/**
	 * The Getter for the amount.
	 * 
	 * @return The amount.
	 */
	public double getGebuehr() {
		return gebuehr;
	}
	
	/**
	 * The Setter for the amount.
	 * 
	 * @param gebuehr The amount.
	 */
	public void setGebuehr(double gebuehr) {
		this.gebuehr = gebuehr;
	}
	
	/**
	 * The Getter for the status (boolean).
	 * 
	 * @return The status.
	 */
	public boolean isErhalten() {
		return erhalten;
	}
	
	/**
	 * The Setter for the status.
	 * 
	 * @param erhalten The status.
	 */
	public void setErhalten(boolean erhalten) {
		this.erhalten = erhalten;
	}
}
