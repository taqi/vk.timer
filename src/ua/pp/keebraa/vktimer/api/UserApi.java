package ua.pp.keebraa.vktimer.api;

import java.util.HashMap;
import java.util.Map;

import ua.pp.keebraa.vktimer.api.answer.UserGetAnswer;
import ua.pp.keebraa.vktimer.api.answer.UserGetAnswerDeserializer;

import com.gargoylesoftware.htmlunit.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserApi extends VKAPIAbstract {
	private static final String userGetMethod = "users.get";
	private static final String userIdArgument = "uids";
	private static final String fieldsArgument = "fields";
	private static final String onlineField = "online";

	private IVkApi api;

	public UserApi(IVkApi api) {
		this.api = api;
	}

	public String isUserOnline(String userId) {
		Map<String, String> arguments = new HashMap<String, String>();
		arguments.put(userIdArgument, userId);
		arguments.put(fieldsArgument, onlineField);
		String url = VKAPIUtils.buildMethodUrl(userGetMethod, arguments,
				api.getAccessToken());
		Page page;
		page = api.getPage(url);
		String json = page.getWebResponse().getContentAsString();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(UserGetAnswer.class,
				new UserGetAnswerDeserializer());
		Gson gson = gsonBuilder.create();
		UserGetAnswer answer = gson.fromJson(json, UserGetAnswer.class);
		System.out.println(answer.isOnline());
		return page.getWebResponse().getContentAsString();
	}
}
