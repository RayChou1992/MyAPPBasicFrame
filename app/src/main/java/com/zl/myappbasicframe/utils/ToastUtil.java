package com.zl.myappbasicframe.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


public class ToastUtil {
	private static Toast toast = null;


	public static void ShowMessage(Context context , String message){
		toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
	}
	public static void ShowMessage(Context context , int message){
		toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
	}
	public static void ShowMessageCenter(Context context , String message){
		toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	public static void ShowMessageCenter(Context context , int message){
		toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
