package com.zl.myappbasicframe.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.zl.myappbasicframe.beans.MyPoint;
import com.zl.myappbasicframe.utils.LogUtils;
import com.zl.myappbasicframe.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ray on 2017-01-10.
 */

public class XYImageView extends ImageView {

    /**
     * 图片尺寸与屏幕尺寸的比例系数
     */
    private final int NUMBER = 2;
    private Context context;
    /**
     * 控件的中心点
     */

    int centerX, centerY;

    List<MyPoint> points;

    /**
     * 圆心（坐标值是相对与控件的左上角的）
     */
    Point po = new Point();

    public XYImageView(Context context) {
        super(context);
    }

    public XYImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XYImageView(Context context, List<MyPoint> points) {
        super(context);
        this.points = points;
        this.context = context;
    }

    /**
     * 控件创建完成之后，在显示之前都会调用这个方法，此时可以获取控件的大小 并得到中心坐标和坐标轴圆心所在的点。
     */
    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w / 2;
        centerY = h / 2;
        po.set(centerX, centerY);//中心点

        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 自定义控件一般都会重载onDraw(Canvas canvas)方法，来绘制自己想要的图形
     */
    @Override
    public void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth((float) 2.0);
        paint.setTextSize(18);
        paint.setColor(Color.parseColor("#303030"));
        paint.setAntiAlias(true);


        if (canvas != null && points.size() > 1) {
            // 画直线

            canvas.drawLine(0, NUMBER * centerY,
                    NUMBER * centerX, NUMBER * centerY,
                    paint);//x轴

            canvas.drawLine(0, 0, 0, NUMBER * centerY, paint);//y轴
//            drawTriangle(canvas, points);
            draw3D(canvas,2*points.get(0).getX(),2*points.get(0).getY(),
                    2*points.get(1).getX(),2*points.get(1).getY(),
                    2*points.get(2).getX(),2*points.get(2).getY(),
                    2*points.get(3).getX(),2*points.get(3).getY(),100f);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX() / NUMBER;
        float y = event.getY() / NUMBER;
        MyPoint myPoint = new MyPoint((int) x, (int) y);


        if (isInPolygon(myPoint, points, points.size())) {
            ToastUtil.ShowMessage(context, "我在这");
        }

        return super.onTouchEvent(event);
    }

    /**
     * 判断点是否在多边形内
     *
     * @param point
     * @param points
     * @param n
     * @return
     */
    public boolean isInPolygon(MyPoint point, List<MyPoint> points, int n) {
        int nCross = 0;
        for (int i = 0; i < n; i++) {
            MyPoint p1 = points.get(i);
            MyPoint p2 = points.get((i + 1) % n);
            // 求解 y=p.y 与 p1 p2 的交点
            // p1p2 与 y=p0.y平行
            if (p1.getY() == p2.getY())
                continue;
            // 交点在p1p2延长线上
            if (point.getY() < Math.min(p1.getY(), p2.getY()))
                continue;
            // 交点在p1p2延长线上
            if (point.getY() >= Math.max(p1.getY(), p2.getY()))
                continue;
            // 求交点的 X 坐标
            double x = (double) (point.getY() - p1.getY()) * (double) (p2.getX() - p1.getX())
                    / (double) (p2.getY() - p1.getY()) + p1.getX();
            // 只统计单边交点
            if (x > point.getX())
                nCross++;
        }
        LogUtils.e(">>>>>>>>>>>" + nCross);
        return (nCross % 2 == 1);
    }

    /**
     * 画多边形
     */
    private void drawTriangle(Canvas canvas, List<MyPoint> points) {
        Path path = new Path();
        path.moveTo(calcRelativePointX(points.get(0).getX()), calcRelativePointY(points.get(0).getY()));
        for (int i = 1; i < points.size(); i++) {

            path.lineTo(calcRelativePointX(points.get(i).getX()), calcRelativePointY(points.get(i).getY()));
        }
        path.close();

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        // 绘制这个多边形
        canvas.drawPath(path, paint);
    }

    /**
     * 计算相对坐标系的点的X坐标
     *
     * @return
     */
    public float calcRelativePointX(int x) {
        return NUMBER * x;
    }

