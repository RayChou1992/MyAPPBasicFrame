package com.zl.myappbasicframe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.zl.myappbasicframe.R;


/**
 * Created by Ray on 2016-09-08.
 */
public class IconFontTextView extends TextView {
    public IconFontTextView(Context context) {
        super(context);
    }

    public IconFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IconFontTextView);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.IconFontTextView_value:
                    String value = a.getString(attr);
                    setText(value);
                    Log.d("tag", "value : " + value);
                    break;
                case R.styleable.IconFontTextView_fontFile:
                    String fontFile = a.getString(attr);
                    Log.d("tag", "fontFile : " + fontFile);
                    try {
                        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontFile);
                        setTypeface(typeface);
                    } catch (Throwable e) {

                    }
                    break;
            }
        }
        a.recycle();
    }
}
