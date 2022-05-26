package de.dbae.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.dbae.administration.Admin;
import de.dbae.administration.Nutzer;
import de.dbae.administration.Student;

/**
 * This Servlet handles the switching from the {@link Admin} View to the normal {@link Student} View (and back).
 * 
 * @author Jonathan Lochmann
 * 
 */
@WebServlet("/SwitchView")
public class SwitchView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SwitchView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * The {@link Admin} object stored in the "logedInPerson" session Attribute is temporarily stored in 
	 * the "tempAdmin" session Attribute. The original {@link Admin} Object in the "logedInPerson" Attribute will be
	 * replaced by Student version of the Admin so that the content in the jsp displays accordingly.
	 * If the {@link Admin} wants to switch back, this process is being reversed and the "tempAdmin" session Attribute 
	 * deleted.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Nutzer nutzer = (Nutzer) session.getAttribute("logedInPerson");

		if(nutzer instanceof Admin){
			Admin admin = (Admin) nutzer;
			session.setAttribute("tempAdmin", admin);
			session.setAttribute("logedInPerson", new Student(admin.getMail(), admin.getName(), admin.getPassword()));
		}else{
			Admin admin = (Admin) session.getAttribute("tempAdmin");
			session.setAttribute("logedInPerson", admin);
			session.removeAttribute("tempAdmin");
		}
		
		response.sendRedirect("courseOverview.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
