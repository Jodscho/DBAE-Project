package de.dbae.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	/**
	 *  Die Connection
	 */
	static Connection instance;
	
	/**
	 * Server-Verindungsinfos
	 */
	private static final String DB_SERVER = "horton.elephantsql.com:5432";
	private static final String DB_NAME = "lbjzxxyq";
    private static final String DB_USER = "lbjzxxyq";
    private static final String DB_PASSWORD = "EaFG_J9Do2Zirs1enObzHGowtoeYAJJ3";

	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://" + DB_SERVER + "/" + DB_NAME;
	
	private static Connection init() {
		try {
			// Treiber laden
			Class.forName(DB_DRIVER);
			
			// Connection holen
			instance = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			return instance;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("PostgresDb: Something went wrong: ");
			e.printStackTrace();
		}

		return null;
	}

	public static Connection getConnection() {
		try {
			if (instance == null || instance.isClosed()) {
				init();
			}
			return instance;
		} catch (SQLException e) {
			e.printStackTrace();
			return instance;
		}
		
	}

	public static void closeConnection() {
		try {
			instance.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
} 
