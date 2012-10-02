package ua.pp.keebraa.vktimer.api.accesstoken;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public interface AccessTokenWizardPage {
	boolean validate(String url);

	boolean validate(HtmlPage page);

	public boolean process(AccessTokenWizard wizard);
	
	public String getPageDescription();
}
