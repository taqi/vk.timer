package ua.pp.keebraa.vktimer.core.statehandlers;

import ua.pp.keebraa.vktimer.core.ApplicationContext;
import ua.pp.keebraa.vktimer.core.IApplicationContext.ApplicationLevel;

public class ConnectLevelHandler implements ApplicationLevelHandler {

    @Override
    public void handleLevel(ApplicationContext application) {
        application.connect();
        application.hook(ApplicationLevel.PREPARE_MONITORING);
    }
}
