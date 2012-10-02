package ua.pp.keebraa.vktimer.core.statehandlers;

import ua.pp.keebraa.vktimer.core.ApplicationContext;

public class DisposeLevelHandler implements ApplicationLevelHandler {

    @Override
    public void handleLevel(ApplicationContext application) {
        application.dispose();
        System.exit(3);
    }
}
