package ua.training.util.exception;

public class UserAlreadyExistsException extends RuntimeException {
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
