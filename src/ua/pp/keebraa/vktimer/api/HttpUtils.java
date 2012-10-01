package ua.pp.keebraa.vktimer.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HttpUtils {

	private static Pattern urlParameter = Pattern
			.compile("[#?&]{1}(([a-zA-Z0-9_\\-]+)=([\\.,:/a-zA-Z0-9_\\-]*))[&]*");

	public static boolean hasGetParameter(String url, String parameterName) {
		Matcher matcher = urlParameter.matcher(url);
		while (matcher.find()) {
			String parameter = matcher.group(2);
			if (parameterName.equals(parameter)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasHtmlElementWithId(HtmlPage page, String elementId) {
		HtmlElement element = page.getElementById(elementId);
		return element != null;
	}

	public static boolean hasHtmlFormWithId(HtmlPage page, String elementId) {
		HtmlElement element = page.getElementById(elementId);
		if (element == null || !(element instanceof HtmlForm))
			return false;
		return true;
	}

	public static boolean hasButtonWithId(HtmlPage page, String buttonId) {
		HtmlElement element = page.getElementById(buttonId);
		if (element == null || !(element instanceof HtmlButton))
			return false;
		return true;
	}

	public static HtmlForm getHtmlFormById(HtmlPage page, String formId) {
		HtmlElement element = page.getElementById("login_submit");
		if (element == null || !(element instanceof HtmlForm)) {
			return null;
		}
		HtmlForm form = (HtmlForm) element;
		return form;
	}

	public static HtmlButton getHtmlButtonById(HtmlPage page, String buttonId) {
		HtmlElement element = page.getElementById(buttonId);
		if (element == null || !(element instanceof HtmlButton)) {
			return null;
		}
		HtmlButton button = (HtmlButton) element;
		return button;
	}
}
