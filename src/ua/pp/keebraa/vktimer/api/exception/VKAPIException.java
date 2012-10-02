package ua.pp.keebraa.vktimer.api.exception;

public class VKAPIException extends RuntimeException {
	private static final long serialVersionUID = 1851196617946721743L;

	public VKAPIException(String message, Throwable e) {
		super(message, e);
	}
	
	public VKAPIException(String message) {
		super(message);
	}
}
