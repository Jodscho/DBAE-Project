package de.dbae.sql;

import de.dbae.administration.FileFormat;

/**
 * 
 * <p>This enum containes all SQL - Statements used in this Programm.</p>
 * Each single Statement can be loaded into a GeneralStatement Object {@link GeneralStatement}
 * to either update the Database ({@link GeneralStatement#updateDatabase(SQL_Statement, SQLSetParams)})
 * or to create an Object from the Database ({@link GeneralStatement#createObjectFromDatabase(SQL_Statement, SQLObjectFromSet)}).
 *
 * @author Jonathan Lochmann
 *
 */

public enum SQL_Statement {

	// Dokument-Statements

	INSERT_DOK("INSERT INTO dokumente VALUES(?,?,?,?,?,?,?,?::date,?,?,?,?::FileFormat, ?::FileType);"),
	DELETE_DOK("DELETE FROM dokumente WHERE dokid = ?;"),
	DELETE_DOKS_IN_KURS("delete from dokumente WHERE kursid = ?;"),
	GET_DOK("SELECT * FROM dokumente WHERE dokid = ?;"),
	LOAD_DOKS_BY_KURS("SELECT * FROM dokumente WHERE kursid = ?;"),
	LOAD_ALL_DOKS("SELECT * FROM dokumente;"),
	LOAD_NON_VISIBLE_DOKS("SELECT * FROM dokumente WHERE sichtbar = false;"),
	MAKE_DOK_VISIBLE("UPDATE dokumente SET sichtbar = true WHERE dokid = ?;"),

	// Nutzer-Statements

	INSERT_NUTZER("INSERT INTO nutzer VALUES(?,?,?);"),
	INSERT_STUDENT("INSERT INTO studenten VALUES(?,false);"),
	INSERT_ADMIN("INSERT INTO admins VALUES(?,?::date);"),
	DELETE_NUTZER("DELETE FROM nutzer WHERE mail = ?;"),
	DELETE_STUDENT("DELETE FROM studenten WHERE mail = ?;"),
	DELETE_ADMIN("DELETE FROM admins WHERE mail = ?;"),
	CHECK_NEW_ACCOUNT("SELECT * FROM studenten NATURAL JOIN nutzer WHERE mail LIKE ? OR name LIKE ?;"),
	CREATE_LOGIN_NUTZER("select nutzer.mail, name, password, premium,creation  from admins full outer join nutzer on nutzer.mail = admins.mail full outer join studenten on nutzer.mail = studenten.mail where name like ?;"),
	LOAD_ALL_STUDENTS("SELECT * FROM nutzer NATURAL JOIN studenten;"),
	UPDATE_STUDENT_PREMIUM("UPDATE studenten SET premium = ? WHERE mail = ?;"),
	
	// Kurs-Statements
	
	LOAD_KURSE("SELECT kurse.kursid, name, markierer, grund FROM kurse FULL OUTER JOIN kurse_deprecated ON kurse_deprecated.kursid = kurse.kursid"),
	RENAME_KURS("UPDATE kurse SET name = ? WHERE kursId = ?;"),
	LOAD_KURS("SELECT kurse.kursid, name, markierer, grund FROM kurse FULL OUTER JOIN kurse_deprecated ON kurse_deprecated.kursid = kurse.kursid WHERE kurse.kursid = ?"),
	INSERT_KURS("INSERT INTO kurse VALUES(?,?);"),
	INSERT_KURS_DEPRECATED("INSERT INTO kurse_deprecated VALUES(?,?,?);"),
	DELETE_KURS("DELETE FROM kurse WHERE kursid = ?;"),
	DELETE_KURS_DEPRECATED("DELETE from kurse_deprecated WHERE kursid = ?;"),
	LOAD_KURSID_FROM_DOK("SELECT kursid FROM dokumente WHERE dokid = ?;"),
	
	// Tag-Statements
    
    LOAD_SEMESTER("SELECT DISTINCT semester FROM dokumente WHERE kursid = ?;"),
    LOAD_TYPE("SELECT DISTINCT type FROM dokumente WHERE kursid = ?;"),
    LOAD_FORMAT("SELECT DISTINCT format FROM dokumente WHERE kursid = ?;"),
    LOAD_UPLOADER("SELECT DISTINCT uploader FROM dokumente WHERE kursid = ?;"),
    
    // Search
    LOAD_SEARCH("SELECT * FROM dokumente WHERE sichtbar = ? AND (UPPER(name) LIKE ? OR UPPER(beschreibung) LIKE ? OR"
        + " UPPER(uploader) LIKE ? OR UPPER(semester) LIKE ?);"),
    LOAD_SEARCH_TYPE("SELECT * FROM dokumente WHERE sichtbar = ? AND (UPPER(name) LIKE ? OR UPPER(beschreibung) LIKE ? OR"
        + " UPPER(uploader) LIKE ? OR UPPER(semester) LIKE ? OR type = ?::FileType);"),
    LOAD_SEARCH_FORMAT("SELECT * FROM dokumente WHERE sichtbar = ? AND (UPPER(name) LIKE ? OR UPPER(beschreibung) LIKE ? OR"
        + " UPPER(uploader) LIKE ? OR UPPER(semester) LIKE ? OR format = ?::FileFormat);"),
   
    
    // Zahlung-Statements
	
	INSERT_ZAHLUNG("INSERT INTO zahlungen VALUES(?,?,?, 10, false);"),
	LOAD_UNPAYED_ZAHLUNGEN("SELECT * FROM zahlungen WHERE erhalten = false;"),
	ACCEPT_ZAHLUNG("UPDATE zahlungen SET erhalten = true WHERE zahlungid = ?;");
    
    
	
	private String statement;

	private SQL_Statement(String statement) {
		this.setStatement(statement);
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}
}
