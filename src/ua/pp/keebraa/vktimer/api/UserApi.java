package ua.pp.keebraa.vktimer.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.gargoylesoftware.htmlunit.Page;

public class UserApi extends VKAPIAbstract {
	private static final String userGetMethod = "users.get";
	private static final String userIdArgument = "uids";
	private static final String fieldsArgument = "fields";
	private static final String onlineField = "online";

	public String isUserOnline(String userId) {
		Map<String, String> arguments = new HashMap<String, String>();
		arguments.put(userIdArgument, userId);
		arguments.put(fieldsArgument, onlineField);
		String url = VKAPIUtils.buildMethodUrl(userGetMethod, arguments,
				VKAPIContext.getAccessToken());
		Page page;
		try {
			page = VKAPIContext.getPage(url);
		} catch (IOException e) {
			return "";
		}
		System.out.println(page.getWebResponse().getContentCharset());
		return page.getWebResponse().getContentAsString();
	}
}
