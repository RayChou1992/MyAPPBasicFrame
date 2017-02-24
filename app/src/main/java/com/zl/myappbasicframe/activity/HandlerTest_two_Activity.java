package com.zl.myappbasicframe.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.zl.myappbasicframe.R;
import com.zl.myappbasicframe.utils.uploadpic;

public class HandlerTest_two_Activity extends Activity {
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(), "成功", 0).show();
				Intent intent=new Intent(HandlerTest_two_Activity.this,HandlerTestActivity.class);
				HandlerTest_two_Activity.this.setResult(1, intent);
				finish();
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handlertesttwo);
		
		
		
	}
	
	
	public void onClick(View v){
		if (v.getId()==R.id.button1) {
			new uploadpic().uploadPic(handler);
		}
	}
}
