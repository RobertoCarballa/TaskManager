package basic.model;

@SuppressWarnings("serial")
public class TaskNotFoundException extends RuntimeException {

	public TaskNotFoundException() {
		super();
	}

	public TaskNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
