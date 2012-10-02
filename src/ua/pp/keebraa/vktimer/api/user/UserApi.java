package ua.pp.keebraa.vktimer.api.user;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.pp.keebraa.vktimer.api.VKAPIAbstract;
import ua.pp.keebraa.vktimer.api.VKAPIUtils;
import ua.pp.keebraa.vktimer.api.answer.UserOnlineAnswer;
import ua.pp.keebraa.vktimer.api.answer.UsersOnlineAnswer;
import ua.pp.keebraa.vktimer.api.interfaces.IUserApi;
import ua.pp.keebraa.vktimer.api.interfaces.IVkApi;

import com.gargoylesoftware.htmlunit.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserApi extends VKAPIAbstract implements IUserApi {
    private static final String userGetMethod = "users.get";
    private static final String userIdArgument = "uids";
    private static final String fieldsArgument = "fields";
    private static final String onlineField = "online";

    private IVkApi api;
    private Gson gson;

    public UserApi(IVkApi api) {
        this.api = api;
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(UserOnlineAnswer.class, new UserOnlineAnswerDeserializer());
        builder.registerTypeAdapter(UsersOnlineAnswer.class, new UsersOnlineAnswerDeserializer());
        gson = builder.create();
    }

    @Override
    public UserOnlineAnswer isUserOnline(String userId) {
        Map<String, String> arguments = new HashMap<String, String>();
        arguments.put(userIdArgument, userId);
        arguments.put(fieldsArgument, onlineField);
        String url = VKAPIUtils.buildMethodUrl(userGetMethod, arguments, api.getAccessToken());
        Page page = api.getPage(url);
        String json = page.getWebResponse().getContentAsString();
        UserOnlineAnswer answer = gson.fromJson(json, UserOnlineAnswer.class);
        return answer;
    }

    @Override
    public UsersOnlineAnswer isUsersOnline(String... userIds) {
        Map<String, String> arguments = new HashMap<String, String>();
        List<String> ids = Arrays.asList(userIds);
        String separatedIds = VKAPIUtils.toSeparated(",", ids);
        arguments.put(userIdArgument, separatedIds);
        arguments.put(fieldsArgument, onlineField);
        String url = VKAPIUtils.buildMethodUrl(userGetMethod, arguments, api.getAccessToken());
        Page page = api.getPage(url);
        String json = page.getWebResponse().getContentAsString();
        UsersOnlineAnswer answer = gson.fromJson(json, UsersOnlineAnswer.class);
        return answer;
    }
}
