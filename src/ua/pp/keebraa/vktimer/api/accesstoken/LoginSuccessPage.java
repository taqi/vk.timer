package ua.pp.keebraa.vktimer.api.accesstoken;

import java.io.IOException;

import ua.pp.keebraa.vktimer.api.HttpUtils;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class LoginSuccessPage implements AccessTokenWizardPage {

	private static String accessTokenGetParamName = "access_token";
	private static String expiredGetParamName = "expires_in";
	
	private static String description = "login success message page";

	@Override
	public boolean validate(HtmlPage page) {
		String url = page.getWebResponse().getWebRequest().getUrl().toString();
		if (!HttpUtils.hasGetParameter(url, accessTokenGetParamName)) {
			return false;
		}
		if (!HttpUtils.hasGetParameter(url, expiredGetParamName)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean process(AccessTokenWizard wizard) {
		String url = wizard.getPage().getWebResponse().getWebRequest().getUrl()
				.toString();
		String accessToken = HttpUtils.getParameter(url,
				accessTokenGetParamName);
		String expiredTime = HttpUtils.getParameter(url, expiredGetParamName);
		wizard.setExpired(expiredTime);
		wizard.setToken(accessToken);
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

	@Override
	public String getPageDescription() {
		return description;
	}
}
