package de.dbae.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.dbae.administration.Student;
import de.dbae.administration.Zahlung;
import de.dbae.sql.ZahlungOperation;
import de.dbae.wui.Alert;
import de.dbae.wui.Message;

/**
 * This Servlets accept the users provisional Payment Request and stores it 
 * into the Database. The Payment however has to be accepted by the User before the
 * {@link Student}'s Account is upgraded.(Here: {@link AusstehendeZahlungen#doPost(HttpServletRequest, HttpServletResponse)})
 * 
 * @author Jonathan Lochmann
 * 
 */
@WebServlet("/UpgradeAccount")
public class UpgradeAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpgradeAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * Creates a {@link Zahlung} Object from the users entered Information.
	 * This Object is stored in the Database with the attribute of not received, since the
	 * admin has to first check whether the money was received with the enclosed IBAN.
	 * {@link AusstehendeZahlungen}
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accountMail = ((Student)request.getSession().getAttribute("logedInPerson")).getMail();
		String iban = request.getParameter("iban");
		
		if(ZahlungOperation.insertZahlungIntoDatabase(accountMail, iban)){
			request.setAttribute("message", new Message(Alert.WARNING, "Die Zahlung wurde vermerkt."));
		} else{
			request.setAttribute("message", new Message(Alert.DANGER, "Ein Fehler ist aufgetreten bitte kontaktiere den Admin."));
		}
		request.getRequestDispatcher("courseOverview.jsp").forward(request, response);
	}

}
