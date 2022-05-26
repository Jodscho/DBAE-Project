package de.dbae.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.dbae.administration.Nutzer;
import de.dbae.utilities.EmailUtilities;
import de.dbae.wui.Alert;
import de.dbae.wui.Message;

/**
 * This Servlet sends a mail containing the provided information from 
 * the contactAdminModal using the {@link EmailUtilities} class.
 * 
 * @author Jonathan Lochmann
 */
@WebServlet("/ContactAdmin")
public class ContactAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// gets the Logged in User from the Session
		Nutzer user = ((Nutzer)request.getSession().getAttribute("logedInPerson"));
		
		//At the top of the message are the Users Credentials
		String message = "MESSAGE BY: " + user.getName() +", " +user.getMail() +"\n";
		
		message += request.getParameter("messageArea");
		String subject = request.getParameter("subject");

		// send Mail
		EmailUtilities.sendEmailTLS(EmailUtilities.MAIL_ACCOUNT, subject, message);
		
		request.setAttribute("message", new Message(Alert.INFO, "Mail was send."));
		
		request.getRequestDispatcher("courseOverview.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
