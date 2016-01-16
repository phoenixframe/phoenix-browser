package org.phoenix.browser;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 * 基于JWebBrowser的默认浏览器
 * @author mengfeiyang
 *
 */
public class DefaultBrowser extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel webBrowserPanel;
	private JWebBrowser webBrowser;

	public DefaultBrowser(String url) {
		super(new BorderLayout());
		webBrowserPanel = new JPanel(new BorderLayout());
		webBrowser = new JWebBrowser();
		webBrowser.navigate(url);
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		add(webBrowserPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		final String url = "http://www.baidu.com";
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(new DefaultBrowser(url),BorderLayout.CENTER);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
			}
		});
		NativeInterface.runEventPump();
	}
}
