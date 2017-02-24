package com.zl.myappbasicframe.view;

import android.content.Context;
import android.util.AttributeSet;

import com.zl.myappbasicframe.R;

/**
 * Created by Ray on 2017-01-09.
 */

public class ManHeadView extends BaseBodyView {
    public ManHeadView(Context context) {
        this(context, null);
    }


    public ManHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ManHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override protected int initArray() {
        return R.array.man_head;
    }


    @Override protected String initPackage() {
        return mContext.getPackageName();
    }
}
