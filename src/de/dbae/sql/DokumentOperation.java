package de.dbae.sql;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.xml.internal.txw2.Document;

import de.dbae.administration.Dokument;
import de.dbae.administration.FileFormat;
import de.dbae.administration.FileType;

/**
 * This class contains all methods that handle Document operations on the Database.
 * 
 * @author Maxim Shulyatev
 *
 */
public class DokumentOperation {

	/**
	 * Inserts a given {@link Dokument} into the Database.
	 * 
	 * @param dok The Document.
	 * @return Success of the Operation (boolean).
	 */
	public static boolean insertDokumentIntoDatabase(Dokument dok) {

		return GeneralStatement.updateDatabase(SQL_Statement.INSERT_DOK, new SQLSetParams() {

		    // Ueberschreiben die methode vom Interface SQLSetParams
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {

				stmt.setString(1, dok.getName());
				stmt.setString(2, dok.getBeschreibung());
				stmt.setString(3, dok.getDokId());
				stmt.setBinaryStream(4, dok.getStream());
				stmt.setString(5, dok.getKursId());
				stmt.setString(6, dok.getFreigeber());
				stmt.setString(7, dok.getUploader());
				stmt.setString(8, new SimpleDateFormat("yyyy-MM-dd").format(dok.getDatum()));
				stmt.setLong(9, dok.getGroesse());
				stmt.setString(10, dok.getSemester());
				stmt.setBoolean(11, dok.getSichtbar());
				stmt.setString(12, dok.getFormat().getFormat());
				stmt.setString(13, dok.getType().getType());

			}
		});
	}


	/**
	 * Deletes a Document from the Database. 
	 * 
	 * @param dokId The ID of the Document.
	 * @return Success of the Operation (boolean).
	 */
	public static boolean deleteDokumentFromDatabase(String dokId) {
		return GeneralStatement.updateDatabase(SQL_Statement.DELETE_DOK, new SQLSetParams() {

			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, dokId);
			}
		});
	}

	/**
	 * Returns all {@link Dokument}'s from the Database that belong to a specific Course.
	 * 
	 * @param kursId The ID of the Course.
	 * @return The List containing the Documents.
	 */
	@SuppressWarnings("unchecked")
	public static List<Dokument> getAllDoksByKurs(String kursId) {

		return (List<Dokument>) GeneralStatement.createObjectFromDatabase(SQL_Statement.LOAD_DOKS_BY_KURS,
				new SQLObjectFromSet() {

		            // SQLObjectFromSet extends SQLSetParams
					@Override
					public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
						stmt.setString(1, kursId);
					}

					@Override
					public Object createObjectFromResultSet(ResultSet set) throws SQLException {

						List<Dokument> dokumente = new ArrayList<>();

						while (set.next()) {
							dokumente.add(createDokumentFromRow(set));
						}

						return dokumente;
					}
				});
	}

	/**
	 * Returns all {@link Dokument}'s from the Database that are not yet visible.
	 * 
	 * @return The List containing the Documents.
	 */
	@SuppressWarnings("unchecked")
	public static List<Dokument> getFreizugebendeDokumente() {

		return (List<Dokument>) GeneralStatement.createObjectFromDatabase(SQL_Statement.LOAD_NON_VISIBLE_DOKS,
				new SQLObjectFromSet() {

					@Override
					public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
					}

					@Override
					public Object createObjectFromResultSet(ResultSet set) throws SQLException {
						List<Dokument> dokumente = new ArrayList<>();

						while (set.next()) {
							dokumente.add(createDokumentFromRow(set));
						}

						return dokumente;
					}
				});
	}

	/**
	 * Makes a specific Document from in the Database visible.
	 * 
	 * @param dokId The Document.
	 * @return Success of the Operation (boolean).
	 */
	public static boolean dokumentSichtbarMachen(String dokId) {
		return GeneralStatement.updateDatabase(SQL_Statement.MAKE_DOK_VISIBLE, new SQLSetParams() {
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, dokId);
			}
		});
	}

	/**
	 * Returns a specific {@link Dokument} from the Database.
	 * 
	 * @param dokid The ID of the Document.
	 * @return The Document.
	 */
	public static Dokument getDokumentFromDatabase(String dokid) {
		
		
		return (Dokument) GeneralStatement.createObjectFromDatabase(SQL_Statement.GET_DOK, new SQLObjectFromSet() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, dokid);				
			}
			
			@Override
			public Object createObjectFromResultSet(ResultSet set) throws SQLException {
				if(set.next()){
					return createDokumentFromRow(set);
				} else{
					return null;
				}
			}
		});
	}

	/**
	 * 
	 * This Helper Method creates a {@link Document} from one Row of the Result Set.
	 * 
	 * @param set The Result Set.
	 * @return The Document.
	 * @throws SQLException An Error Occured during the creating Process.
	 */
	private static Dokument createDokumentFromRow(ResultSet set) throws SQLException {
		Dokument dok = null;

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String name = set.getString(1);
		String beschreibung = set.getString(2);
		String dokId = set.getString(3);
		InputStream stream = set.getBinaryStream(4);
		String kursID = set.getString(5);
		String freigeber = set.getString(6);
		String uploader = set.getString(7);
		Date date = null;
		try {
			date = formatter.parse(set.getString(8));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long groesse = set.getLong(9);
		String semester = set.getString(10);
		boolean sichtbar = set.getBoolean(11);
		FileFormat format = FileFormat.valueOf(set.getString(12));
		FileType type = FileType.valueOf(set.getString(13).toUpperCase());

		dok = new Dokument(name, beschreibung, dokId, stream, kursID, freigeber, uploader, date, groesse, semester,
				sichtbar, format, type);

		dok.setBytes(set.getBytes(4));
		
		return dok;
	}

	/**
	 * Returns every {@link Document} stored in the Database.
	 * 
	 * @return List containing the Documents.
	 */
	@SuppressWarnings("unchecked")
	public static List<Dokument> getAllDoks() {
		
		return (List<Dokument>) GeneralStatement.createObjectFromDatabase(SQL_Statement.LOAD_ALL_DOKS, new SQLObjectFromSet() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				
			}
			
			@Override
			public Object createObjectFromResultSet(ResultSet set) throws SQLException {
				List<Dokument> dokumente = new ArrayList<>();

				while (set.next()) {
					dokumente.add(createDokumentFromRow(set));
				}

				return dokumente;
			}
		});
	}


	public static String getKursIdOfDokument(String id) {
		
		return (String) GeneralStatement.createObjectFromDatabase(SQL_Statement.LOAD_KURSID_FROM_DOK, new SQLObjectFromSet() {
			
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, id);
			}
			
			@Override
			public Object createObjectFromResultSet(ResultSet set) throws SQLException {
				String kursid = null;
				if(set.next()){
					kursid = set.getString(1);
				}
				return kursid;
			}
		});
	}

}
