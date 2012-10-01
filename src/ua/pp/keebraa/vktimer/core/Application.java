package ua.pp.keebraa.vktimer.core;

import ua.pp.keebraa.vktimer.api.UserOnlineStatus;
import ua.pp.keebraa.vktimer.api.accesstoken.AccessTokenWizard;
import ua.pp.keebraa.vktimer.api.impl.JDialogAccessTokenGetter;

//http://habrahabr.ru/post/131943/
public class Application {

	private AccessTokenHolder accessTokenHolder = new AccessTokenHolder();

	public void init() {
		AccessTokenWizard wizard = new AccessTokenWizard();
		wizard.setLogin("excelka@mail.ru");
		wizard.setPassword("WhatsMyAgeAgain");
		wizard.process();
	}

	public void initializeConnection() {
		JDialogAccessTokenGetter tokenGetter = new JDialogAccessTokenGetter();
		String token = tokenGetter.getAccessToken();
		UserOnlineStatus status = new UserOnlineStatus();
		boolean isOnline = status.isOnline("blablabla_whiskas", token);
		System.out.println(isOnline);
	}
}
