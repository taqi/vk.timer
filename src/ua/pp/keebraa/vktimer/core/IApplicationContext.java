package ua.pp.keebraa.vktimer.core;

import ua.pp.keebraa.vktimer.api.interfaces.IVkApi;

public interface IApplicationContext {
    public enum ApplicationLevel {
        CLEAN_START, INIT, CONNECT, PREPARE_MONITORING, MONITORING, DISPOSE;
    }

    public void hook(ApplicationLevel level);
    public void setApi(IVkApi api);
    public void setLogin(String login);

    public void setPassword(String password);

    public void setApplicationId(String applicationId);

    public void addOnlineMonitoringTarget(String userId);

    /**
     * lifecycle methods
     */

    public void initLevel();

    public void cleanStart();

    public void connect();

    public void prepareMonitoring();

    public void startMonitoring();

    public void dispose();
}
