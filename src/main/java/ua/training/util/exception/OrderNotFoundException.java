package ua.training.util.exception;

public class OrderNotFoundException  extends RuntimeException {
	public OrderNotFoundException(String message) {
		super(message);
	}
}
