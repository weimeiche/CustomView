package com.navyliu.customview.constants;

/**
 * Author: navyLiu
 * Time: 2017-12-07 15:40
 * Description: this is TimeConstants
 * qq :497488219
 */

public class TimeConstants {
	private static long lastClickTime=0;
	/**
	 * 防止重复点击
	 * @return
	 */
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if ( 0 < timeD && timeD < 900) {
			return true;
		}
		lastClickTime = time;
		return false;
	}
}
