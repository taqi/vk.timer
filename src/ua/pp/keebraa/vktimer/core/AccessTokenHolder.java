package ua.pp.keebraa.vktimer.core;

import java.io.IOException;
import java.net.MalformedURLException;

import ua.pp.keebraa.vktimer.api.accesstoken.AccessTokenAskLoginPasswordPage;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AccessTokenHolder {
	private String accessToken;

	private long expired;

	private String login;

	private String password;

	public void init(String login, String password) {
		this.login = login;
		this.password = password;
		getAccessToken();
	}

	public String getAccessToken() {

		AccessTokenAskLoginPasswordPage wpage = new AccessTokenAskLoginPasswordPage();
		wpage.validate("http://api.vk.com/oauth/authorize?client_id=3145529&redirect_uri=http://api.vk.com/blank.html&scope=notify,friends,photos,audio,video,docs,notes,pages,wall,groups,messages,ads&display=page&response_type=token");
		try {
			WebClient client = new WebClient();
			HtmlPage page = client
					.getPage("http://api.vk.com/oauth/authorize?client_id=3145529&redirect_uri=http://api.vk.com/blank.html&scope=notify,friends,photos,audio,video,docs,notes,pages,wall,groups,messages,ads&display=page&response_type=token");
			HtmlElement element = page.getElementById("login_submit");
			if (element != null && element instanceof HtmlForm) {
				HtmlForm form = (HtmlForm) element;
				HtmlInput loginInput = form.getInputByName("email");
				HtmlInput passInput = form.getInputByName("pass");

				loginInput.setValueAttribute(login);
				passInput.setValueAttribute(password);

				HtmlElement buttonElement = page
						.getElementById("install_allow");
				if (buttonElement != null
						&& buttonElement instanceof HtmlButton) {
					HtmlButton button = (HtmlButton) buttonElement;
					HtmlPage secondPage = button.click();
					
				}
			}
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
