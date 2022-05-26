package de.dbae.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.dbae.connection.DatabaseConnection;

/**
 * 
 * <p>This class contains the basic Implementation for the Database Access.</p>
 * <p>Every Database operation in this programm can use this Implementation to simplify 
 * the process when updating the Database or when handling the ResultSet from an executed {@link SQL_Statement}.</p>
 * 
 * 
 * @author Maxim Shulyatev
 *
 */
public class GeneralStatement {

	/**
	 * <p>This method updates the Database using the given SQL - Statement and the given Instance of the SQLSetParams 
	 * Interface.</p>
	 * <p>The SQL - Statement is prepared using the function implementation from the received 
	 * setParams Interface.</p>
	 * <p>Every time a simple <code>INSERT/DELETE/UPDATE</code> to the Database is needed this method can be used.</p>
	 * 
	 * @param sqlStatement The SQL - Statement from the {@link SQL_Statement} enum.
	 * @param setParams An Interface of type {@link SQLSetParams} to set the parameters in the choosen SQL Statement.
	 * @return Boolean whether the Update was succesful.
	 */
	public static boolean updateDatabase(SQL_Statement sqlStatement, SQLSetParams setParams) {

		Connection con = DatabaseConnection.getConnection();

		try {
			
			con.setAutoCommit(false);
			PreparedStatement stmt = con.prepareStatement(sqlStatement.getStatement());
			setParams.prepareTheStatement(stmt);
			
			stmt.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		try {
			con.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	} finally {
		try {
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return true;

	}
	
	/**
	 * <p>This method creates an Object from the Database using the given SQL - Statement and the given Instance 
	 * of the SQLObjectFromSet Interface.</p>
	 * 
	 * <p>Firstly, the SQL - Statement is prepared using the function implementation from the received 
	 * Interface.<p>
	 * <p>After the Query to the Database is executed sucessfully, the object is created using the
	 * received ResultSet and the Implementation of the {@link SQLObjectFromSet#createObjectFromResultSet(java.sql.ResultSet)} function
	 * from the given Interface.
	 * </p>
	 * <p>Every time an <code>Object</code> needs to be created from the Database this method can be used.</p>
	 * 
	 * @param sqlStatement The SQL - Statement from the {@link SQL_Statement} enum.
	 * @param objectFromSet An Interface of type {@link SQLObjectFromSet} to set the parameters in the choosen SQL Statement and 
	 * to define how the Object is created from the ResultSet.
	 * @return
	 */
	public static Object createObjectFromDatabase(SQL_Statement sqlStatement, SQLObjectFromSet objectFromSet){
		
		Object object = null;
		Connection con = DatabaseConnection.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement(sqlStatement.getStatement());
			objectFromSet.prepareTheStatement(stmt);
			
			object = objectFromSet.createObjectFromResultSet(stmt.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return object;
	}
	

}
