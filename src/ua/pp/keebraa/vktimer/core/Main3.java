package ua.pp.keebraa.vktimer.core;

import java.io.UnsupportedEncodingException;

public class Main3 {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		Application.setLogin("excelka@mail.ru");
		Application.setPassword("WhatsMyAgeAgain");
		Application.setApplicationId("3145529");
		Application.init();
		Application.start();
	}
}
