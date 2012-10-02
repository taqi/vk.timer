package ua.pp.keebraa.vktimer.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class VKAPIUtils {

	public static String buildMethodUrl(String methodName,
			Map<String, String> arguments, String accessToken) {
		StringBuilder builder = new StringBuilder();
		builder.append("https://api.vkontakte.ru/method/");
		builder.append(methodName);
		builder.append("?");
		for (String key : arguments.keySet()) {
			builder.append(key);
			builder.append("=");
			builder.append(arguments.get(key));
			builder.append("&");
		}
		return builder.toString();
	}

	public static String callMethod(String urlString) {
		String result = null;
		try {
			URL url = new URL(urlString);
			InputStream stream = url.openConnection().getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream));
			StringBuilder resultBuilder = new StringBuilder();
			while (reader.ready()) {
				String line = reader.readLine();
				resultBuilder.append(line);
			}
			result = resultBuilder.toString();
		} catch (IOException e) {
			result = null;
		}
		return result;
	}
}
