package ua.pp.keebraa.vktimer.core;

import ua.pp.keebraa.vktimer.api.AccessTokenGetter;
import ua.pp.keebraa.vktimer.api.UserOnlineStatus;
//http://habrahabr.ru/post/131943/
public class Application {
	public void init() {

	}

	public void initializeConnection() {
		AccessTokenGetter tokenGetter = new AccessTokenGetter();
		String token = tokenGetter.getAccessToken();
		UserOnlineStatus status = new UserOnlineStatus();
		boolean isOnline = status.isOnline("blablabla_whiskas", token);
		System.out.println(isOnline);
	}
}
