package com.zl.myappbasicframe.utils;

import android.os.Handler;
import android.os.Message;

public class uploadpic {
	public void uploadPic(final Handler h) {
		final Message msg = h.obtainMessage();

		new Thread() {
			public void run() {
				try {
					Thread.sleep(5000);
					msg.what = 1;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				h.sendMessage(msg);
			};

		}.start();
	}
}
