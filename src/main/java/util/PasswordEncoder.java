package util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.log4j.Logger;

import controller.Servlet;

public class PasswordEncoder {
    private static Logger logger = Logger.getLogger(PasswordEncoder.class.getName());

	private static final String KEY = "PBKDF2WithHmacSHA1";
	private static final String RANDOM_KEY = "SHA1PRNG";
	private static final int INTERATIOND = 1000;

	public static String generatePasswordHash(String password) {
		char[] chars = password.toCharArray();
		byte[] hash = null;
		byte[] salt = null;
		SecretKeyFactory skf;
		try {
			salt = getSalt();
			PBEKeySpec spec = new PBEKeySpec(chars, salt, INTERATIOND, 64 * 8);
			skf = SecretKeyFactory.getInstance(KEY);
			hash = skf.generateSecret(spec).getEncoded();
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			logger.error(e);
		}
		return INTERATIOND + ":" + toHex(salt) + ":" + toHex(hash);
	}

	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance(RANDOM_KEY);
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	public static boolean validatePassword(String originalPassword, String storedPassword){
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);
		byte[] testHash = null;
		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skf;
		try {
			skf = SecretKeyFactory.getInstance(KEY);
			testHash = skf.generateSecret(spec).getEncoded();

		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			logger.error(e);
		}

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	private static byte[] fromHex(String hex) {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
}
