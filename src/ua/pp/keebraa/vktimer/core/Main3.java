package ua.pp.keebraa.vktimer.core;

import java.io.UnsupportedEncodingException;

import ua.pp.keebraa.vktimer.api.ExceptionUnsafeVkApi;
import ua.pp.keebraa.vktimer.core.IApplicationContext.ApplicationLevel;

public class Main3 {

    /**
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        IApplicationContext context = new ApplicationContext();
        context.setLogin("excelka@mail.ru");
        context.setPassword("WhatsMyAgeAgain");
        context.setApplicationId("3145529");
        context.setApi(new ExceptionUnsafeVkApi());
        context.hook(ApplicationLevel.CLEAN_START);
    }
}
