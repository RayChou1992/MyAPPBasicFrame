package com.zl.myappbasicframe;

import android.os.Environment;


public class Constant {
	public static String SDCARD_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	//10.115.25.41


	public final static String  URL_UPLOAD_PIC="http://192.168.23.1:8080/MyAppsServer/servlet/PicUploadServlet";
}
