package com.zl.myappbasicframe.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.zl.myappbasicframe.R;
import com.zl.myappbasicframe.handle.PhotoHandler;
import com.zl.myappbasicframe.userpremissions.PermissionChecker;
import com.zl.myappbasicframe.userpremissions.PermissionsActivity;
import com.zl.myappbasicframe.utils.ToastUtil;

import java.io.IOException;

import static com.zl.myappbasicframe.application.MyApplication.phoneImei;

;

public class MainActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_PHONE_STATE,
    };
    private static final int REQUEST_CODE = 0; // 请求码
    private PermissionChecker mPermissionChecker; // 权限检测器


    private String[] menuList;
    private GridView gv_menu;
    private Camera camera;
    SensorManager sm;


    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        initView();
        //为应用获取用户权限
        mPermissionChecker = new PermissionChecker(this);
        // 缺少权限时, 进入权限配置页面
        if (mPermissionChecker.lacksPermissions(PERMISSIONS)) {
            PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
        } else {
            // 获取手机的IMEI号
            TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            phoneImei = TelephonyMgr.getDeviceId();

            ToastUtil.ShowMessage(MainActivity.this, "phoneImei="+phoneImei);
        }


        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_menu, R.id.tv_menu_name, getMenuList());
        gv_menu.setAdapter(adapter);
        gv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intentJump(MainActivity.this, Widget_Notification_Activity.class);
                        break;
                    case 1:
                        intentJump(MainActivity.this, PicUploadActivity.class);
                        break;
                    case 2:
                        intentJump(MainActivity.this, HandlerTestActivity.class);
                        break;
                    case 3:
                        intentJump(MainActivity.this, CameraDemoActivity.class);
                        break;
                    case 4:
                        intentJump(MainActivity.this, PhotoWallDemo.class);
                        break;
                    case 5:
                        intentJump(MainActivity.this, PhotoWaterFallDemo.class);
                        break;
                    case 6:
                        intentJump(MainActivity.this, EditTextDemo.class);
                        break;
                    case 7:
                        intentJump(MainActivity.this, SenserListenerActivity.class);
                        break;
                    case 8:
                        if (camera != null) {
                            SurfaceView dummy = new SurfaceView(getBaseContext());
                            try {
                                camera.setPreviewDisplay(dummy.getHolder());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            camera.startPreview();

                            camera.takePicture(null, null, new PhotoHandler(
                                    getApplicationContext()));
                        }
                        break;
                    case 9:
                        intentJump(MainActivity.this, BannerActivity.class);
                        break;
                    case 10:
                        intentJump(MainActivity.this, IconFontActivity.class);
                        break;
                    case 11:
                        intentJump(MainActivity.this, PictureClickActivity.class);
                        break;
                    case 12:
                        intentJump(MainActivity.this, OrderSelectActivity.class);
                        break;
                    case 13:
                        intentJump(MainActivity.this, PicCoordinateSystemActivity.class);
                        break;
                    case 14:
                        intentJump(MainActivity.this, CustomControlActivity.class);
                        break;
                    case 15:
                        intentJump(MainActivity.this, WifiStateFirstActivity.class);
                        break;
                }
            }
        });


    }

    private String[] getMenuList() {
        menuList = new String[]{"控件消息提醒", "图片上传", "HandlerTest",
                "CameraDemo", "照片墙", "瀑布流", "EditTextDemo",
                "加速度感应器", "隐式拍照",
                "广告轮播", "图标字体",
                "图片点击", "选座位", "图片坐标系", "自定义view","wifi强度"};

        return menuList;
    }

    private void initView() {
        gv_menu = (GridView) findViewById(R.id.menu);
        camera = openFacingBackCamera();


    }


    public void intentJump(Activity context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        startActivity(intent);
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    private Camera openFacingBackCamera() {
        Camera cam = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int camIdx = 0, cameraCount = Camera.getNumberOfCameras(); camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                try {
                    cam = Camera.open(camIdx);
                    Camera.Parameters params = cam.getParameters();
                    params.setPictureFormat(PixelFormat.JPEG);
                    params.set("rotation", 90);
                    cam.setParameters(params);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }

        return cam;
    }

    @Override
    public void onDestroy() {

        if (camera != null) {
            camera.release();
            camera = null;
        }
        super.onDestroy();

    }


    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadDatas() {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        } else {
            // 获取手机的IMEI号
            TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            phoneImei = TelephonyMgr.getDeviceId();
            ToastUtil.ShowMessage(MainActivity.this, "phoneImei="+phoneImei);

        }
    }

}
