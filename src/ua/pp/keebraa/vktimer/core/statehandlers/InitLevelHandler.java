package ua.pp.keebraa.vktimer.core.statehandlers;

import ua.pp.keebraa.vktimer.core.ApplicationContext;
import ua.pp.keebraa.vktimer.core.IApplicationContext.ApplicationLevel;

public class InitLevelHandler implements ApplicationLevelHandler {

    @Override
    public void handleLevel(ApplicationContext application) {
        application.initLevel();
        application.hook(ApplicationLevel.CONNECT);
    }
}
