package de.dbae.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.dbae.administration.Zahlung;
import de.dbae.sql.NutzerOperation;
import de.dbae.sql.ZahlungOperation;
import de.dbae.wui.Alert;
import de.dbae.wui.Message;

/**
 * This Servlet Handles the Upgrading Process for a specific User Account,
 * by flagging the {@link Zahlung} as received.
 * 
 * @author Jonathan Lochmann
 * 
 */
@WebServlet("/AusstehendeZahlungen")
public class AusstehendeZahlungen extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AusstehendeZahlungen() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * The Get method is used to load all Payments from the Database and converting the Information 
	 * into a Json Object, which then is send back to the original jsp. 
	 * 
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Zahlung> zahlungenAusstehend = ZahlungOperation.loadAusstehendeZahlungen();
		String json = new Gson().toJson(zahlungenAusstehend);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	/**
	 * 
	 * The Post method updates the Student Account to premium by marking the specific Payment
	 * as received. Firstly it needs to be checked if there even is a Payment Object from that specific User in the Database.
	 * The specific ID of the Payment is send back to the jsp for display purposes.
	 * 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String zahlungid = request.getParameter("id");
		String mail = request.getParameter("mail");
		
		if (ZahlungOperation.ZahlungIstEingegangen(zahlungid)) {
			if(NutzerOperation.updateStudentToPremium(mail)){
				System.out.println("Account wurde upgegraded.");
			}else{
				System.out.println("Account konnte nicht upgegraded werden.");
			}
		} else{
			System.out.println("Zahlung konnte nicht als eingegangen markiert werden.");
		}
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(zahlungid);

	}

}
