package ua.pp.keebraa.vktimer.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UserOnlineStatus {
	public UserOnlineStatus() {

	}

	private String buildUrlString(String accessToken, String method,
			String... variables) {
		StringBuilder builder = new StringBuilder();
		builder.append("https://api.vkontakte.ru/method/");
		builder.append(method);
		builder.append("?");
		for (String variable : variables) {
			builder.append(variable);
			builder.append("&");
		}
		builder.append("access_token=");
		builder.append(accessToken);
		return builder.toString();
	}

	public boolean isOnline(String userId, String accessToken) {
		String variableUserId = "uids=" + userId;
		String variableFields = "fields=online";
		String urlString = buildUrlString(accessToken, "users.get",
				variableUserId, variableFields);
		return false;
	}
}
