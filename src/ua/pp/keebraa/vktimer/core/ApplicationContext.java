package ua.pp.keebraa.vktimer.core;

import java.util.HashMap;
import java.util.Map;

import ua.pp.keebraa.vktimer.api.answer.UserOnlineAnswer;
import ua.pp.keebraa.vktimer.api.answer.UsersOnlineAnswer;
import ua.pp.keebraa.vktimer.api.interfaces.IVkApi;
import ua.pp.keebraa.vktimer.core.statehandlers.ApplicationLevelHandler;
import ua.pp.keebraa.vktimer.core.statehandlers.CleanStartLevelHandler;
import ua.pp.keebraa.vktimer.core.statehandlers.ConnectLevelHandler;
import ua.pp.keebraa.vktimer.core.statehandlers.DisposeLevelHandler;
import ua.pp.keebraa.vktimer.core.statehandlers.InitLevelHandler;
import ua.pp.keebraa.vktimer.core.statehandlers.MonitoringLevelHandler;
import ua.pp.keebraa.vktimer.core.statehandlers.PrepareMonitoringLevelHandler;

//http://habrahabr.ru/post/131943/
public class ApplicationContext implements IApplicationContext {

    private static Map<ApplicationLevel, ApplicationLevelHandler> levelHandlers;
    private String login;
    private String password;
    private String applicationId;

    private IVkApi api;

    static {
        levelHandlers = new HashMap<ApplicationLevel, ApplicationLevelHandler>();
        levelHandlers.put(ApplicationLevel.INIT, new InitLevelHandler());
        levelHandlers.put(ApplicationLevel.CLEAN_START, new CleanStartLevelHandler());
        levelHandlers.put(ApplicationLevel.CONNECT, new ConnectLevelHandler());
        levelHandlers.put(ApplicationLevel.PREPARE_MONITORING, new PrepareMonitoringLevelHandler());
        levelHandlers.put(ApplicationLevel.MONITORING, new MonitoringLevelHandler());
        levelHandlers.put(ApplicationLevel.DISPOSE, new DisposeLevelHandler());
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }
    
    @Override
    public void setApi(IVkApi api){
        this.api = api;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

//    public static void init() {
//        api = new ExceptionUnsafeVkApi();
//        api.init(login, password, applicationId);
//    }

//    public static void start() throws UnsupportedEncodingException {
//        UserApi userApi = new UserApi(api);
//        String result = userApi.isUserOnline("blablabla_whiskas");
//        System.out.println("1: " + result);
//        result = userApi.isUserOnline("id48432956");
//        System.out.println("2: " + result);
//        result = userApi.isUserOnline("id9211039");
//        System.out.println("3: " + result);
//        result = userApi.isUserOnline("ashokina");
//        System.out.println("4: " + result);
//        result = userApi.isUserOnline("id13440155");
//        System.out.println("4: " + result);
//    }

    @Override
    public void initLevel() {
        api.init(login, password, applicationId);
    }

    @Override
    public void cleanStart() {
            api.dispose();
    }

    @Override
    public void connect() {
        api.getAccessToken();
    }

    @Override
    public void startMonitoring() {
        UsersOnlineAnswer answers = api.getUserApi().isUsersOnline("blablabla_whiskas","id13440155","stasy_st","id48432956","id15457031", "id6964099", "id92211236", "id111886171");
        for(UserOnlineAnswer answer : answers){
            System.out.println("user: "+answer.getUser()+" is online:"+answer.isOnline());
        }
    }

    @Override
    public void dispose() {
        api.dispose();
        login = null;
        password = null;
        applicationId = null;
        api = null;
        levelHandlers.clear();
        levelHandlers = null;
        System.exit(0);
    }

    @Override
    public void addOnlineMonitoringTarget(String userId) {

    }

    @Override
    public void prepareMonitoring() {

    }

    @Override
    public void hook(ApplicationLevel level) {
        System.out.println("HOOK: level "+level);
        levelHandlers.get(level).handleLevel(this);
    }
}
