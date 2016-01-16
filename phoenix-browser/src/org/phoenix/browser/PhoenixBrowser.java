package org.phoenix.browser;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import org.phoenix.style.SetObjectStyle;

import craky.componentc.JCToggleButton;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.DefaultWebBrowserDecorator;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser.WebBrowserDecoratorFactory;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserDecorator;

/**
 * phoenixframework定制的浏览器，用于录制元素的属性
 * @author mengfeiyang
 *
 */
public class PhoenixBrowser{
	private JTextField urlTxf;
	private JButton goBtn;

	private JWebBrowser jwebBrowser;
	private JButton backBtn;
	private JButton forwardBtn;
	private JButton reloadBtn;
	private JButton stopBtn;
	private JButton homePageBtn;
	private JMouseAdapter jMouseAdapter;
	private static JCToggleButton isSpiderTgBtn;
	private static String locationJSON;

	public static String getLocationJSON() {
		return locationJSON;
	}

	public static void setLocationJSON(String locationJSON) {
		PhoenixBrowser.locationJSON = locationJSON;
	}

	public JComponent createContent() {
		JPanel contentPane = new JPanel(new BorderLayout());
		final JWebBrowser webBrowser = new JWebBrowser() {
			private static final long serialVersionUID = 1L;

			@Override
			protected WebBrowserDecorator createWebBrowserDecorator(Component renderingComponent) {
				jwebBrowser = this;
				jMouseAdapter = new JMouseAdapter(this);
				this.addMouseListener(jMouseAdapter);
				this.addWebBrowserListener(new JWebBrowserAdapter());
				return createCustomWebBrowserDecorator(this, renderingComponent);
			}
		};
		JWebBrowser.setWebBrowserDecoratorFactory(new WebBrowserDecoratorFactory() {
					public WebBrowserDecorator createWebBrowserDecorator(JWebBrowser webBrowser, Component renderingComponent) {
						jwebBrowser = webBrowser;
						jMouseAdapter = new JMouseAdapter(webBrowser);
						webBrowser.addMouseListener(jMouseAdapter);
						webBrowser.addWebBrowserListener(new JWebBrowserAdapter());
						return createCustomWebBrowserDecorator(webBrowser,renderingComponent);
					}
				});

		contentPane.add(webBrowser, BorderLayout.CENTER);
		webBrowser.navigate("http://www.hao123.com");
		return contentPane;
	}

