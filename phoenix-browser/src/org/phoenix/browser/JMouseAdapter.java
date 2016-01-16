package org.phoenix.browser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 * 
 * @author mengfeiyang<br>
 */
public class JMouseAdapter extends MouseAdapter {
	private JWebBrowser browser;
	public JMouseAdapter(JWebBrowser browser) {
		this.browser = browser;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (PhoenixBrowser.getIsSpiderTgBtn().isSelected()) {
			browser.executeJavascriptWithResult(Const.SELECTOR_DIV_SHOW);
		} else {
			browser.executeJavascript(Const.SELECTOR_DIV_HIDE);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (PhoenixBrowser.getIsSpiderTgBtn().isSelected()) {
			String selectData = (String) browser.executeJavascriptWithResult(Const.SELECTOR_DIV_SHOW);
			PhoenixBrowser.setLocationJSON(selectData);
			System.out.println(selectData);
		} else {
			browser.executeJavascript(Const.SELECTOR_DIV_HIDE);
		}
	}
}
