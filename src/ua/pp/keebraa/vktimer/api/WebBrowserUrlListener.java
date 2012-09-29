package ua.pp.keebraa.vktimer.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;

import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserListener;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;

public class WebBrowserUrlListener implements WebBrowserListener {

	private Pattern pattern = Pattern.compile("access_token=([a-zA-Z0-9]*)&");
	private String token;
	private JDialog dialog;

	public String getToken() {
		return token;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void commandReceived(WebBrowserCommandEvent arg0) {
	}

	@Override
	public void loadingProgressChanged(WebBrowserEvent arg0) {
	}

	@Override
	public void locationChangeCanceled(WebBrowserNavigationEvent arg0) {
	}

	@Override
	public void locationChanged(WebBrowserNavigationEvent arg0) {
		String url = arg0.getNewResourceLocation();
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			token = matcher.group(1);
			dialog.dispose();
		}
	}

	@Override
	public void locationChanging(WebBrowserNavigationEvent arg0) {

	}

	@Override
	public void statusChanged(WebBrowserEvent arg0) {
	}

	@Override
	public void titleChanged(WebBrowserEvent arg0) {
	}

	@Override
	public void windowClosing(WebBrowserEvent arg0) {
	}

	@Override
	public void windowOpening(WebBrowserWindowOpeningEvent arg0) {
	}

	@Override
	public void windowWillOpen(WebBrowserWindowWillOpenEvent arg0) {
	}

}
