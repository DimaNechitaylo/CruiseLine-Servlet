package util;

import java.util.Base64;

import org.apache.log4j.Logger;

public class PasswordEncoder {
	private static Logger logger = Logger.getLogger(PasswordEncoder.class.getName());

	public static String encoge(String password) {
		return Base64.getEncoder().encodeToString(password.getBytes());
	}

	public static boolean validatePassword(String enteredPassword, String storedPassword) {
		logger.debug("enteredPassword: "+enteredPassword+"  storedPassword: "+storedPassword);
		return enteredPassword.equals(new String(Base64.getDecoder().decode(storedPassword)));
	}
}
