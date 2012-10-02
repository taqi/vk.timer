package ua.pp.keebraa.vktimer.api.answer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class UserGetAnswerDeserializer implements
		JsonDeserializer<UserGetAnswer> {

	@Override
	public UserGetAnswer deserialize(JsonElement element, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		UserGetAnswer answer = new UserGetAnswer();
		int uid = element.getAsJsonObject().get("response").getAsJsonArray()
				.get(0).getAsJsonObject().get("uid").getAsInt();
		boolean isOnline = element.getAsJsonObject().get("response")
				.getAsJsonArray().get(0).getAsJsonObject().get("online")
				.getAsInt() == 1;
		boolean isOnlineMobile = element.getAsJsonObject().get("response")
				.getAsJsonArray().get(0).getAsJsonObject().get("online_mobile") == null;
		answer.setOnline(isOnline);
		answer.setUid(uid);
		return answer;
	}
}
