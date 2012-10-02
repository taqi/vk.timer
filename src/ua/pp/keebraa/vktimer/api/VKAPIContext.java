package ua.pp.keebraa.vktimer.api;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

public class VKAPIContext {
	private static String login;
	private static String password;
	private static String applicationId;
	private static WebClient webClient;

	private static AccessToken accessToken;

	public static void init(String login, String password, String applicationId) {
		VKAPIContext.login = login;
		VKAPIContext.password = password;
		VKAPIContext.applicationId = applicationId;
		VKAPIContext.webClient = new WebClient();
		VKAPIContext.accessToken = new AccessToken();
	}

	public static String getAccessToken() {
		return accessToken.getAccessToken(login, password, applicationId);
	}

	public static String getLogin() {
		return login;
	}

	public static String getPassword() {
		return password;
	}

	public static Page getPage(String url) throws IOException {
		return webClient.getPage(url);
	}
}
