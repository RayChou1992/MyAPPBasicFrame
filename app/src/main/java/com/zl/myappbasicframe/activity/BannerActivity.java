package com.zl.myappbasicframe.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zl.myappbasicframe.R;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {
    private Banner banner;
    List<String> images = new ArrayList<String>();

    String[] titles = new String[]{"标题1", "标题2", "标题3", "标题4", "标题5", "标题6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        images.add("http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg");
        images.add("http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg");
        images.add("http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg");
        images.add("http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg");
        images.add("http://120.27.161.4:8090/smartTransport/upload/register/2016-08-04/44f4b279a12560d920b99.jpg");

        banner = (Banner) findViewById(R.id.banner);


        /**
         * 需要什么设置，请看着文档在设置图片和标题前完成设置
         */
        //可以选择设置图片网址，或者资源文件，默认加载框架Glide
        //banner.setImages(images);
        //自定义图片加载框架
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
//                Glide.with(getApplicationContext()).load(url).into(view);
        banner.start();
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //在页面可见时开始轮播，
        //默认的是页面初始化时就开始轮播了，如果你不需要可以再onCreate方法里设置banner.isAutoPlay(false);
        banner.isAutoPlay(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //在页面不可见时停止轮播
        banner.isAutoPlay(false);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }

    }
}
