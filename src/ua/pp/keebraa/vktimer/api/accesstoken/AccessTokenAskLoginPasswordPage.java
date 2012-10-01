package ua.pp.keebraa.vktimer.api.accesstoken;

import java.io.IOException;

import ua.pp.keebraa.vktimer.api.HttpUtils;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AccessTokenAskLoginPasswordPage implements AccessTokenWizardPage {

	private static String loginFormId = "login_submit";
	private static String loginInputName = "email";
	private static String passwordInputName = "pass";
	private static String submitButtonId = "install_allow";
	private static String mandatoryGetParameter = "response_type";

	@Override
	public boolean validate(HtmlPage page) {
		String url = page.getWebResponse().getWebRequest().getUrl().toString();
		if (!HttpUtils.hasGetParameter(url, mandatoryGetParameter)) {
			return false;
		}

		if (!HttpUtils.hasHtmlFormWithId(page, loginFormId)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean process(AccessTokenWizard wizard) {
		HtmlPage page = wizard.getPage();
		HtmlForm form = HttpUtils.getHtmlFormById(page, loginFormId);
		HtmlInput loginInput = form.getInputByName(loginInputName);
		loginInput.setValueAttribute(wizard.getLogin());
		HtmlInput passInput = form.getInputByName(passwordInputName);
		passInput.setValueAttribute(wizard.getPassword());
		HtmlButton button = HttpUtils.getHtmlButtonById(page, submitButtonId);
		HtmlPage newPage = null;
		try {
			newPage = button.click();
		} catch (IOException e) {
			return false;
		}
		if (validate(newPage)) {
			return false;
		}
		wizard.changePage(newPage);
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
