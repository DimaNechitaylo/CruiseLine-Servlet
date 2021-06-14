package ua.training.util.exception;

public class ForbiddenOrderException extends RuntimeException {
	public ForbiddenOrderException(String message) {
		super(message);
	}
}
