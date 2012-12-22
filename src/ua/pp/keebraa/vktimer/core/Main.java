package ua.pp.keebraa.vktimer.core;

import java.io.UnsupportedEncodingException;

import ua.pp.keebraa.vktimer.api.ExceptionUnsafeVkApi;
import ua.pp.keebraa.vktimer.api.interfaces.IVkApi;
import ua.pp.keebraa.vktimer.core.units.UserOnlineLogTimeUnit;
import ua.pp.keebraa.vktimer.core.units.UsersOnlineUnit;

public class Main {

    private static final String login = "excelka@mail.ru";
    private static final String password = "WhatsMyAgeAgain";
    private static final String applicationId = "3145529";

    /**
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        IVkApi api = new ExceptionUnsafeVkApi();
        api.init(login, password, applicationId);
        Application app = new Application();
        app.setApi(api);
        UsersOnlineUnit usersOnlineUnit = new UsersOnlineUnit();
        usersOnlineUnit.setLogin(login);
        usersOnlineUnit.setPassword(password);
        usersOnlineUnit.setApplicationId(applicationId);
        usersOnlineUnit.setApi(api);
        usersOnlineUnit.setApplication(app);
        
        UserOnlineLogTimeUnit logTimeUnit = new UserOnlineLogTimeUnit();
        logTimeUnit.setApplication(app);
        app.addUnit(usersOnlineUnit);
        app.addUnit(logTimeUnit);
        app.start();
    }
}

