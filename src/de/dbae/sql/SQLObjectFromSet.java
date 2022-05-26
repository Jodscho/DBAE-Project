package de.dbae.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>The Interface used in the {@link GeneralStatement} class to create an Object 
 * From the given Result Set. The Implementation of its {@link SQLObjectFromSet#createObjectFromResultSet(ResultSet)}
 * function is used to create the Object.</p>
 * <p>This Interface inherits from the {@link SQLSetParams} Interface, since the used {@link SQL_Statement} might need
 * to be prepared with given parameters. If this is not the case the function can be left empty.</p>
 * 
 * @author Maxim Shulyatev
 *
 */
public interface SQLObjectFromSet extends SQLSetParams{

	/**
	 * This method creates the Object using the given ResultSet. An mostly used way is e.g 
	 * Iterating over the Set using the {@link ResultSet#next()} function in combination with a while loop
	 * or without the loop if just one row is expected.
	 * 
	 * @param set The ResultSet which is used to create the Object.
	 * @return The created Object.
	 * @throws SQLException The Exception thrown if an Error Occurs during the Object Creation.
	 */
	public Object createObjectFromResultSet(ResultSet set) throws SQLException;
	
}
