package com.zl.myappbasicframe.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.zl.myappbasicframe.R;
import com.zl.myappbasicframe.adapter.WorkLineAdapter;
import com.zl.myappbasicframe.beans.WorkLine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Ray on 2017/5/23.
 */

public class DataBindingAcitivty extends BaseActivity {
    private List<WorkLine> workLines;
    private Button bt_change;
    private ListView lv_workline;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_databinding);
        lv_workline = (ListView) findViewById(R.id.lv_workline);
        workLines = new ArrayList<>();
        context = DataBindingAcitivty.this;
//or
//         binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, viewGroup, false);
        for (int i = 0; i < 100; i++) {

            WorkLine workLine = new WorkLine();
            if (i % 2 == 0) {
                workLine.setCompleted(true);
            } else {
                workLine.setCompleted(false);

            }
            workLine.setDistance(i + "");
            workLines.add(workLine);
        }

        final WorkLineAdapter adapter = new WorkLineAdapter(workLines, DataBindingAcitivty.this);
        lv_workline.setAdapter(adapter);
        lv_workline.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
//        showLine(workLine);
        bt_change=(Button)findViewById(R.id.bt_change);
        bt_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workLines.get(0).setCompleted(false);
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void showLine(WorkLine workLine) {

    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadDatas() {

    }
}
