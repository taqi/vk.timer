package ua.pp.keebraa.vktimer.core.statehandlers;

import ua.pp.keebraa.vktimer.core.ApplicationContext;
import ua.pp.keebraa.vktimer.core.IApplicationContext.ApplicationLevel;

public class CleanStartLevelHandler implements ApplicationLevelHandler {

    @Override
    public void handleLevel(ApplicationContext application) {
        application.cleanStart();
        application.hook(ApplicationLevel.INIT);
    }
}
