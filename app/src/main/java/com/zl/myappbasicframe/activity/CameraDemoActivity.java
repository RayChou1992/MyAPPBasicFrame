package com.zl.myappbasicframe.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;

import com.zl.myappbasicframe.R;

public class CameraDemoActivity extends Activity{
	
	private Button bt;
	private SurfaceView sv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cameraactivity);
		bt=(Button)findViewById(R.id.button);
		sv=(SurfaceView)findViewById(R.id.surfaceview);
	}
}
