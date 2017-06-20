package com.zl.myappbasicframe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.zl.myappbasicframe.R;
import com.zl.myappbasicframe.view.PickerView;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */

public class HorizontalPicker extends Activity {
    PickerView minute_pv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_picker);
        minute_pv = (PickerView) findViewById(R.id.minute_pv);
        List<String> data = new ArrayList<String>();
        for (int i = 61; i < 120; i++) {
            data.add("" + i);//添加da
        }
        minute_pv.setData(data);
        minute_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                Toast.makeText(HorizontalPicker.this, "选择了 " + text + " 分",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}