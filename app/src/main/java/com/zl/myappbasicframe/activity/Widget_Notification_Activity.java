package com.zl.myappbasicframe.activity;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.zl.myappbasicframe.R;

public class Widget_Notification_Activity extends Activity {

	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_notifications);
		tv = (TextView) findViewById(R.id.text);
		BadgeView badgeView = new com.jauker.widget.BadgeView(this);
		badgeView.setTargetView(tv);
		badgeView.setBadgeCount(3);
		badgeView.setBadgeGravity(Gravity.BOTTOM | Gravity.RIGHT);
		tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Drawable aDrawable = getResources().getDrawable(
						R.mipmap.camera);
				aDrawable.setBounds(0, 0, aDrawable.getMinimumWidth(),
						aDrawable.getMinimumHeight());
				tv.setCompoundDrawables(null, null, null, aDrawable);
			}
		});

	}

}
