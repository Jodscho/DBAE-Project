package de.dbae.utilities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.dbae.administration.Deprecated_Kurs;
import de.dbae.administration.Kurs;
import de.dbae.administration.Student;
import de.dbae.sql.GeneralStatement;
import de.dbae.sql.KursOperation;
import de.dbae.sql.NutzerOperation;
import de.dbae.sql.SQLSetParams;
import de.dbae.sql.SQL_Statement;

public class TestUtilities {
	
	
	private static String getHashedPassword(){
		String password = "12345";
		
		try {
			password = SecurityUtilities.getSaltedHash(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
	
	
	
	public static void createAdminAccount() {
		
		GeneralStatement.updateDatabase(SQL_Statement.INSERT_NUTZER, new SQLSetParams() {
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, "admin@admin.de");
				stmt.setString(2, "admin");
				stmt.setString(3, getHashedPassword());
			}
		});
		
		GeneralStatement.updateDatabase(SQL_Statement.INSERT_ADMIN, new SQLSetParams() {
			@Override
			public void prepareTheStatement(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, "admin@admin.de");
				stmt.setString(2, new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			}
		});
	}
	
	public static void createTenTestStudents(){
		
		Student[] testStudenten = new Student[10];
		
		for (int i = 0; i < 10; i++) {
			testStudenten[i] = new Student("mail" + (i+1) + "@mail.de", "student_" + (i+1), getHashedPassword());
			NutzerOperation.insertNutzerToDatabase(testStudenten[i]);
		}
		
	}
	
	public static void createTestKurse(){
		
		Kurs[] kurse = new Kurs[6];
		kurse[0] = new Kurs("Analytische Methoden SoSe 14", SecurityUtilities.generateKeyID());
		kurse[1] = new Kurs("Software Engineering", SecurityUtilities.generateKeyID());
		kurse[2] = new Kurs("Statistische Methoden", SecurityUtilities.generateKeyID());
		kurse[3] = new Kurs("Marketing A", SecurityUtilities.generateKeyID());
		kurse[4] = new Kurs("Programmierpraktikum Java", SecurityUtilities.generateKeyID());
		kurse[5] = new Kurs("Diskrete Methoden SoSe 14", SecurityUtilities.generateKeyID());
		
		Deprecated_Kurs[] kurse_deprecated = new Deprecated_Kurs[6];
		kurse_deprecated[0] = new Deprecated_Kurs(kurse[0].getKursId(), "admin@admin.de", "Neuer Dozent wurde eingestellt.");
		kurse_deprecated[1] = new Deprecated_Kurs(kurse[3].getKursId(), "mail1@mail.de", "Inhalte hier sind nicht mehr aktuell.");
		
		for (Kurs kurs : kurse) {
			KursOperation.insertKursIntoDatabase(kurs);
		}
		
		for (Deprecated_Kurs deprecated_Kurs : kurse_deprecated) {
			KursOperation.insertDeprecatedKursIntoDatabase(deprecated_Kurs.getKursId(), deprecated_Kurs.getMarkierer(), deprecated_Kurs.getGrund());
		}
	}
	
	public static void main(String[] args) {
		
		//createAdminAccount();
		//createTenTestStudents();
		createTestKurse();
		
	}
	

}
