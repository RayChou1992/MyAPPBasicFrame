package com.zl.myappbasicframe.activity;

import android.app.Activity;
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
import com.zl.myappbasicframe.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WifiStateFirstActivity extends Activity {
    private String excelPath;
    private File excelFile;

    private WritableWorkbook wwb;

    WifiManager wifiManager;
    private Button shuaxin, tijao, huanhang;
    private EditText et_juli;
    private ArrayList<ScanResult> list; // 存放周围wifi热点对象的列表
    private String ssidA = "34:a8:4e:c7:c1:30";//zhiren
    private String ssidB = "8c:ab:8e:9c:9a:08";//BTEC
    private String ssidC = "00:3a:9a:dc:8e:80";//ZHIREN
    private String ssidD = "e0:05:c5:b6:76:f0";//7daysinn
    private String ssidE = "00:87:36:3f:63:2e";//zswlf
    TextView mTv;

    String level = "";

    int column = 0;
    int row = 1;
    boolean juliIsWrite=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_state_first);
        excelPath = getExcelDir() + File.separator + "demo.xls";
        excelFile = new File(excelPath);
        createExcel(excelFile);

        shuaxin = (Button) findViewById(R.id.shuaxin);

        mTv = (TextView) findViewById(R.id.tv1);
        tijao = (Button) findViewById(R.id.tijiao);
        huanhang = (Button) findViewById(R.id.huanhang);
        et_juli = (EditText) findViewById(R.id.et_juli);


        huanhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row+=1;
                column=0;
                juliIsWrite=false;
            }
        });
        tijao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String juli = et_juli.getText().toString().trim();
                if (juli.isEmpty()) {
                    ToastUtil.ShowMessage(WifiStateFirstActivity.this, "输入距离");
                } else if (!juliIsWrite){
                    writeToExcel(juli, 0, row);
                    column+=1;
                    juliIsWrite=true;
                }
                if (level.isEmpty()) {
                    ToastUtil.ShowMessage(WifiStateFirstActivity.this, "获取信号强度");

                } else {
                    writeToExcel(level, column, row);
                    column += 1;
                }
            }

        });

        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE); //获得系统wifi服务
        list = (ArrayList<ScanResult>) wifiManager.getScanResults();
        showWifiList(list);

        shuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                mTv.setText("");
                wifiManager.startScan();//
                list = (ArrayList<ScanResult>) wifiManager.getScanResults();
                showWifiList(list);
            }
        });

    }


    /**
     * 依次展示周围wifi的ssid、bssid、capbilities、level
     *
     * @param list 存放周围wifi热点对象的列表
     */
    private void showWifiList(ArrayList<ScanResult> list) {
        for (int i = 0; i < list.size(); i++) {
            String strBssid = list.get(i).BSSID;
            if ("88:25:93:d2:67:c4".equals(strBssid)) {
                String strSsid = list.get(i).SSID;
                String strCapabilities = list.get(i).capabilities;
                int strLevel = list.get(i).level;
                mTv.append("\n" + "SSID: " + strSsid + "\n" + "BSSID: " + strBssid + "\n" + "capabilities： "
                        + strCapabilities + "\n" + "level: " + strLevel + "\n");
                level = Math.abs(strLevel) + "";
            }
        }
    }

//    /**
//     * 将搜索到的wifi根据信号强度从强到时弱进行排序
//     *
//     * @param list 存放周围wifi热点对象的列表
//     */
//    private void sortByLevel(ArrayList<ScanResult> list) {
//
//        Collections.sort(list, new Comparator<ScanResult>() {
//
//            @Override
//            public int compare(ScanResult lhs, ScanResult rhs) {
//                return rhs.level - lhs.level;
//            }
//        });
//    }

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

    public void writeToExcel(String value, int column, int row) {

        try {
            Workbook oldWwb = Workbook.getWorkbook(excelFile);
            wwb = Workbook.createWorkbook(excelFile,
                    oldWwb);
            WritableSheet ws = wwb.getSheet(0);
            Label lbl1 = new Label(column, row, value);
            ws.addCell(lbl1);

            // 从内存中写入文件中,只能刷一次.
            wwb.write();
            wwb.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 创建excel表.
    public void createExcel(File file) {
        WritableSheet ws = null;
        try {
            if (!file.exists()) {
                wwb = Workbook.createWorkbook(file);

                ws = wwb.createSheet("sheet1", 0);

                // 在指定单元格插入数据
                Label lbl1 = new Label(0, 0, "距离");
                Label bll2 = new Label(1, 0, "level1");
                Label bll3 = new Label(2, 0, "level2");
                Label bll4 = new Label(3, 0, "level3");
                Label bll5 = new Label(4, 0, "level4");
                Label bll6 = new Label(5, 0, "level5");
                Label bll7 = new Label(6, 0, "level6");
                Label bll8 = new Label(7, 0, "level7");
                Label bll9 = new Label(8, 0, "level8");
                Label bll10 = new Label(9, 0, "level9");
                Label bll11 = new Label(10, 0, "level10");

                ws.addCell(lbl1);
                ws.addCell(bll2);
                ws.addCell(bll3);
                ws.addCell(bll4);
                ws.addCell(bll5);
                ws.addCell(bll6);
                ws.addCell(bll7);
                ws.addCell(bll8);
                ws.addCell(bll9);
                ws.addCell(bll10);
                ws.addCell(bll11);


                // 从内存中写入文件中
                wwb.write();
                wwb.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
