package com.zl.myappbasicframe.application;

import android.app.Application;
import android.content.Context;

import com.zl.myappbasicframe.utils.ActivityCollector;
import com.zl.myappbasicframe.utils.LogUtils;
import com.zl.myappbasicframe.utils.MyPreference;

import java.util.Map;

/**
 * Created by thinkpad on 2016-04-18.
 */
public class MyApplication extends Application {


    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "JSESSIONID";


    //控制器
    private static ActivityCollector collector;

    private static MyApplication mInstance;

    public static Context context;
    //手机设备串号
    public static String phoneImei;

    //120服务器正式
    public static String BASE_URL = "http://120.27.161.4:8090/smartTransport/app";
    //开发测试环境
//    public  static String BASE_URL = "http://120.27.161.4:8080/smartTransport/app";

    //本地
//    public  static String BASE_URL = "http://10.115.25.185:8080/smartTransport/app";


    /**
     * 暴漏上下文
     *
     * @return
     */
    public static MyApplication getApplication() {
        return mInstance;
    }

    /**
     * @return ActivityCollector
     */
    public static ActivityCollector getActivityCollector() {
        return collector;
    }

    /**
     * @return 返回网络访问队列
     */

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        mInstance = this;
        context = this;
        collector = ActivityCollector.getInstantiate();




        // 在这里为应用设置异常处理程序，然后我们的程序才能捕获未处理的异常
     /*   CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);*/


        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
//        SDKInitializer.initialize(this);

        //初始化ImageLoader,其实就是初始化缓存目录，内存缓存大小，磁盘缓存LV

        LogUtils.isDebug = true;
        MyPreference.GetIntance(context);

    }



    /**
     * 检查返回的Response header中有没有session
     *
     * @param responseHeaders Response Headers.
     */
    public final void checkSessionCookie(Map<String, String> responseHeaders) {
//        if (MyPreference.getString(SESSION_COOKIE, "null").equals("null")) {
        if (responseHeaders.containsKey(SET_COOKIE_KEY)
//                && responseHeaders.get(SET_COOKIE_KEY).startsWith(SESSION_COOKIE)
                ) {
            String cookie = responseHeaders.get(SET_COOKIE_KEY);
            LogUtils.e(cookie);
            //JSESSIONID=v7gqnowumpre1bcj2hoc4pwnw;Path=/smartTransport
            if (cookie.length() > 0) {
                String[] splitCookie = cookie.split(";");
                String[] splitSessionId = splitCookie[0].split("=");
                cookie = splitSessionId[1];
                LogUtils.e(cookie);
                MyPreference.commitString(SESSION_COOKIE, cookie);
            }
        }
//        }


    }

    /**
     * 添加session到Request header中
     */
    public final void addSessionCookie(Map<String, String> requestHeaders) {
        String sessionId = MyPreference.getString(SESSION_COOKIE, "");
        if (sessionId.length() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(SESSION_COOKIE);
            builder.append("=");
            builder.append(sessionId);
            if (requestHeaders.containsKey(COOKIE_KEY)) {
                builder.append("; ");
                builder.append(requestHeaders.get(COOKIE_KEY));
            }
            requestHeaders.put(COOKIE_KEY, builder.toString());
        }
    }

}
