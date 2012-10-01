package ua.pp.keebraa.vktimer.api.accesstoken;

import java.io.IOException;

import ua.pp.keebraa.vktimer.api.HttpUtils;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class LoginSuccessPage implements AccessTokenWizardPage {

	private static String submitButtonId = "install_allow";
	
	@Override
	public boolean validate(HtmlPage page) {
		if (!HttpUtils.hasButtonWithId(page, submitButtonId)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean process(AccessTokenWizard wizard) {
		HtmlPage page = wizard.getPage();
		HtmlButton allowButton = HttpUtils.getHtmlButtonById(page,
				submitButtonId);
		if (allowButton == null)
			return false;
		HtmlPage resultPage;
		try {
			resultPage = allowButton.click();
		} catch (IOException e) {
			return false;
		}
		wizard.changePage(resultPage);
		return true;
	}

	@Override
	public boolean validate(String url) {
		HtmlPage page = null;
		WebClient client = new WebClient();
		try {
			page = client.getPage(url);
		} catch (IOException e) {
			return false;
		}
		return validate(page);
	}
}
