package ua.pp.keebraa.vktimer.core;

import java.io.UnsupportedEncodingException;

import ua.pp.keebraa.vktimer.api.ExceptionUnsafeVkApi;
import ua.pp.keebraa.vktimer.api.IVkApi;
import ua.pp.keebraa.vktimer.api.UserApi;

//http://habrahabr.ru/post/131943/
public class Application {

	private static String login;
	private static String password;
	private static String applicationId;

	private static IVkApi api;

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
		api = new ExceptionUnsafeVkApi();
		api.init(login, password, applicationId);
	}

	public static void start() throws UnsupportedEncodingException {
		UserApi userApi = new UserApi(api);
		String result = userApi.isUserOnline("blablabla_whiskas");
		System.out.println("1: " + result);

		result = userApi.isUserOnline("id48432956");
		System.out.println("2: " + result);

		result = userApi.isUserOnline("id9211039");
		System.out.println("3: " + result);
		result = userApi.isUserOnline("ashokina");
		System.out.println("4: " + result);
		result = userApi.isUserOnline("id13440155");
		System.out.println("4: " + result);
	}
}
