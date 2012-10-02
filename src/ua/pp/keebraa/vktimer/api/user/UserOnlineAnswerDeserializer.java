package ua.pp.keebraa.vktimer.api.user;

import java.lang.reflect.Type;

import ua.pp.keebraa.vktimer.api.answer.User;
import ua.pp.keebraa.vktimer.api.answer.UserOnlineAnswer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class UserOnlineAnswerDeserializer implements JsonDeserializer<UserOnlineAnswer> {
    private static String response = "response";
    private static String firstName = "first_name";
    private static String lastName = "last_name";
    private static String online = "online";
    private static String onlineMobile = "online_mobile";
    private static String uid = "uid";

    @Override
    public UserOnlineAnswer deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject responseObject = element.getAsJsonObject();
        JsonArray usersArray = responseObject.get(response).getAsJsonArray();
        JsonObject userObject = usersArray.get(0).getAsJsonObject();
        String userUID = userObject.get(uid).getAsString();
        String userFirstName = userObject.get(firstName).getAsString();
        String userLastName = userObject.get(lastName).getAsString();
        boolean userOnline = userObject.get(online).getAsInt() == 1;
        boolean userOnlineMobile = false;
        JsonElement onlineMobileElement = userObject.get(onlineMobile);
        if (onlineMobileElement != null) {
            userOnlineMobile = onlineMobileElement.getAsInt() == 1;
        }
        User user = new User();
        user.setFirstName(userFirstName);
        user.setLastName(userLastName);
        user.setUid(userUID);
        UserOnlineAnswer answer = new UserOnlineAnswer();
        answer.setUser(user);
        answer.setOnline(userOnline);
        answer.setMobileOnline(userOnlineMobile);
        return answer;
    }
}
