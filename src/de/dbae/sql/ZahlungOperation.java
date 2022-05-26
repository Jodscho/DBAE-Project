package de.dbae.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.dbae.administration.Zahlung;
import de.dbae.utilities.SecurityUtilities;

/**
 * This class contains all methods that handle {@link Zahlung} Operations.
 * 
 * @author Jonathan Lochmann
 *
 */
public class ZahlungOperation {

	/**
	 * Inserts a {@link Zahlung} into the Database.
	 * 
	 * @param accountMail The Mail of the Person that made the Payment.
	 * @param iban The IBAN of the Person.
	 * @return Success of the Operation.
	 */
	public static boolean insertZahlungIntoDatabase(String accountMail, String iban) {
		
		return GeneralStatement.updateDatabase(SQL_Statement.INSERT_ZAHLUNG, new SQLSetParams() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, accountMail);
				stmt.setString(2, SecurityUtilities.generateKey());
				stmt.setString(3, iban);
			}
		});

	}

	/**
	 * Returns all Payments stored in the Database.
	 * 
	 * @return {@link List} of {@link Zahlung}en.
	 */
	@SuppressWarnings("unchecked")
	public static List<Zahlung> loadAusstehendeZahlungen() {
		
		return (List<Zahlung>) GeneralStatement.createObjectFromDatabase(SQL_Statement.LOAD_UNPAYED_ZAHLUNGEN, new SQLObjectFromSet() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
			}
			
			@Override
			public Object createObjectFromResultSet(ResultSet set) throws SQLException {
				List<Zahlung> zahlungen  = new ArrayList<>();
				while (set.next()) {
					zahlungen.add(new Zahlung(set.getString(1), set.getString(2), set.getString(3), set.getDouble(4), set.getBoolean(5)));
				}
				return zahlungen;
			}
		});
	}
	
	/**
	 * Marks a {@link Zahlung} as received.
	 * 
	 * @param zahlungid The ID of the Payment.
	 * @return Success of the Operation.
	 */
	public static boolean ZahlungIstEingegangen(String zahlungid){
		
		return GeneralStatement.updateDatabase(SQL_Statement.ACCEPT_ZAHLUNG, new SQLSetParams() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, zahlungid);
			}
		});
		
	}

}
