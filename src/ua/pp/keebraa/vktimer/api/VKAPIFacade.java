package ua.pp.keebraa.vktimer.api;

public class VKAPIFacade {

	private String login;
	private String password;
	private String applicationId;
	private UserApi userApi;
	private AccessToken accessToken;

	public VKAPIFacade(String login, String password, String applicationId) {
		this.login = login;
		this.password = password;
		this.applicationId = applicationId;
		init();
	}

	private void init() {
		userApi = new UserApi();
		accessToken = new AccessToken();
		accessToken.getAccessToken(login, password, applicationId);
	}

	public boolean isUserOnline(String userId) {
		return userApi.isUserOnline(getAccessToken(), userId);
	}

	public String getAccessToken() {
		return accessToken.getAccessToken(login, password, applicationId);
	}
}
