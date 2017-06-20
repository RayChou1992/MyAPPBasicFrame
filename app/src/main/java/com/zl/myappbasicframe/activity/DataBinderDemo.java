package com.zl.myappbasicframe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zl.myappbasicframe.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataBinderDemo extends BaseActivity {
    @BindView(R.id.bt_databinding1)
    Button bt_databinding1;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_databinder_demo);
        context = this;
        ButterKnife.bind(this);

        bt_databinding1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionStart((Activity) context, DataBindingAcitivty.class);
            }
        });
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadDatas() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
