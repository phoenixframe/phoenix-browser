package org.phoenix.style;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 * 设置显示元素的显示效果及主题
 * @author M.F.Yang
 *
 */
public class SetObjectStyle {
	/**
	 * 统一定制JLabel的主题
	 * @author mengfeiyang
	 * @param jLabel
	 * @param title
	 */
	public static void SetLabelStyle(JLabel jLabel,String title){		
        jLabel.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jLabel.setText(title);
        jLabel.setToolTipText(title);
        jLabel.setForeground(Color.BLUE);
        jLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);		
	}
	
	public static void setInfoLabelStyle(JLabel jLabel,Color color,String str){
		if(color == Color.BLUE){
			jLabel.setForeground(Color.BLUE);
		}else{
			jLabel.setForeground(color);
		}
		jLabel.setText(str);
		jLabel.setToolTipText(str);
	}
	
	/**
	 * 统一定制Frame主题。<br>
	 * 框架封装了多款主题，集中在ThemeSelect类中，可通过Theme枚举类选择<br>
	 * <em>编写日期：2013年9月11日 11：17</em>
	 * @author mengfeiyang
	 * @since JDK 1.7 及以上
	 */
	public static void setFrameTheme(){
    	JFrame.setDefaultLookAndFeelDecorated(true);
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	try  {
  	      UIManager.setLookAndFeel(ThemeSelect.getTheme(Theme.BusinessBlackSteel));
  	    }  catch  (Exception e) {
  	     e.printStackTrace();
  	    }
	}
}
