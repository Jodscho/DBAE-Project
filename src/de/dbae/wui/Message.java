package de.dbae.wui;

/**
 * Message that pops up as a Modal on the website.
 * 
 * @author Jonathan Lochmann
 *
 */
public class Message {
	
	/**
	 * The {@link Alert} type of the message.
	 */
	private Alert alert;
	
	/**
	 * The actual message.
	 */
	private String message;
	
	/**
	 * The color of the Modal.
	 */
	private String color;
	
	/**
	 * Constructor for the Message.
	 * 
	 * @param alert The {@link Alert} of this message.
	 * @param message The actual message.
	 */
	public Message(Alert alert, String message) {
		super();
		this.alert = alert;
		this.message = message;
		setColor();
	}

	/**
	 * Sets the Color of the Modal according to its {@link Alert}.
	 */
	private void setColor() {
		if(alert == Alert.DANGER){
			color = "#f2dede";
		} else if(alert == Alert.WARNING){
			color = "#fcf8e3";
			
		} else if(alert == Alert.INFO){
			color = "#d9edf7";
			
		} else if(alert == Alert.SUCCESS){
			color = "#dff0d8";
		}
	}

	/**
	 * Returns the css Class of the Alert.
	 * 
	 * @return css Class
	 */
	public String getAlert() {
		return alert.getCssClass();
	}

	/**
	 * Setter for the alert.
	 * 
	 * @param alert The alert
	 */
	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	/**
	 * Getter for the message
	 *  
	 * @return The message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter for the message.
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter for the color.
	 * 
	 * @return The color.
	 */
	public String getColor() {
		return color;
	}
}
