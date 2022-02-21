package exception;

public class BowlingGameException extends RuntimeException {

	private static final long serialVersionUID = -3174279303913541121L;

	public BowlingGameException(String message) {
		super(message);
	}
}
