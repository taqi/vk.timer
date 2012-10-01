package ua.pp.keebraa.vktimer.api;

public class VKAPIFacade {

	public static String buildLoginPageURL(String applicationId) {
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

	public String getAccessToken() {
		return null;
	}
}
