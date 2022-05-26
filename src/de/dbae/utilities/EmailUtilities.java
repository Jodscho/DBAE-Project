package de.dbae.utilities;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class is used during the Registration Process to send the Authentification Key to the User.
 * 
 * <p>Mostly copied from: <a href="https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/">Here</a></p>
 * 
 * @author Jonathan Lochmann
 *
 */
public class EmailUtilities {

	/**
	 * Mail of Project Account on GMAIL.
	 */
	public static final String MAIL_ACCOUNT = "dbaecloud@gmail.com";
	
	/**
	 * Unsafe Password of the Account.
	 */
	private static final String PASSWORD = "abcdefg12345";
	
	/**
	 * {@link Properties} that are used to prepare the Transmission.
	 */
	private static Properties props;

	/**
	 * Handles the actual Transmission of the Mail.
	 *  
	 * @param recipient The Mail of the Recipient.
	 * @param subject The Subject Line.
	 * @param body The actual Message.
	 */
	public static void sendEmailTLS(String recipient, String subject,
			String body) {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				new Authenticator() {
	        		protected PasswordAuthentication getPasswordAuthentication() {
	        		return new PasswordAuthentication(MAIL_ACCOUNT, PASSWORD);
	                }
	    		});
		
		Message message = new MimeMessage(session);	
		try {
			message.setFrom(new InternetAddress(MAIL_ACCOUNT));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipient));
			message.setSubject(subject);
			message.setText(body);
			
			Transport.send(message);
		} catch (AddressException e) {
			System.err.println("sendEmailTLS: The specified address does"
					+ "not exist!");
		} catch (MessagingException e) {
			System.err.println("sendEmailTLS: Could not send the message!");
			e.printStackTrace();
		}
	}
}
