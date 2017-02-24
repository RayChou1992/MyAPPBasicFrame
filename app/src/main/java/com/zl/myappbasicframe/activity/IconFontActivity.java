package com.zl.myappbasicframe.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zl.myappbasicframe.R;
import com.zl.myappbasicframe.utils.FontHelper;

public class IconFontActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_font);
        TextView iconLabel = (TextView) findViewById(R.id.iconLabel);
        Typeface font = Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf");
        iconLabel.setTypeface(font);
        iconLabel.setTextSize(40f);
        iconLabel.setTextColor(Color.parseColor("#eb4f38"));

        FontHelper.applyFont(this, findViewById(R.id.iconLabel2), "iconfont/iconfont.ttf");
    }
}
