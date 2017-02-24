package com.zl.myappbasicframe.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zl.myappbasicframe.Constant;
import com.zl.myappbasicframe.R;

import java.io.ByteArrayOutputStream;


public class PicUploadActivity extends Activity implements OnClickListener {
    private ImageView iv_pic;
    private Button bt_base64;
    private String fileName = Constant.SDCARD_PATH + "/myappfile/testpic.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_upload);
        initView();

    }

    private void initView() {
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        bt_base64 = (Button) findViewById(R.id.bt_base64);
        bt_base64.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_base64:
                @SuppressWarnings("static-access")
                Bitmap bitmap = getSmallBitmap(fileName);
//			Bitmap bitmap = new BitmapFactory().decodeFile(fileName);


                iv_pic.setImageBitmap(bitmap);
                uploadPicByBase64(this, bitmap, "");
                break;

            default:
                break;
        }
    }

    /**
     * 通过压缩图片成base64的方式上传图片
     *
     * @param cont      上下文对象
     * @param photodata 图片
     * @param regData
     */
    public static void uploadPicByBase64(final Context cont, Bitmap photodata,
                                         String regData) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // 将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流
            photodata.compress(Bitmap.CompressFormat.PNG, 100, baos);
            baos.close();
            byte[] buffer = baos.toByteArray();
            System.out.println("图片的大小：" + buffer.length);

            // 将图片的字节流数据加密成base64字符输出
            String photo = Base64.encodeToString(buffer, 0, buffer.length,
                    Base64.DEFAULT);

            // photo=URLEncoder.encode(photo,"UTF-8");
            RequestParams params = new RequestParams();
            params.put("photo", photo);
            params.put("name", "picpicpic");// 传输的字符数据
            String url = Constant.URL_UPLOAD_PIC;

            AsyncHttpClient client = new AsyncHttpClient();
            Log.e("tag", params.toString());
            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String content) {
                    Toast.makeText(cont, "头像上传成功!" + content, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Throwable e, String data) {

                    Toast.makeText(cont, "头像上传失败!", Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }
}
