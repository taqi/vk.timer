package ua.pp.keebraa.vktimer.core;

import java.io.UnsupportedEncodingException;

import ua.pp.keebraa.vktimer.api.UserApi;
import ua.pp.keebraa.vktimer.api.VKAPIContext;

//http://habrahabr.ru/post/131943/
public class Application {

	private static String login;
	private static String password;
	private static String applicationId;

	public static void setLogin(String login) {
		Application.login = login;
	}

	public static void setPassword(String password) {
		Application.password = password;
	}

	public static void setApplicationId(String applicationId) {
		Application.applicationId = applicationId;
	}

	public static void init() {
		VKAPIContext.init(login, password, applicationId);
	}

	public static void start() throws UnsupportedEncodingException {
		UserApi api = new UserApi();
		String result = api.isUserOnline("blablabla_whiskas");
		System.out.println("1: "+result);
		
		result = api.isUserOnline("blablabla_whiskas");
		System.out.println("2: "+result);
		
		result = api.isUserOnline("blablabla_whiskas");
		System.out.println("3: "+result);
	}
}
