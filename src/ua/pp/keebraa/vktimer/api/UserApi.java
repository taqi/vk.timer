package ua.pp.keebraa.vktimer.api;

import java.util.HashMap;
import java.util.Map;

public class UserApi extends VKAPIAbstract {
	private static final String userGetMethod = "users.get";
	private static final String userIdArgument = "uids";
	private static final String fieldsArgument = "fields";
	private static final String onlineField = "online";

	public boolean isUserOnline(String token, String userId) {
		Map<String, String> arguments = new HashMap<String, String>();
		arguments.put(userIdArgument, userId);
		arguments.put(fieldsArgument, onlineField);
		String url = VKAPIUtils.buildMethodUrl(userGetMethod, arguments, token);
		String result = VKAPIUtils.callMethod(url);
		System.out.println(result);
		return false;
	}
}
