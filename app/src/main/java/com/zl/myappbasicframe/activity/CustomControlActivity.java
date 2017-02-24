package com.zl.myappbasicframe.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.zl.myappbasicframe.R;
import com.zl.myappbasicframe.utils.ToastUtil;

/**
 * Created by Ray on 2017-02-22.
 */
public class CustomControlActivity extends Activity implements View.OnClickListener {

    private Button scale, rotate, alpha, translate, set,valueAnimator;
    Animation scaleAnimation, rotateAnimation, alphaAnimation,
            translateAnimation,
            setAnimation;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_control);
        scale = (Button) findViewById(R.id.scale);
        rotate = (Button) findViewById(R.id.rotate);
        alpha = (Button) findViewById(R.id.alpha);
        translate = (Button) findViewById(R.id.translate);
        set = (Button) findViewById(R.id.set);
        valueAnimator = (Button) findViewById(R.id.valueAnimator);

        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(this);

        scale.setOnClickListener(this);
        rotate.setOnClickListener(this);
        set.setOnClickListener(this);
        alpha.setOnClickListener(this);
        translate.setOnClickListener(this);
        valueAnimator.setOnClickListener(this);

        //缩放、旋转、透明度、平移动画

        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scaleanim);
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotateanim);
        alphaAnimation = new AlphaAnimation(0.8f, 0.2f);
        translateAnimation = new TranslateAnimation(50, 0, 800, 100);
        //动画合集
        setAnimation = AnimationUtils.loadAnimation(this, R.anim.setanim);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
                ToastUtil.ShowMessage(CustomControlActivity.this, "我在这");

                break;
            case R.id.valueAnimator:
                //ValueAnimator
                ValueAnimator animator=ValueAnimator.ofInt(0,400);
                animator.setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int curValue = (int)animation.getAnimatedValue();
                        tv.layout(curValue,curValue,curValue+tv.getWidth(),curValue+tv.getHeight());
                        Log.d("qijian","curValue:"+curValue);
                    }
                });
                animator.start();

                break;
            case R.id.scale:
                tv.startAnimation(scaleAnimation);

                break;
            case R.id.rotate:
                tv.startAnimation(rotateAnimation);

                break;
            case R.id.set:
                tv.startAnimation(setAnimation);

                break;
            case R.id.alpha:
                tv.startAnimation(setAnimation);

                break;
            case R.id.translate:
                tv.startAnimation(setAnimation);

                break;
            default:
                break;
        }
    }
}