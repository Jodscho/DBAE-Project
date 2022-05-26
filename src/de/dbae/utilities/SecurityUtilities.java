package de.dbae.utilities;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * This class implements the Security Utilities.
 * 
 * <p>Mostly copied from: <a href="https://stackoverflow.com/a/11038230">Stackoverflow Post</a></p>
 * 
 * @author Jonathan Lochmann
 *
 */
public class SecurityUtilities {

	/**
	 * Number of Hashing Iterations.
	 */
	private static final int ITERATIONS = 20 * 1000;
	
	/**
	 * Length of the Salt.
	 */
	private static final int SALT_LEN = 32;
	
	/**
	 * Length of the final Hashed Password.
	 */
	private static final int KEY_LEN = 256;

	/**
	 * Returns a Salted Hash of the Password.
	 * 
	 * @param pwd The Password that is to be hashed.
	 * @return The hashed Password.
	 * @throws Exception
	 */
	public static String getSaltedHash(String pwd) throws Exception {

		byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(SALT_LEN);
		String saltString = Base64.getEncoder().encodeToString(salt);
		String saltedHashString = Base64.getEncoder().encodeToString(hash(pwd, salt));
		return saltString + "$" + saltedHashString;
	}
	
	/**
	 * Generates a random Key for Identification Purposes in other classes.
	 * @return The Key.
	 */
	public static String generateKey(){
		byte[] salt = null;
		try {
			salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(10);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String saltString = Base64.getEncoder().encodeToString(salt);
		return saltString;
	}

	/**
	 * The Actual Hashing Method, which is called {@link SecurityUtilities#ITERATIONS} times over.
	 * 
	 * @param rawInput The password (most likely already hashed).
	 * @param salt The Salt.
	 * @return The hash in Form of a byte array.
	 */
	public static byte[] hash(String rawInput, byte[] salt) {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			SecretKey skey = skf.generateSecret(new PBEKeySpec(rawInput.toCharArray(), salt, ITERATIONS, KEY_LEN));
			return skey.getEncoded();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Checks whether the Users Password is equal to the Hashed Password in the Database.
	 * 
	 * @param enteredPwd The Entered Password.
	 * @param hashFromDatabase The Hash from the Database.
	 * @return Boolean whether the user typed in the right Password.
	 */
	public static boolean isPwdEqual(String enteredPwd, String hashFromDatabase){
		
		String[] saltAndHash = hashFromDatabase.split("\\$");
		String saltString = saltAndHash[0];
		String hashFromSalt = saltAndHash[1];
		
		byte[] saltByteArray = Base64.getDecoder().decode(saltString);
		return Base64.getEncoder().encodeToString(hash(enteredPwd, saltByteArray)).equals(hashFromSalt);
	}
	
	/**
	 * Generates Random Key as Course ID.
	 * @return The Key.
	 */
	public static String generateKeyID(){
		long key = new Random().nextLong();
		key = (key < 0) ? key *-1: key;
		return String.valueOf(key);
	}
}
