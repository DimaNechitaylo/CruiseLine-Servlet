package ua.training.util;

import java.util.Base64;

public class PasswordEncoder {

	public static String encoge(String password) {
		return Base64.getEncoder().encodeToString(password.getBytes());
	}

	public static boolean validatePassword(String enteredPassword, String storedPassword) {
		return enteredPassword.equals(new String(Base64.getDecoder().decode(storedPassword)));
	}
}
