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

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import jxl.write.WritableWorkbook;

public class WifiStateSecondActivity extends Activity {
    private String filePath1, filePath2, filePath3, filePath4, filePath5, filePath6, filePath7, filePath8;
    private File file1, file2, file3, file4, file5, file6, file7, file8;

    private WritableWorkbook wwb;

    WifiManager wifiManager;
    private Button shuaxin, tijao, huanhang;
    private EditText et_juli;
    private ArrayList<ScanResult> list; // 存放周围wifi热点对象的列表
//    private String ssid1 = "00:3a:9a:dc:8e:80";//ZHIREN
//    private String ssid2 = "00:14:bf:d5:a3:0d";//wuming
//    private String ssid3 = "8c:ab:8e:9c:9a:08";//BTEC
//    private String ssid4 = "34:a8:4e:c7:c1:30";//zhiren
//    private String ssid5 = "00:87:36:3f:63:2e";//zswl
//    private String ssid6 = "88:25:93:d2:67:c4";//D267C4
//    private String ssid7 = "7e:7a:91:05:45:47";//ARay
    private String ssid5 = "50:fa:84:09:05:55";//090555
    private String ssid6 = "88:25:93:d2:67:c4";//D267C4
    private String ssid7 = "88:25:93:cf:ac:82";//CFAC82

    TextView mTv;

    String level = "";

    int column = 0;
    int row = 1;
    boolean juliIsWrite = false;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_state_first);
        filePath1 = getExcelDir() + File.separator + "1.txt";
        filePath2 = getExcelDir() + File.separator + "2.txt";
        filePath3 = getExcelDir() + File.separator + "3.txt";
        filePath4 = getExcelDir() + File.separator + "4.txt";
        filePath5 = getExcelDir() + File.separator + "5.txt";
        filePath6 = getExcelDir() + File.separator + "6.txt";
        filePath7 = getExcelDir() + File.separator + "7.txt";
        filePath8 = getExcelDir() + File.separator + "8.txt";
//        file1=creatFile(filePath1);
//        file2=creatFile(filePath2);
//        file3=creatFile(filePath3);
//        file4=creatFile(filePath4);
        file5=creatFile(filePath5);
        file6=creatFile(filePath6);
        file7=creatFile(filePath7);
//        file8=creatFile(filePath8);
        shuaxin = (Button) findViewById(R.id.shuaxin);

        mTv = (TextView) findViewById(R.id.tv1);
        tijao = (Button) findViewById(R.id.tijiao);
        huanhang = (Button) findViewById(R.id.huanhang);
        et_juli = (EditText) findViewById(R.id.et_juli);


        huanhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                row += 1;
                column = 0;
                juliIsWrite = false;
//                writeToFileWithRow(filePath1, et_juli.getText().toString()+"米");
//                writeToFileWithRow(filePath2, et_juli.getText().toString()+"米");
//                writeToFileWithRow(filePath3, et_juli.getText().toString()+"米");
//                writeToFileWithRow(filePath4, et_juli.getText().toString()+"米");
                writeToFileWithRow(filePath5, et_juli.getText().toString()+"米");
                writeToFileWithRow(filePath6, et_juli.getText().toString()+"米");
                writeToFileWithRow(filePath7, et_juli.getText().toString()+"米");
//                writeToFileWithRow(filePath8, et_juli.getText().toString()+"米");
            }
        });

        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE); //获得系统wifi服务
        list = (ArrayList<ScanResult>) wifiManager.getScanResults();
        showWifiList(list);

        shuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 1;
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
//            if (ssid1.equals(strBssid)) {
//                String str=list.get(i).SSID;
//                int strLevel = list.get(i).level;
//                mTv.append(str+"\n" + "BSSID: " + strBssid + "\n" + "level: " + strLevel + "\n" + "次数" + count);
//                writeToFileWithLine(filePath1, strLevel + "");
//            }
//            if (ssid2.equals(strBssid)) {
//                int strLevel = list.get(i).level;
//                mTv.append("\n" + "BSSID: " + strBssid + "\n" + "level: " + strLevel + "\n" + "次数" + count);
//                writeToFileWithLine(filePath2, strLevel + "");
//            }
//            if (ssid3.equals(strBssid)) {
//                int strLevel = list.get(i).level;
//                mTv.append("\n" + "BSSID: " + strBssid + "\n" + "level: " + strLevel + "\n" + "次数" + count);
//                writeToFileWithLine(filePath3, strLevel + "");
//            }
//            if (ssid4.equals(strBssid)) {
//                int strLevel = list.get(i).level;
//                mTv.append("\n" + "BSSID: " + strBssid + "\n" + "level: " + strLevel + "\n" + "次数" + count);
//                writeToFileWithLine(filePath4, strLevel + "");
//            }
            if (ssid5.equals(strBssid)) {
                int strLevel = list.get(i).level;
                mTv.append("\n" + "BSSID: " + strBssid + "\n" + "level: " + strLevel + "\n" + "次数" + count);
                writeToFileWithLine(filePath5, strLevel + "");
            }
            if (ssid6.equals(strBssid)) {
                int strLevel = list.get(i).level;
                mTv.append("\n" + "BSSID: " + strBssid + "\n" + "level: " + strLevel + "\n" + "次数" + count);
                writeToFileWithLine(filePath6, strLevel + "");
            }
            if (ssid7.equals(strBssid)) {
                int strLevel = list.get(i).level;
                mTv.append("\n" + "BSSID: " + strBssid + "\n" + "level: " + strLevel + "\n" + "次数" + count);
                writeToFileWithLine(filePath7, strLevel + "");
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

    /**
     * 写入内容到SD卡中的txt文本中,在一行
     * str为内容
     */
   public void writeToFileWithLine(String filename, String str) {
       // 每次写入时，都换行写
       String strContent = str + "\t";
        try {
            File f = creatFile(filename);
            RandomAccessFile raf = new RandomAccessFile(f, "rwd");
            raf.seek(f.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 写入内容到SD卡中的txt文本中,换一行
     * str为内容
     */
    public void writeToFileWithRow(String filename, String str) {
        // 每次写入时，都换行写
        String strContent = str + "\r\n";
        try {
            File f = creatFile(filename);
            RandomAccessFile raf = new RandomAccessFile(f, "rwd");
            raf.seek(f.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File creatFile(String filepath) {

        File file = null;
        try {
            file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


}
