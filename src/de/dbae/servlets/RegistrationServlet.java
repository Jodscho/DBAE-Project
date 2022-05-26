package de.dbae.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.dbae.administration.Nutzer;
import de.dbae.administration.Student;
import de.dbae.sql.KursOperation;
import de.dbae.sql.NutzerOperation;
import de.dbae.utilities.EmailUtilities;
import de.dbae.utilities.SecurityUtilities;
import de.dbae.wui.Alert;
import de.dbae.wui.Message;

/**
 * This Servlet handles the Registration Process.
 * 
 * @author Jonathan Lochmann
 * 
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * <b>NOTE:</b> Read
	 * {@link #doPost(HttpServletRequest, HttpServletResponse)} first.
	 * </p>
	 * 
	 * The Get method receives the Authentication Key submitted by the User and
	 * checks if the Key is correct. If the Key is correct, the {@link Student}
	 * Object of the User will be removed from the session Attribute
	 * "pendingUser" and stored into the Database. The successfully created
	 * {@link Student} is now set as as the logged in {@link Nutzer}. The User
	 * is send to the courseOverview.jsp and a message opens accordingly.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get the entered key from the user with no whitespaces
		String key = request.getParameter("key").trim();

		// add the User to the "pendingUsers" session Attribute
		List<Student> users = (List<Student>) session.getAttribute("pendingUsers");
		Student studentToRemove = null;

		// check for every User in the pending list if the key has a
		// corresponding User
		for (Student student : users) {

			// if thats the case remove the found User from the List
			if (student.getKey().equals(key)) {
				studentToRemove = student;
			}
		}

		// if the student is empty redirect the User to the authenticate.jsp to
		// enter a new and correct Key
		if (studentToRemove != null) {
			users.remove(studentToRemove);
			studentToRemove.setEnabled(true);

			// set the necessary Attributes for a Login
			session.setAttribute("logedInPerson", studentToRemove);
			request.setAttribute("username", studentToRemove.getName());

			// insert the User into the Database
			NutzerOperation.insertNutzerToDatabase(studentToRemove);

			// print message and load all Courses
			request.setAttribute("message", new Message(Alert.SUCCESS, "Registration is complete."));
			session.setAttribute("courseList", KursOperation.loadAlleKurseFromDatabase());
			request.getRequestDispatcher("courseOverview.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("authenticateStudent.jsp").forward(request, response);
		}
	}

	/**
	 * The Post method receives the submitted username, email and password.
	 * Firstly the username and the email are both checked to see if one of them
	 * already exists in the Database.
	 * {@link NutzerOperation#checkNewAccount(String, String)}. A Message is set
	 * according to the returned Integer. Only if both the username and the mail
	 * are not already taken will the Process continue. The newly created
	 * {@link Student} Object is added to a "pendingUsers" session Attribute.
	 * After the User inputs the Authentication Key that was send via mail, the
	 * student will be removed from the "pendingUsers" session Attribute and
	 * finally stored into the Database.
	 * 
	 * <p>
	 * <i>The Authentication Key insures that the mail the User provided is
	 * valid.</i>
	 * </p>
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		session = request.getSession();

		
		// check if the mail matches the below pattern
		if (email.matches(".+@uni-hildesheim.de")) {

			// check if the name or the mail is already taken
			int result = NutzerOperation.checkNewAccount(email, username);
			if (result == 3) {

				// send Key to User

				String key = SecurityUtilities.generateKey();

				String subject = "Activate Your Account at DBAE-Cloud";
				String body = "Thanks for registering." + "\n" + "Activate Your Account with the following key." + "\n"
						+ "KEY: " + key + "\n";

				EmailUtilities.sendEmailTLS(email, subject, body);

				try {
					password = SecurityUtilities.getSaltedHash(password);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// add the created Student to the Pending list, until the User
				// was authenticated via the key
				addStudentToPendingList(new Student(email, username, password, key));

				request.getRequestDispatcher("authenticateStudent.jsp").forward(request, response);

			} else {
				if (result == 1) {
					request.setAttribute("message", new Message(Alert.WARNING, "The Mail is already registerd."));
				} else if (result == 2) {
					request.setAttribute("message", new Message(Alert.WARNING, "The Username is already taken."));
				}
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", new Message(Alert.DANGER, "Please register with your uni mail."));
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * Adds the {@link Student} Object to the "pendingUsers" session Attribute
	 * where it is temporarily stored, until the User inputs the correct
	 * Authentication Key.
	 * 
	 * @param student
	 */
	private void addStudentToPendingList(Student student) {

		if (session.getAttribute("pendingUsers") == null) {

			List<Student> users = new ArrayList<>();
			users.add(student);
			session.setAttribute("pendingUsers", users);

		} else {
			((List<Student>) session.getAttribute("pendingUsers")).add(student);
		}

	}

}
