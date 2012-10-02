package ua.pp.keebraa.vktimer.api.accesstoken;

import java.io.IOException;

import ua.pp.keebraa.vktimer.api.HttpUtils;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AllowAccessPage implements AccessTokenWizardPage {

	private static String loginFormId = "login_submit";
	private static String submitButtonId = "install_allow";

	@Override
	public boolean validate(HtmlPage page) {
		String url = page.getWebResponse().getWebRequest().getUrl().toString();
		if (HttpUtils.hasHtmlFormWithId(page, loginFormId)) {
			return false;
		}
		if (!HttpUtils.hasHtmlElementWithId(page, submitButtonId)) {
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