    /**
     * 计算相对坐标系的点的Y坐标
     *
     * @return
     */
    public float calcRelativePointY(int y) {
        return NUMBER * y;
    }

    /**
     * 根据相对坐标计算绝对坐标
     *
     * @return
     */
    public float calcAbsolutePointX(int x) {
        return x / NUMBER;
    }

    /**
     * 计算相对坐标系的点的Y坐标
     *
     * @return
     */
    public float calcAbsolutePointY(int y) {
        return y / NUMBER;
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

    /**
     * 开始计算立方体每个点坐标.
     * @param canvas
     * @param Xa  LT（L左，R右，T上，B下）
     * @param Ya  LT
     * @param Xb  RT
     * @param Yb  RT
     * @param Xc  RB
     * @param Yc  RB
     * @param Xd  LB
     * @param Yd  LB
     * @param h 高度
     */
    private void draw3D(Canvas canvas, int Xa, int Ya, int Xb, int Yb, int Xc, int Yc, int Xd, int Yd, float h) {
        //计算顶面四个点的坐标(Xa1,Ya1,...)
        float dXa =(float)(Xa + h * 1 / Math.tan(70 * 0.17453293));
        float dYa = (float)(Ya - h * 1 / Math.tan(70 * 0.17453293));

        float dXb = (float)(Xb - h * 1 / Math.tan(70 * 0.17453293));
        float dYb = (float)(Yb - h * 1 / Math.tan(70 * 0.17453293));

        float dXc = (float)(Xc - h * 1 / Math.tan(70 * 0.17453293));
        float dYc = (float)(Yc -h * 1 / Math.tan(70 * 0.17453293));

        float dXd = (float)(Xd + h * 1 / Math.tan(70 * 0.17453293));
        float dYd = (float)(Yd -h * 1 / Math.tan(70 * 0.17453293));

        //点变换,二维转三维.
        //底角四点坐标
        float tXa = (float)(Xa + Ya * 0.0707);
        float tYa = (float)(300-Ya * 0.0707);

        float tXb =(float) (Xb + Yb * 0.0707);
        float tYb = (float)(300 - Yb * 0.0707);

        float tXc = (float)(Xc + Yc * 0.0707);
        float tYc = (float)(300 - Yc * 0.0707);

        float tXd = (float)(Xd + Yd * 0.0707);
        float tYd = (float)(300 - Yd * 0.0707);
        //顶角四点坐标
        float tdXa = (float)(dXa + dYa * 0.707);
        float tdYa =(float) (300 - dYa * 0.707 - h);

        float tdXb = (float)(dXb + dYb * 0.707);
        float tdYb =(float)( 300 - dYb * 0.707 - h);

        float tdXc = (float)(dXc + dYc * 0.707);
        float tdYc = (float)(300 - dYc * 0.707 - h);

        float tdXd = (float)(dXd + dYd * 0.707);
        float tdYd = (float)(300 - dYd * 0.707 - h);


        //连接底角各点
        Path path = new Path();
        Paint paint = new Paint();

        path.moveTo(Xa, Ya);
        path.lineTo(Xb, Yb);
        path.lineTo(Xc, Yc);
        path.lineTo(Xd, Yd);
        path.lineTo(Xa, Ya);
        path.close();

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        // 绘制这个多边形
        canvas.drawPath(path, paint);


        //连接顶角各点
        path.moveTo(dXa, dYa);
        path.lineTo(dXb, dYb);
        path.lineTo(dXc, dYc);
        path.lineTo(dXd, dYd);
        path.lineTo(dXa, dYa);
        path.close();

        paint.setStyle(Paint.Style.STROKE);
//        ctx.fillStyle = "transparent";
        // 绘制这个多边形
        canvas.drawPath(path, paint);


        //连接各个棱
        path.moveTo(Xa, Ya);
        path.lineTo(dXa, dYa);
        path.close();
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);

        path.moveTo(Xb, Yb);
        path.lineTo(dXb, dYb);
        path.close();
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);

        path.moveTo(Xc, Yc);
        path.lineTo(dXc, dYc);
        path.close();
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);

        path.moveTo(Xd, Yd);
        path.lineTo(dXd, dYd);
        path.close();
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);

    }

}
