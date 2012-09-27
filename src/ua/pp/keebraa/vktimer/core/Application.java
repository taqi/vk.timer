package ua.pp.keebraa.vktimer.core;

import ua.pp.keebraa.vktimer.api.AccessTokenGetter;
import ua.pp.keebraa.vktimer.api.UserOnlineStatus;

public class Application {
	public void init() {

	}

	public void initializeConnection() {
		AccessTokenGetter tokenGetter = new AccessTokenGetter();
		String token = tokenGetter.getAccessToken();
		UserOnlineStatus status = new UserOnlineStatus();
		boolean isOnline = status.isOnline("163730895", token);
		System.out.println(isOnline);
	}
}
