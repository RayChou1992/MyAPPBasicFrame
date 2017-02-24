package com.zl.myappbasicframe.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zl.myappbasicframe.beans.MyPoint;

import java.util.List;

/**
 * Created by Ray on 2017-01-10.
 */

public class XYImageCopyView extends ImageView {
    /**
     * 控件的中心点
     */

    int centerX, centerY;

    List<MyPoint> points;

    /**
     * 圆心（坐标值是相对与控件的左上角的）
     */
    Point po = new Point();

    public XYImageCopyView(Context context) {
        super(context);
    }

    public XYImageCopyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XYImageCopyView(Context context, List<MyPoint> points) {
        super(context);
        this.points = points;
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

        // 画坐标轴
        if (canvas != null) {

            // 画直线

            canvas.drawLine(5, 2 * centerY - 40, 2 * centerX, 2 * centerY - 40, paint);//x轴
            canvas.drawLine(5, 10, 5, 2 * centerY - 40, paint);//y轴
            // 画X轴箭头
            int x = 2 * centerX, y = 2 * centerY - 40;//减去40是为了避免画在最底部，看不到，所以减去40
            drawTriangle(canvas, new Point(x, y), new Point(x - 10, y - 5),
                    new Point(x - 10, y + 5));//三点决定一个三角形，并且填充
            canvas.drawText("90", x - 20, y + 18, paint);
            // 画Y轴箭头
            x = 5;
            y = 0;
            drawTriangle(canvas, new Point(x, y), new Point(x - 5, y + 10),
                    new Point(x + 5, y + 10));
            canvas.drawText("300", x + 12, y + 15, paint);
            // 画中心点坐标
            // 先计算出来当前中心点坐标的值
            String centerString = "(" + (centerX - po.x) / 2 + ","
                    + (po.y - centerY) / 2 + ")";

            // 然后显示坐标
            canvas.drawText(centerString, 2, 2 * centerY - 15, paint);
        }


        if (canvas != null) {
            int size = points.size();
            if (size > 0) {

//从第一个点到最后一个点按顺序链接
                for (int i = 0; i < points.size() - 1; i++) {
                    canvas.drawLine(calcRelativePointX(points.get(i).getX()),
                            calcRelativePointY(points.get(i).getY()),
                            calcRelativePointX(points.get(i + 1).getX()),
                            calcRelativePointY(points.get(i + 1).getY()), paint);
                }
                //链接第一个点和最后一个点
                canvas.drawLine(calcRelativePointX(points.get(0).getX()),
                        calcRelativePointY(points.get(0).getY()),
                        calcRelativePointX(points.get(size - 1).getX()),
                        calcRelativePointY(points.get(size - 1).getY()), paint);
            }
        }

//
//        int size = 2 * centerX - 15;
//        float n = size / 40;
//        canvas.drawLine(p_1.x,
//                p_1.y,
//                p_2.x,
//                p_2.y,
//                paint);//画出点 1和 点2 两点之间的连线


//            canvas.drawLine(p_2.x,
//                    p_2.y,
//                    p_3.x,
//                    p_3.y,
//                    paint);//画出点 2和 点3 两点之间的连线

//            canvas.drawLine(p_1.x,
//                    p_1.y,
//                    p_3.x,
//                    p_3.y,
//                    paint);//画出点 1和 点3 两点之间的连线
//            Point p_1 = new Point(10,20);
//            Point p_2 = new Point(20,170);
//            Point p_3 = new Point(30,40);
//
//            int size=2*centerX-15;
//            float n=size/40;
//            canvas.drawLine(5+n*p_1.x,
//                    2*po.y-40-p_1.y,
//                    5+n*p_2.x,
//                    2*po.y-40-p_2.y,
//                    paint);//画出点 1和 点2 两点之间的连线
//
//
//            canvas.drawLine(5+n*p_2.x,
//                    2*po.y-40-p_2.y,
//                    5+n*p_3.x,
//                    2*po.y-40-p_3.y,
//                    paint);//画出点 2和 点3 两点之间的连线
//
//            canvas.drawLine(5+n*p_1.x,
//                    2*po.y-40-p_1.y,
//                    5+n*p_3.x,
//                    2*po.y-40-p_3.y,
//                    paint);//画出点 1和 点3 两点之间的连线

        //显示该黑点
//        canvas.drawCircle(5 + n * p_1.x, 2 * po.y - 40 - p_1.y, 2, paint);
//        canvas.drawCircle(5 + n * p_2.x, 2 * po.y - 40 - p_2.y, 2, paint);
//        canvas.drawCircle(5 + n * p_3.x, 2 * po.y - 40 - p_3.y, 2, paint);


        // canvas.drawPoint(pa.x+po.x, po.y-pa.y, paint);
    }


    /**
     * 画三角形 用于画坐标轴的箭头
     */
    private void drawTriangle(Canvas canvas, Point p1, Point p2, Point p3) {
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.close();

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        // 绘制这个多边形
        canvas.drawPath(path, paint);
    }

    /**
     * 计算相对坐标系的点的X坐标
     *
     * @return
     */
    public  float calcRelativePointX(int x) {
        int size = 2 * centerX - 15;
        float n = size / 40;
        return 5 + n * x;
    }

    /**
     * 计算相对坐标系的点的Y坐标
     *
     * @return
     */
    public  float calcRelativePointY(int y) {
        return 2 * po.y - 40 - y;
    }
    /**
     * 根据相对坐标计算绝对坐标
     *
     * @return
     */
    public  float calcAbsolutePointX(int x) {
        int size = 2 * centerX - 15;
        float n = size / 40;
        return (x-5)/n;
    }

    /**
     * 计算相对坐标系的点的Y坐标
     *
     * @return
     */
    public  float calcAbsolutePointY(int y) {
        return 2 * po.y - 40 - y;
    }

}
