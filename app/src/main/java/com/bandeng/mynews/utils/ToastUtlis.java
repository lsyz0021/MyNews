package com.bandeng.mynews.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者： lcw on 2016/7/13.
 * 博客： http://blog.csdn.net/lsyz0021/
 */
public class ToastUtlis {
	/**
	 * 弹短时间的吐司
	 *
	 * @param context
	 * @param msg
	 */
	public static void show(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 弹长时间的吐司
	 *
	 * @param context
	 * @param msg
	 */
	public static void showLong(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
}
