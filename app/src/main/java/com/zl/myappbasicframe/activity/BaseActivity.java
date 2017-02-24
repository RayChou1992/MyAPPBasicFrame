package com.zl.myappbasicframe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zl.myappbasicframe.R;
import com.zl.myappbasicframe.application.MyApplication;
import com.zl.myappbasicframe.utils.ActivityCollector;

public abstract class BaseActivity extends Activity {
    /**
     * 请求列表管理器
     */
    protected Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        MyApplication.getActivityCollector().addActivity(this);
        initViews(savedInstanceState);
        initVariables();
        loadDatas();
    }

    /**
     * 加载layout布局文件，初始化控件，为控件设置事件方法
     */
    protected abstract void initViews(Bundle savedInstanceState);

    /**
     * 初始化变量，包括Intent带的数据和Activity自身变量。
     */
    protected abstract void initVariables();

    /**
     * 获取初始化数据
     */
    protected abstract void loadDatas();


    protected void onDestroy() {
        //在activity销毁的时候同时设置停止请求，停止线程请求回调

        ActivityCollector.removeActivity(this);
        //在activity销毁的时候同时设置停止handler消息处理
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

    protected void onPause() {
        /**
         * 在activity停止的时候同时设置停止请求，停止线程请求回调
         */
        super.onPause();
    }


    /**
     * @param context  上下文
     * @param activity 新的activity类
     * @描述：启动新的activity
     */
    public void actionStart(Activity context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        startActivity(intent);
        context.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    /**
     * @param context
     * @param activity
     * @param bundle
     * @描述：启动新的activity，按照指定的动画
     */
    public void actionStart(Activity context, Class<?> activity, Bundle bundle) {
        Intent intent = new Intent(context, activity);
        intent.putExtras(bundle);
        startActivity(intent);
        context.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    /**
     * 启动新的activity forresult
     *
     * @param context
     * @param activity
     * @param requestCode
     */
    public void actionForResult(Activity context, Class<?> activity, int requestCode) {
        Intent intent = new Intent(context, activity);
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    /**
     * @param context
     * @param activity
     * @param bundle
     * @param requestCode
     * @描述 start new activity
     */
    public void actionForResult(Activity context, Class<?> activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, activity);
        intent.putExtras(bundle);
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    /**
     * @描述：end activity
     */
    public void finishActivity(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

}
