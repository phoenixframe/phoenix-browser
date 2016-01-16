package org.phoenix.browser;

import org.nutz.lang.Files;

/**
 * 常量配置
 * @author mengfeiyang<br>
 *         2014-1-8  下午‏‎9:12:05<br>
 * @since 1.0<br>
 * @version 1.0<br>
 * 
 */
public interface Const {
    public static final String FILE_SEP = System.getProperty("file.separator");
    public static final String HOMEPAGE="http://www.baidu.com";
    public static final String USER_DIR = System.getProperty("user.dir");
    public final static String JQUERY_LIB = Files.read("phoenix/js/jquery-1.11.3.min.js");
    public final static String PATH_FINDER_LIB = Files.read("phoenix/js/pathFinder-1.0.7.js");
    public final static String SELECTOR_DIV_SHOW = "return mouseClickSelect();";
    public final static String SELECTOR_DIV_HIDE = "hideHighLight();";
    public static final String IMAGE_DIR = Const.class.getResource("/images/").toString().substring(6);
}
