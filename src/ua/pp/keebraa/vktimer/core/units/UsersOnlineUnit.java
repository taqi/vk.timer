package ua.pp.keebraa.vktimer.core.units;

import ua.pp.keebraa.vktimer.api.exception.GetPageException;
import ua.pp.keebraa.vktimer.api.interfaces.IVkApi;
import ua.pp.keebraa.vktimer.core.Application;
import ua.pp.keebraa.vktimer.core.tasks.InitUnitTask;
import ua.pp.keebraa.vktimer.core.tasks.PrepareStartTask;
import ua.pp.keebraa.vktimer.core.tasks.StartTask;

public class UsersOnlineUnit implements ApplicationUnit {

    public static final String userOnlineUnitId = "users-online-unit";
    private String login;
    private String password;
    private String applicationId;

    private Application application;

    private IVkApi api;

    @Override
    public void setApplication(Application application) {
        this.application = application;
    }

    
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public IVkApi getApi() {
        return api;
    }

    public void setApi(IVkApi api) {
        this.api = api;
    }

    @Override
    public void cleanStart() {
        application.registerTask(new InitUnitTask(this));
    }

    @Override
    public void init() {
        api.init(login, password, applicationId);
        application.registerTask(new PrepareStartTask(this));
    }

    @Override
    public void prepareStart() {
        try{
            api.getAccessToken();
            application.registerTask(new StartTask(this));
        }catch(GetPageException e){
            System.out.println("connection problems...");
            application.registerTask(new InitUnitTask(this, 5000));
        }
        
    }

    @Override
    public void start() {
        try {
            System.out.println(api.getUserApi().isUserOnline("blablabla_whiskas").isOnline());
        } catch (GetPageException e) {
            System.out.println("connection timed out...");
        }

        application.registerTask(new StartTask(this, 500));
    }

    @Override
    public void stop() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public String getUnitId() {
        return userOnlineUnitId;
    }
}
