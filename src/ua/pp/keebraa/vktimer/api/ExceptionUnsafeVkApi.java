package ua.pp.keebraa.vktimer.api;

import java.io.IOException;

import ua.pp.keebraa.vktimer.api.exception.GetPageException;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

public class ExceptionUnsafeVkApi implements IVkApi {
	private static String login;
	private static String password;
	private static String applicationId;
	private static WebClient webClient;

	private static AccessToken accessToken;

	public void init(String login, String password, String applicationId) {
		ExceptionUnsafeVkApi.login = login;
		ExceptionUnsafeVkApi.password = password;
		ExceptionUnsafeVkApi.applicationId = applicationId;
		ExceptionUnsafeVkApi.webClient = new WebClient();
		ExceptionUnsafeVkApi.accessToken = new AccessToken(this);
		ExceptionUnsafeVkApi.accessToken.getAccessToken(login, password, applicationId);
	}

	public String getAccessToken() {
		return accessToken.getAccessToken(login, password, applicationId);
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public Page getPage(String url) {
		try {
			Page page = webClient.getPage(url);
			return page;
		} catch (IOException e) {
			throw new GetPageException(url, e);
		}
	}
}
