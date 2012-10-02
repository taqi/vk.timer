package ua.pp.keebraa.vktimer.api;

import java.util.HashMap;
import java.util.Map;

import ua.pp.keebraa.vktimer.api.exception.AccessTokenWizardException;
import ua.pp.keebraa.vktimer.api.exception.handlers.AccessTokenWizardExceptionHandler;
import ua.pp.keebraa.vktimer.api.exception.handlers.ExceptionHandler;

import com.gargoylesoftware.htmlunit.Page;

public class ExceptionSafeVkApi implements IVkApi {
	private static Map<Class<? extends Throwable>, ExceptionHandler> exceptionHandlers;
	private IVkApi api;
	static {
		exceptionHandlers = new HashMap<Class<? extends Throwable>, ExceptionHandler>();
		exceptionHandlers.put(AccessTokenWizardException.class,
				new AccessTokenWizardExceptionHandler());
	}

	public ExceptionSafeVkApi() {
		api = new ExceptionUnsafeVkApi();
	}

	@Override
	public void init(String login, String password, String applicationId) {
		try {
			api.init(login, password, applicationId);
		} catch (Exception e) {
			ExceptionHandler handler = exceptionHandlers.get(e.getClass());
			if (handler == null)
				throw e;
			handler.handle(e);
		}
	}

	@Override
	public String getAccessToken() {
		try {
			return api.getAccessToken();
		} catch (Exception e) {
			ExceptionHandler handler = exceptionHandlers.get(e.getClass());
			if (handler == null)
				throw e;
			handler.handle(e);
			return null;
		}
	}

	@Override
	public Page getPage(String url) {
		try {
			return api.getPage(url);
		} catch (Exception e) {
			ExceptionHandler handler = exceptionHandlers.get(e.getClass());
			if (handler == null)
				throw e;
			handler.handle(e);
			return null;
		}
	}
}
