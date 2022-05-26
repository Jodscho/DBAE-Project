package de.dbae.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * The Interface defining the function that is used in the {@link GeneralStatement} class to 
 * prepare the given Statement.
 * 
 * @author Jonathan Lochmann
 *
 */
public interface SQLSetParams {
	
	/**
	 * 
	 * This method prepares the given {@link PreparedStatement} e.g using the {@link PreparedStatement#setString(int, String)} function.
	 * 
	 * @param stmt The Statement that is to be prepared.
	 * @throws SQLException The Exception thrown if an Error Occurs during the preparation.
	 */
	public void prepareTheStatement(PreparedStatement stmt) throws SQLException;

}
