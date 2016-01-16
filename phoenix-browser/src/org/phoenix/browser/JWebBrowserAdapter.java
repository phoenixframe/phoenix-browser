package org.phoenix.browser;

import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;
/**
 * 浏览器监听适配器
 * @author mengfeiyang<br>
 *         2011-7-13 下午3:48:55<br>
 * @since 1.0<br>
 * @version 1.0<br>
 * 
 */

public class JWebBrowserAdapter extends WebBrowserAdapter {
    @Override
    public void locationChanged(WebBrowserNavigationEvent e) {
        if (e.getWebBrowser().getLoadingProgress() == 100) {
        	e.getWebBrowser().executeJavascript(Const.JQUERY_LIB + Const.PATH_FINDER_LIB);
        }
    }
    public void windowWillOpen(WebBrowserWindowWillOpenEvent e) {
    	e.getWebBrowser().executeJavascript(Const.JQUERY_LIB + Const.PATH_FINDER_LIB);
    }
}
