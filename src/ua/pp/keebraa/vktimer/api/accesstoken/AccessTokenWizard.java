package ua.pp.keebraa.vktimer.api.accesstoken;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import ua.pp.keebraa.vktimer.api.VKAPIFacade;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AccessTokenWizard {
	private String token;

	private long expired;

	private long queryTime;

	private HtmlPage currentPage;

	private WebClient client = new WebClient();

	private String login;

	private String password;

	private List<AccessTokenWizardPage> pages;

	public AccessTokenWizard() {
		pages = new LinkedList<AccessTokenWizardPage>();
		pages.add(new AccessTokenAskLoginPasswordPage());
		pages.add(new AllowAccessPage());
		changePage(VKAPIFacade.buildLoginPageURL("3145529"));
	}

	public HtmlPage getPage() {
		return currentPage;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void changePage(String url) {
		try {
			currentPage = client.getPage(url);
		} catch (IOException e) {
			currentPage = null;
		}
	}

	public void changePage(HtmlPage page) {
		currentPage = page;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void process() {
		for (AccessTokenWizardPage page : pages) {
			if (!page.validate(currentPage))
				continue;
			boolean result = page.process(this);
			if (!result) {

			}
		}
	}

	public void setExpired(String expired) {
		this.expired = Long.getLong(expired);
	}

	public void setQueryTime(long time) {
		queryTime = time;
	}
}