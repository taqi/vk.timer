package ua.pp.keebraa.vktimer.api.exception;

public class AccessTokenWizardException extends VKAPIException {

	private static final long serialVersionUID = -3787502426126038368L;
	
	public AccessTokenWizardException(String page) {
		super("Error during get accessToken. Reason: Page: "+page);
	}
}
