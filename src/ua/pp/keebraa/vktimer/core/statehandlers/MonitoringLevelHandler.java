package ua.pp.keebraa.vktimer.core.statehandlers;

import ua.pp.keebraa.vktimer.core.ApplicationContext;
import ua.pp.keebraa.vktimer.core.IApplicationContext.ApplicationLevel;

public class MonitoringLevelHandler implements ApplicationLevelHandler {

    @Override
    public void handleLevel(ApplicationContext application) {
        application.startMonitoring();
        application.hook(ApplicationLevel.DISPOSE);
    }
}
