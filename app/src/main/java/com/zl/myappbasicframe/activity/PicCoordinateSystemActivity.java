package com.zl.myappbasicframe.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;

import com.zl.myappbasicframe.R;
import com.zl.myappbasicframe.beans.MyPoint;
import com.zl.myappbasicframe.view.XYImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ray on 2017-01-10.
 */

public class PicCoordinateSystemActivity extends BaseActivity {
    private XYImageView xyImageView;
    private List<MyPoint> points;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pic_coordinate_system);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.map);
        final int height = bitmap.getHeight();
        final int width = bitmap.getWidth();

        bitmap.recycle();

        points = new ArrayList<>();
        MyPoint myPoint1 = new MyPoint(122, 388);
        MyPoint myPoint2 = new MyPoint(241, 378);
        MyPoint myPoint3 = new MyPoint(251, 501);
        MyPoint myPoint4 = new MyPoint(133, 507);
        points.add(myPoint1);
        points.add(myPoint2);
        points.add(myPoint3);
        points.add(myPoint4);


        xyImageView = new XYImageView(this, points);
        xyImageView.setBackgroundResource(R.mipmap.map);
        addContentView(xyImageView, new ViewGroup.LayoutParams(width, height));
    }


    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadDatas() {

    }





}
