package com.zl.myappbasicframe.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Log统一管理类
 *
 * @author Ray
 *
 */
public class LogUtils {

	private LogUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isDebug = false;// 是否需要打印bug，可以在application的onCreate函数里面初始化
	private static final String TAG = "coaltransportsystem";

	// 下面四个是默认tag的函数
	public static void i(String msg) {
		if (isDebug) {
			if (!TextUtils.isEmpty(msg)) {
				Log.i(TAG, msg);
			} else {
				Log.i(TAG, "null");

			}
		}
	}

	public static void d(String msg) {
		if (isDebug) {
			if (!TextUtils.isEmpty(msg)) {
				Log.d(TAG, msg);
			} else {
				Log.d(TAG, "null");

			}
		}
	}

	public static void e(String msg) {
		if (isDebug) {
			if (!TextUtils.isEmpty(msg)) {
				Log.e(TAG, msg);
			} else {
				Log.e(TAG, "null");

			}
		}
	}

	public static void v(String msg) {
		if (isDebug) {
			if (!TextUtils.isEmpty(msg)) {
				Log.v(TAG, msg);
			} else {
				Log.v(TAG, "null");

			}
		}
	}

	// 下面是传入自定义tag的函数
	public static void i(String tag, String msg) {
		if (isDebug) {
			if (!TextUtils.isEmpty(msg)) {
				Log.i(tag, msg);
			} else {
				Log.i(tag, "null");

			}
		}
	}

	public static void d(String tag, String msg) {
		if (isDebug) {
			if (!TextUtils.isEmpty(msg)) {
				Log.d(tag, msg);
			} else {
				Log.d(tag, "null");

			}
		}
	}

	public static void e(String tag, String msg) {
		if (isDebug) {
			if (!TextUtils.isEmpty(msg)) {
				Log.e(tag, msg);
			} else {
				Log.e(tag, "null");

			}
		}
	}

	public static void v(String tag, String msg) {
		if (isDebug) {
			if (!TextUtils.isEmpty(msg)) {
				Log.v(tag, msg);
			} else {
				Log.v(tag, "null");

			}
		}
	}
}