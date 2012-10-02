package ua.pp.keebraa.vktimer.api;

import ua.pp.keebraa.vktimer.api.accesstoken.AccessTokenWizard;

public class AccessToken {
	private String token;
	private Long expired;
	private Long queryTime;

	private AccessTokenWizard wizard;

	public AccessToken(IVkApi api) {
		expired = -10000l;
		queryTime = 0l;
		wizard = new AccessTokenWizard(api);
	}

	public String getAccessToken(String login, String password,
			String applicationId) {
		long currentTime = System.currentTimeMillis();
		boolean isExpired = ((queryTime + expired * 1000) <= currentTime);
		if (token == null || isExpired) {
			queryTime = System.currentTimeMillis();
			String url = buildAccessTokenUrl(applicationId);
			wizard.setLogin(login);
			wizard.setPassword(password);
			wizard.changePage(url);
			token = wizard.process();
			expired = wizard.getExpired();
		}
		return token;
	}

	private String buildAccessTokenUrl(String applicationId) {
		StringBuilder builder = new StringBuilder();
		builder.append("http://oauth.vk.com/oauth/authorize");
		builder.append("?client_id=");
		builder.append(applicationId);
		builder.append("&");
		builder.append("redirect_uri=http%3A%2F%2Fapi.vk.com%2Fblank.html");
		builder.append("&");
		builder.append("response_type=token");
		builder.append("&");
		builder.append("scope=440479");
		builder.append("&");
		builder.append("display=page");
		return builder.toString();
	}
}
