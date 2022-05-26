package de.dbae.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.dbae.administration.Deprecated_Kurs;
import de.dbae.administration.Kurs;

/**
 * This class contains all methods that handle Course Operations on the Database.
 * 
 * @author Jonathan Lochmann
 *
 */
public class KursOperation {

	/**
	 * Returns all {@link Kurs}e stored in the Database.
	 * 
	 * @return {@link List} containing the {@link Kurs}e.
	 */
	@SuppressWarnings("unchecked")
	public static List<Kurs> loadAlleKurseFromDatabase() {

		return (List<Kurs>) GeneralStatement.createObjectFromDatabase(SQL_Statement.LOAD_KURSE, new SQLObjectFromSet() {

			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
			}

			@Override
			public Object createObjectFromResultSet(ResultSet set) throws SQLException {
				List<Kurs> kurse = new ArrayList<>();

				while (set.next()) {
					if (set.getString(3) == null) {
						kurse.add(new Kurs(set.getString(2), set.getString(1)));
					} else {
						kurse.add(new Deprecated_Kurs(set.getString(2), set.getString(1), set.getString(3),
								set.getString(4)));
					}
				}

				return kurse;
			}
		});
	}

	/**
	 * Updates a Course Name in the Database.
	 * 
	 * @param name The new Name.
	 * @param id The ID of the Course.
	 * @return Success of the Operation.
	 */
	public static boolean updateKursNameInDatabase(String name, String id) {

		return GeneralStatement.updateDatabase(SQL_Statement.RENAME_KURS, new SQLSetParams() {

			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, name);
				stmt.setString(2, id);
			}
		});
	}

	/**
	 * Returns a specific Course from the Database.
	 * 
	 * @param kursId The Course ID.
	 * @return Success of the Operation.
	 */
	public static Kurs loadKursById(String kursId) {

		return (Kurs) GeneralStatement.createObjectFromDatabase(SQL_Statement.LOAD_KURS, new SQLObjectFromSet() {

			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, kursId);
			}

			@Override
			public Object createObjectFromResultSet(ResultSet set) throws SQLException {
				if (set.next()) {
					if (set.getString(3) == null) {
						return new Kurs(set.getString(2), set.getString(1));
					} else {
						return new Deprecated_Kurs(set.getString(2), set.getString(1), set.getString(3),
								set.getString(4));
					}
				}else{
					return null;
				}
			}
		});

	}

	/**
	 * Inserts a specific Course into the Database.
	 * 
	 * @param kurs The Course.
	 * @return Success of the Operation.
	 */
	public static boolean insertKursIntoDatabase(Kurs kurs) {

		return GeneralStatement.updateDatabase(SQL_Statement.INSERT_KURS, new SQLSetParams() {
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, kurs.getKursId());
				stmt.setString(2, kurs.getName());
			}
		});
	}

	/**
	 * Deletes an entire Course, including it's Documents, from the Database.
	 * 
	 * @param kursId The Course ID.
	 * @return Success of the Operation.
	 */
	public static boolean deleteEntireKursFromDatabase(String kursId) {
		SQL_Statement[] queries = { SQL_Statement.DELETE_DOKS_IN_KURS, SQL_Statement.DELETE_KURS };

		for (SQL_Statement sql_Statement : queries) {
			if (!GeneralStatement.updateDatabase(sql_Statement, new SQLSetParams() {

				@Override
				public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
					stmt.setString(1, kursId);
				}
			})) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Inserts a {@link Deprecated_Kurs} into the Database (Course must exit first). 
	 * 
	 * @param id The Course ID.
	 * @param marker The Person (mail) who marked the Course.
	 * @param grund The Reason for the Deprecation.
	 * @return
	 */
	public static boolean insertDeprecatedKursIntoDatabase(String id, String marker, String grund) {

		return GeneralStatement.updateDatabase(SQL_Statement.INSERT_KURS_DEPRECATED, new SQLSetParams() {

			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, id);
				stmt.setString(2, marker);
				stmt.setString(3, grund);
			}
		});

	}

	/**
	 * Removes a {@link Deprecated_Kurs} from the Database.
	 * 
	 * @param id The ID of the {@link Deprecated_Kurs}.
	 * @return Success of the Operation.
	 */
	public static boolean removeDeprecatedKursFromDatabase(String id) {

		return GeneralStatement.updateDatabase(SQL_Statement.DELETE_KURS_DEPRECATED, new SQLSetParams() {

			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, id);
			}
		});

	}
}
