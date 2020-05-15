package serverside;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/*
 * 
 */
public class Server_HashFunction {

	/**
	 * ueberprüft Client und Serverpassword
	 * @param originalPassword originalpasswort vom Nutzer (schon gehast)
	 * @param storedPassword DbPasswort des Nutzers (nochmal gehast)
	 * @return true wenn gleich, sonst false
	 * @throws NoSuchAlgorithmException Fehlermeldung
	 * @throws InvalidKeySpecException Fehlermeldung
	 */
	static boolean validatePassword(String originalPassword, String storedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);
//        System.out.println(originalPassword);

		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, 64);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

//        System.out.println(iterations + ":"+ toHex(salt) + ":" + toHex(hash) + "\n" + storedPassword);
//        System.out.println();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	/**
	 * 
	 * @param hex Hexadez -> to byte
	 * @return bytes zum entschlüsseln
	 * @throws NoSuchAlgorithmException Fehlermeldung
	 */
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	/**
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		int iterations = 10000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}

	/**
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	/**
	 * @param array
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

}
