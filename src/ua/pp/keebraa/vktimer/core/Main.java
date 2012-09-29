package ua.pp.keebraa.vktimer.core;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;

import javax.net.ssl.SSLParameters;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;

import chrriis.common.UIUtils;
import chrriis.common.WebServer.HTTPRequest;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserListener;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;

public class Main extends JPanel {

  public Main() {
    super(new BorderLayout());
    JPanel webBrowserPanel = new JPanel(new BorderLayout());
    webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));
    final JWebBrowser webBrowser = new JWebBrowser();
    webBrowser.navigate("http://api.vk.com/oauth/authorize?client_id=3145529&redirect_uri=http://api.vk.com/blank.html&scope=notify,friends,photos,audio,video,docs,notes,pages,wall,groups,messages,ads&display=page&response_type=token");
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
    add(webBrowserPanel, BorderLayout.CENTER);
    // Create an additional bar allowing to show/hide the menu bar of the web browser.
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
    JCheckBox menuBarCheckBox = new JCheckBox("Menu Bar", webBrowser.isMenuBarVisible());
    menuBarCheckBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        webBrowser.setMenuBarVisible(e.getStateChange() == ItemEvent.SELECTED);
      }
    });
    webBrowser.addWebBrowserListener(new WebBrowserListener() {
		
		@Override
		public void windowWillOpen(WebBrowserWindowWillOpenEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowOpening(WebBrowserWindowOpeningEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WebBrowserEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void titleChanged(WebBrowserEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void statusChanged(WebBrowserEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void locationChanging(WebBrowserNavigationEvent arg0) {
		}
		
		@Override
		public void locationChanged(WebBrowserNavigationEvent arg0) {
			
		}
		
		@Override
		public void locationChangeCanceled(WebBrowserNavigationEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void loadingProgressChanged(WebBrowserEvent arg0) {
			
		}
		
		@Override
		public void commandReceived(WebBrowserCommandEvent arg0) {
			System.out.println("command revieved");
		}
	});
    buttonPanel.add(menuBarCheckBox);
    add(buttonPanel, BorderLayout.SOUTH);
  }

  /* Standard main method to try that test as a standalone application. */
  public static void main(String[] args) {
    UIUtils.setPreferredLookAndFeel();
    NativeInterface.open();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JDialog dialog = new JDialog();
       
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(new Main(), BorderLayout.CENTER);
        dialog.setSize(800, 600);
        dialog.setLocationByPlatform(true);
        dialog.setVisible(true);
        System.out.println("hello");
      }
    });
    NativeInterface.runEventPump();
  }
}