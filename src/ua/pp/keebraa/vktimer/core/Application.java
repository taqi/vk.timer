package ua.pp.keebraa.vktimer.core;

import ua.pp.keebraa.vktimer.api.VKAPIFacade;

//http://habrahabr.ru/post/131943/
public class Application {

	private static String login;
	private static String password;
	private static String applicationId;

	private static VKAPIFacade apiFacade;

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
		Application.apiFacade = new VKAPIFacade(login, password, applicationId);
	}

	public static void start() {
		boolean result = Application.apiFacade
				.isUserOnline("blablabla_whiskas");
		System.out.println(result);
	}
}
