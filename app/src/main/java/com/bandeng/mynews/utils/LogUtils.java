package com.bandeng.mynews.utils;

import android.util.Log;

/**
 * Log的工具类
 * 作者： lcw on 2016/7/12.
 * 博客： http://blog.csdn.net/lsyz0021/
 */
public class LogUtils {
	// 是否显示log，true 显示 ，false 不显示
	private static boolean isShow = true;

	/**
	 * v类型的log
	 *
	 * @param tag 标签
	 * @param msg 内容
	 */
	public static void e(String tag, String msg) {

		if (isShow)
			Log.e(tag, msg);
	}

}
