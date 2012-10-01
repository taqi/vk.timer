package ua.pp.keebraa.vktimer.api.impl;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.tools.Diagnostic;

import ua.pp.keebraa.vktimer.api.WebBrowserUrlListener;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class JDialogAccessTokenGetter {
	private String accessToken;
	private final WebBrowserUrlListener listener = new WebBrowserUrlListener();

	public String getAccessToken() {
		if (accessToken == null) {
			accessToken = askForToken();
		}
		return accessToken;
	}

	private String askForToken() {
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JDialog dialog = new JDialog();
				listener.setDialog(dialog);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.getContentPane().add(buildWebBrowserPanel(),
						BorderLayout.CENTER);
				dialog.setSize(1024, 768);
				dialog.setLocationByPlatform(true);
				dialog.setVisible(true);
			}
		});
		NativeInterface.runEventPump();
		return listener.getToken();
	}

	private JPanel buildWebBrowserPanel() {
		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		webBrowserPanel.setBorder(BorderFactory
				.createTitledBorder("Native Web Browser component"));
		final JWebBrowser webBrowser = new JWebBrowser();
		webBrowser
				.navigate("http://api.vk.com/oauth/authorize?client_id=3145529&redirect_uri=http://api.vk.com/blank.html&scope=notify,friends,photos,audio,video,docs,notes,pages,wall,groups,messages,ads&display=page&response_type=token");
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		webBrowser.addWebBrowserListener(listener);
		return webBrowserPanel;
	}
}
