package com.zl.myappbasicframe.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.zl.myappbasicframe.R;

public class EditTextDemo extends Activity {

	private EditText editText1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edittextdemo);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText1.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId==EditorInfo.IME_ACTION_DONE) {
					Toast.makeText(EditTextDemo.this, "********", 1).show();
				}
				return false;
			}
		});
	}
}
