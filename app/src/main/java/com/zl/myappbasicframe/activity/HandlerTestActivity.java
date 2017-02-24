package com.zl.myappbasicframe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zl.myappbasicframe.R;


public class HandlerTestActivity extends Activity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handlertest);

	}

	public void onClick(View v) {
		if (v.getId() == R.id.button1) {
			Intent intent = new Intent(this, HandlerTest_two_Activity.class);
			startActivityForResult(intent, 0);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode==0&&resultCode==1) {
			Toast.makeText(getApplicationContext(), "成功", 0).show();
			
		}
	}
}
