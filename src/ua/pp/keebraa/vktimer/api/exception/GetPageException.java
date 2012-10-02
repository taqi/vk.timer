package ua.pp.keebraa.vktimer.api.exception;

public class GetPageException extends VKAPIException {

	public GetPageException(String url, Throwable e) {
		super("Could not get page with url: "+url, e);
	}

	private static final long serialVersionUID = 3685534306972962767L;
}