	private WebBrowserDecorator createCustomWebBrowserDecorator(
			JWebBrowser webBrowser, Component renderingComponent) {

		return new DefaultWebBrowserDecorator(webBrowser, renderingComponent) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void addMenuBarComponents(WebBrowserMenuBar menuBar) {
				// super.addMenuBarComponents(menuBar);
				/*
				 * JMenu myMenu = new JMenu("[[My Custom Menu]]");
				 * myMenu.add(new JMenuItem("My Custom Item 1")); myMenu.add(new
				 * JMenuItem("My Custom Item 2"));
				 */
				JMenu jMenu = menuBar.getFileMenu();
				jMenu.setText("文件");
				JMenu jMenuHelp = menuBar.getViewMenu();
				jMenuHelp.setText("视图");
				// menuBar.add(jMenu);
				// menuBar.add(jMenuHelp);
			}

			@Override
			protected void addLocationBarComponents(WebBrowserLocationBar locationBar) {
				urlTxf = locationBar.getLocationField();
				urlTxf.setFont(new Font("TimesRoman", Font.PLAIN, 12));
				locationBar.add(urlTxf);

				locationBar.add(getBlackLbl());

				goBtn = locationBar.getGoButton();
				goBtn.setIcon(new ImageIcon("phoenix/images/go.png"));
				goBtn.setToolTipText("前往");
				locationBar.add(goBtn);

				locationBar.add(getBlackLbl());

				isSpiderTgBtn = new JCToggleButton("正常   ");
				locationBar.add(isSpiderTgBtn);
				isSpiderTgBtn.setToolTipText("当前处于正常浏览模式，点击可切换到属性录制模式");
				isSpiderTgBtn.setIcon(new ImageIcon("phoenix/images/internet-web-browser.png"));
				isSpiderTgBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isSpiderTgBtn_select(isSpiderTgBtn);
					}
				});

				locationBar.add(getBlackLbl());

				locationBar.add(new JLabel("  "));
			}

			@Override
			protected void addButtonBarComponents(WebBrowserButtonBar buttonBar) {

				final JButton button = new JButton("[[My Custom Button!]]");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(button,
								"My Custom Button was pressed!");
					}
				});
				/*
				 * buttonBar.add(button);
				 * buttonBar.add(buttonBar.getForwardButton());
				 * buttonBar.add(buttonBar.getReloadButton());
				 * buttonBar.add(buttonBar.getStopButton());
				 */

				buttonBar.add(getBlackLbl());

				backBtn = buttonBar.getBackButton();
				backBtn.setIcon(new ImageIcon("phoenix/images/back.png"));
				backBtn.setToolTipText("返回");
				buttonBar.add(backBtn);
				buttonBar.add(getBlackLbl());

				forwardBtn = buttonBar.getForwardButton();
				forwardBtn.setIcon(new ImageIcon("phoenix/images/forward.png"));
				forwardBtn.setToolTipText("前进");
				buttonBar.add(forwardBtn);

				buttonBar.add(getBlackLbl());

				reloadBtn = buttonBar.getReloadButton();
				reloadBtn.setIcon(new ImageIcon("phoenix/images/reload.png"));
				reloadBtn.setToolTipText("刷新");
				buttonBar.add(reloadBtn);

				buttonBar.add(getBlackLbl());

				stopBtn = buttonBar.getStopButton();
				// stopBtn.setIcon(new ImageIcon("phoenix/images/reload.png"));
				stopBtn.setToolTipText("停止");
				buttonBar.add(stopBtn);

				buttonBar.add(getBlackLbl());

				homePageBtn = new JButton();
				homePageBtn.setIcon(new ImageIcon("phoenix/images/home.png"));
				// homePageBtn.addActionListener(this);
				buttonBar.add(homePageBtn);
				
				homePageBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						homePageBtn_click();
					}
				});

				buttonBar.add(getBlackLbl());
			}
		};
	}

	public static JCToggleButton getIsSpiderTgBtn() {
		return isSpiderTgBtn;
	}

	public void setIsSpiderTgBtn(JCToggleButton isSpiderTgBtn) {
		PhoenixBrowser.isSpiderTgBtn = isSpiderTgBtn;
	}

	private static JLabel getBlackLbl() {
		return new JLabel(" ");
	}

	public void homePageBtn_click() {
		urlTxf.setText(Const.HOMEPAGE);
		jwebBrowser.navigate(Const.HOMEPAGE);
	}

	public JWebBrowser getWebBrowser() {
		return jwebBrowser;
	}

	/**
	 * isSpiderTgBtn
	 * 
	 * @param isSpiderTgBtn
	 */
	protected void isSpiderTgBtn_select(JToggleButton isSpiderTgBtn) {
		boolean isSelected = isSpiderTgBtn.isSelected();
		if (isSelected) {
			jwebBrowser.executeJavascriptWithResult(Const.SELECTOR_DIV_SHOW);
			jwebBrowser.getNativeComponent().addMouseListener(jMouseAdapter);
			isSpiderTgBtn.setText("录制    ");
			isSpiderTgBtn.setIcon(new ImageIcon("phoenix/images/media-record.png"));
			isSpiderTgBtn.setToolTipText("当前处于属性录制模式，点击可切换到正常浏览模式");
		} else {
			jwebBrowser.executeJavascript(Const.SELECTOR_DIV_HIDE);
			jwebBrowser.getNativeComponent().removeMouseListener(jMouseAdapter);
			isSpiderTgBtn.setText("正常    ");
			isSpiderTgBtn.setIcon(new ImageIcon("phoenix/images/internet-web-browser.png"));
			isSpiderTgBtn.setToolTipText("当前处于正常浏览模式，点击可切换到属性录制模式");
		}
		urlTxf.setEnabled(!isSelected);
		backBtn.setEnabled(!isSelected);
		forwardBtn.setEnabled(!isSelected);
		reloadBtn.setEnabled(!isSelected);
		goBtn.setEnabled(!isSelected);
		homePageBtn.setEnabled(!isSelected);
		jwebBrowser.executeJavascript(Const.JQUERY_LIB + Const.PATH_FINDER_LIB);
	}

	public static void main(String args[]) {
		// UIUtils.setPreferredLookAndFeel();
		SetObjectStyle.setFrameTheme();
		NativeInterface.open();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame("Phoenix Framework 专用浏览器");
				frame.setIconImage(Toolkit.getDefaultToolkit().createImage(
						"phoenix/images/browser.png"));
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				PhoenixBrowser browser = new PhoenixBrowser();
				frame.getContentPane().add(browser.createContent(),
						BorderLayout.CENTER);
				frame.setSize(1000, 600);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		NativeInterface.runEventPump();
	}

}
