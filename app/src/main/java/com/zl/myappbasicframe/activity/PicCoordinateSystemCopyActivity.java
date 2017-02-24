package com.zl.myappbasicframe.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.zl.myappbasicframe.R;
import com.zl.myappbasicframe.beans.MyPoint;
import com.zl.myappbasicframe.utils.ToastUtil;
import com.zl.myappbasicframe.view.XYImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ray on 2017-01-10.
 */

public class PicCoordinateSystemCopyActivity extends BaseActivity {
    private XYImageView xyImageView;
    private List<MyPoint> points;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pic_coordinate_system);

        points = new ArrayList<>();
        MyPoint myPoint1 = new MyPoint(10, 20);
        MyPoint myPoint2 = new MyPoint(20, 170);
        MyPoint myPoint3 = new MyPoint(30, 40);
        points.add(myPoint1);
        points.add(myPoint2);
        points.add(myPoint3);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.map);
        int height = bitmap.getHeight();
        int width= bitmap.getWidth();

        bitmap.recycle();




        xyImageView = new XYImageView(this, points);
        xyImageView.setBackgroundResource(R.mipmap.map);
        addContentView(xyImageView, new ViewGroup.LayoutParams(width, height));
        xyImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                float x1 = xyImageView.calcAbsolutePointX((int) x);
                float y1 = xyImageView.calcAbsolutePointY((int) y);

                int minX=Collections.min(getAllX(points));
                int maxX=Collections.max(getAllX(points));
                int minY=Collections.min(getAllY(points));
                int maxY=Collections.max(getAllY(points));

                if (x1>minX&&x1<maxX&&y1>minY&&y1<maxY) {
                    ToastUtil.ShowMessage(PicCoordinateSystemCopyActivity.this,"我在这");
                }


                return false;
            }
        });
    }


    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadDatas() {

    }

    /**
     * 根据点 的集合得到所有的X坐标的集合
     *
     * @return
     */

    private List<Integer> getAllX(List<MyPoint> points) {
        List<Integer> xs = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            xs.add(points.get(i).getX());
        }

        return xs;
    }
    /**
     * 根据点 的集合得到所有的Y坐标的集合
     *
     * @return
     */

    private List<Integer> getAllY(List<MyPoint> points) {
        List<Integer> xs = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            xs.add(points.get(i).getY());
        }

        return xs;
    }


}
