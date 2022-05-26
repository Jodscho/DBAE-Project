package de.dbae.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import de.dbae.administration.Admin;
import de.dbae.administration.Nutzer;
import de.dbae.administration.Student;
import de.dbae.utilities.SecurityUtilities;
import de.dbae.wui.Alert;
import de.dbae.wui.Message;
import jdk.internal.org.objectweb.asm.Handle;

/**
 * This class contains all methods that handle User Operations on the Database.
 * 
 * @author Jonathan Lochmann
 *
 */
public class NutzerOperation {
	
	/**
	 * Inserts a specific {@link Nutzer} into the Database.
	 * 
	 * @param nutzer The User.
	 * @return Success of the Operation.
	 */
	public static boolean insertNutzerToDatabase(Nutzer nutzer) {
		
		SQL_Statement query = (nutzer instanceof Student) ? SQL_Statement.INSERT_STUDENT : SQL_Statement.INSERT_ADMIN;
	
		if(!GeneralStatement.updateDatabase(SQL_Statement.INSERT_NUTZER, new SQLSetParams() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, nutzer.getMail());
				stmt.setString(2, nutzer.getName());
				stmt.setString(3, nutzer.getPassword());
			}
		})){
			return false;
		}
		
		return GeneralStatement.updateDatabase(query, new SQLSetParams() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, nutzer.getMail());
				if(query.equals(SQL_Statement.INSERT_ADMIN)){
					stmt.setString(2, (new SimpleDateFormat("dd/MM/yyyy").format(((Admin) nutzer).getCreation())));
				}
				
			}
		});
	}
	
	/**
	 * Deletes a specific {@link Nutzer} from the Database.
	 * 
	 * @param mail The mail of the User.
	 * @param student Boolean if {@link Nutzer} is a {@link Student}.
	 * @return Success of the Operation.
	 */
	public static boolean deleteNutzerFromDatabase(String mail, boolean student){
		
		SQL_Statement query = (student) ? SQL_Statement.DELETE_STUDENT : SQL_Statement.DELETE_ADMIN;
		
		if(!GeneralStatement.updateDatabase(query, new SQLSetParams() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, mail);
			}
		})){
			return false;
		}
		
		return GeneralStatement.updateDatabase(SQL_Statement.DELETE_NUTZER, new SQLSetParams() {
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, mail);				
			}
		});
		
	}
	
	/**
	 * Checks if a User is already registred with the given mail/name.
	 * 
	 * @param mail The mail.
	 * @param name The name.
	 * @return Returns an Integer describing one of the following Situations:
	 * <ol>
	 * 	<li> The mail is already taken</li>
	 *	<li> The name is already taken</li>
	 * 	<li> The name and mail are not taken</li>
	 * 	<li> An Error Occurred</li>
	 * </ol>
	 * 
	 */
	public static int checkNewAccount(String mail, String name){
		
		return (int) GeneralStatement.createObjectFromDatabase(SQL_Statement.CHECK_NEW_ACCOUNT, new SQLObjectFromSet() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, mail);
				stmt.setString(2, name);				
			}
			
			@Override
			public Object createObjectFromResultSet(ResultSet set) throws SQLException {
				if (set.next()) {
					if (set.getString(1).equals(mail)) {
						return 1;
					} else if (set.getString(3).equals(name)) {
						return 2;
					}
					return 4;
				}else {
					return 3;
				}
				
			}
		});
	}
	
	/**
	 * Creates a {@link Nutzer} Object from the Login, by checking if the name exists in the Database and the password are matching.
	 * 
	 * @param name The name.
	 * @param password The hashed Password.
	 * @return The {@link Nutzer}.
	 */
	public static Nutzer createNutzerObjectFromLogin(String name, String password){
		
		return (Nutzer) GeneralStatement.createObjectFromDatabase(SQL_Statement.CREATE_LOGIN_NUTZER, new SQLObjectFromSet() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, name);
			}
			@Override
			public Object createObjectFromResultSet(ResultSet set) throws SQLException {
				Nutzer nutzer = null;
				if(set.next()){
					if(SecurityUtilities.isPwdEqual(password, set.getString(3))){
						if(set.getString(5) != null){
							nutzer = new Admin(set.getString(1), set.getString(2), set.getString(3));
						}else{
							nutzer = new Student(set.getString(1), set.getString(2), set.getString(3), set.getBoolean(4));
						}
					}
				}
				return nutzer;
			}
		});
	}
	
	/**
	 * 
	 * Returns all Students from the Database.
	 * 
	 * @return {@link List} of {@link Student}'s.
	 */
	@SuppressWarnings("unchecked")
	public static List<Student> loadAlleStudentenFromDatabase(){
		return (List<Student>) GeneralStatement.createObjectFromDatabase(SQL_Statement.LOAD_ALL_STUDENTS, new SQLObjectFromSet() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
			}
			
			@Override
			public Object createObjectFromResultSet(ResultSet set) throws SQLException {
				List<Student> studenten = new ArrayList<>();
				while(set.next()){
					studenten.add(new Student(set.getString(1), set.getString(2), set.getString(3)));
				}
				return studenten;
			}
		});
	}
	
	
	/**
	 * Updates {@link Student} to Premium.
	 * 
	 * @param mail The mail.
	 * @return Success of the Operation.
	 */
	public static boolean updateStudentToPremium(String mail){
		
		return GeneralStatement.updateDatabase(SQL_Statement.UPDATE_STUDENT_PREMIUM, new SQLSetParams() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setBoolean(1, true);
				stmt.setString(2, mail);
			}
		});
	}
}
