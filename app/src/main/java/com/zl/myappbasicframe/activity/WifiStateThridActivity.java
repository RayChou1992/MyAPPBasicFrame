package com.zl.myappbasicframe.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zl.myappbasicframe.R;
import com.zl.myappbasicframe.userpremissions.PermissionChecker;
import com.zl.myappbasicframe.userpremissions.PermissionsActivity;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

public class WifiStateThridActivity extends Activity {
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int REQUEST_CODE = 0; // 请求码
    private PermissionChecker mPermissionChecker; // 权限检测器

    WifiManager wifiManager;
    private Button shuaxin;
    private EditText et_juli;
    private ArrayList<ScanResult> list; // 存放周围wifi热点对象的列表

    private String ssid5 = "88:25:93:d2:67:c4";//D267C4
    private String ssid6 = "88:25:93:cf:ac:82";//CFAC82
    private String ssid7 = "50:fa:84:09:05:55";//090555

    TextView mTv;

    private double levelA = -100, levelB = -100, levelC = -100;
    //指纹库
    private List<Float> fingerprintA, fingerprintB, fingerprintC;
//    //存放实时获取的各设备的RSS值
//    List<Double> mLevelListA, mLevelListB, mLevelListC;

    //存放RSS值与指纹库比对关系
    List<Double> mList;
    //最小值
    private double minValue = 100;
    //最小值的位置。即是算出的设备位置
    private int position = 0;
    //存放每次的最小值位置，以便求平均值
    List<Integer> mPositionList;
    private double TruePosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_state_first);
        //为应用获取用户权限
        mPermissionChecker = new PermissionChecker(this);
        // 缺少权限时, 进入权限配置页面
        if (mPermissionChecker.lacksPermissions(PERMISSIONS)) {
            PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
        }

        shuaxin = (Button) findViewById(R.id.shuaxin);
        mTv = (TextView) findViewById(R.id.tv1);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE); //获得系统wifi服务
        list = (ArrayList<ScanResult>) wifiManager.getScanResults();
        mList = new ArrayList<>();
        fingerprintA = new ArrayList<>();
        fingerprintB = new ArrayList<>();
        fingerprintC = new ArrayList<>();
//        mLevelListA = new ArrayList<>();
//        mLevelListB = new ArrayList<>();
//        mLevelListC = new ArrayList<>();
        mPositionList = new ArrayList<>();

        readExcel();




        shuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        //这儿是耗时操作，完成之后更新UI；
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                mPositionList.clear();
                                for (int i = 0; i < 5; i++) {
                                    list.clear();
                                    mList.clear();
                                    position = 0;
                                    minValue = 100;

                                    mTv.setText("");
                                    wifiManager.startScan();
                                    list = (ArrayList<ScanResult>) wifiManager.getScanResults();
                                    showWifiList(list);
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }

                                TruePosition = getAverage(mPositionList);
                                mTv.append("位置在：" + TruePosition + "米");

//                                    levelA = getAverage(mLevelListA);
//                                    levelB = getAverage(mLevelListB);
//                                    levelC = getAverage(mLevelListC);


                            }

                        });
                    }
                }.start();
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        shuaxin.performClick();
    }

    /**
     * 依次展示周围wifi的ssid、bssid、capbilities、level
     *
     * @param list 存放周围wifi热点对象的列表
     */
    private void showWifiList(ArrayList<ScanResult> list) {
        for (int i = 0; i < list.size(); i++) {
            String strBssid = list.get(i).BSSID;
            String strSsid = list.get(i).SSID;
            if (ssid5.equals(strBssid)) {
                levelA = list.get(i).level;
//                mTv.append("SSID: " + strSsid + "\n" + "BSSID: " + strBssid + "\n" + "level: " + levelA + "\n");
            }
            if (ssid6.equals(strBssid)) {
                levelB = list.get(i).level;
//                mTv.append("SSID: " + strSsid + "\n" + "BSSID: " + strBssid + "\n" + "level: " + levelB + "\n");
            }
            if (ssid7.equals(strBssid)) {
                levelC = list.get(i).level;
//                mTv.append("SSID: " + strSsid + "\n" + "BSSID: " + strBssid + "\n" + "level: " + levelC + "\n");
            }
        }

        calcDistance(levelA, levelB, levelC);

//        mLevelListA.add(levelA);
//        mLevelListB.add(levelB);
//        mLevelListC.add(levelC);


    }

    /**
     * 对每个信号源取多次样，求平均值
     *
     * @param list
     * @return
     */
    public double getAverage(List<Integer> list) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).doubleValue();
        }
        return sum / list.size();
    }

    /**
     * 计算距离
     *
     * @param levelA
     * @param levelB
     * @param levelC
     */
    private void calcDistance(double levelA, double levelB, double levelC) {
        mList = new ArrayList<>();

        for (int i = 0; i < fingerprintA.size(); i++) {
            double a = Math.sqrt(Math.pow((levelA - fingerprintA.get(i).floatValue()), 2)
                    + Math.pow((levelB - fingerprintB.get(i).floatValue()), 2)
                    + Math.pow((levelC - fingerprintC.get(i).floatValue()), 2));
            mList.add(a);

        }
        for (int i = 0; i < mList.size(); i++) {
            if (minValue > mList.get(i)) {
                minValue = mList.get(i);
                position = i;
            }

        }
//        mTv.append("最小值：" + minValue);
//        mTv.append("位置在：" + (position + 1) + "米");

        mPositionList.add(position + 1);

    }

    public void readExcel() {
        try {
            InputStream is = getAssets().open("test.xls");
            Workbook book = Workbook.getWorkbook(is);
            int num = book.getNumberOfSheets();
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            int Cols = sheet.getColumns();
            mTv.append("the name of sheet is " + sheet.getName() + "\n");
            mTv.append("total rows is " + Rows + "\n");
            mTv.append("total cols is " + Cols + "\n");
            for (int i = 1; i < Cols; i++) {

                for (int j = 1; j < Rows; j++) {
                    if (i == 1) {
                        fingerprintA.add(Float.valueOf(sheet.getCell(i, j).getContents()));
                    }
                    if (i == 2) {
                        fingerprintB.add(Float.valueOf(sheet.getCell(i, j).getContents()));
                    }
                    if (i == 3) {
                        fingerprintC.add(Float.valueOf(sheet.getCell(i, j).getContents()));
                    }
                }
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取Excel文件夹
    public String getExcelDir() {
        // SD卡指定文件夹
            String sdcardPath = Environment.getExternalStorageDirectory()
                .toString();
        File dir = new File(sdcardPath + File.separator + "Excel"
                + File.separator + "Wifi_Distance");

        if (dir.exists()) {
            return dir.toString();

        } else {
            dir.mkdirs();
            Log.d("BAG", "保存路径不存在,");
            return dir.toString();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }

}



