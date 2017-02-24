package com.zl.myappbasicframe.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {

	private static List<Activity> activitys;

	/**
	 * 获取一个Activity控制器的实例
	 * 
	 * @return
	 */
	public static ActivityCollector getInstantiate() {
		return new ActivityCollector();
	}

	/**
	 * ActivityCollector构造函数
	 */
	public ActivityCollector() {
		// TODO Auto-generated constructor stub
		activitys = new ArrayList<Activity>();
	}

	/**
	 * @描述：移除activity
	 * @param activity
	 */
	public static void removeActivity(Activity activity) {
		if (activitys.contains(activity)) {
			activitys.remove(activity);
			LogUtils.e("退出"+activity.getLocalClassName());
		}
	}

	/**
	 * @描述：添加新的activity到控制器
	 * @param activity
	 */
	public static void addActivity(Activity activity) {
		if (!activitys.contains(activity)) {
			activitys.add(activity);
			LogUtils.e("进入"+activity.getLocalClassName());
		}
	}

	/**
	 * 移除所有的activity
	 */
	public static void removeAll() {
		for (Activity activity : activitys) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

}
