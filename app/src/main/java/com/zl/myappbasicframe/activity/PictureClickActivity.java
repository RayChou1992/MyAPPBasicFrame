package com.zl.myappbasicframe.activity;

import android.os.Bundle;

import com.zl.myappbasicframe.R;
import com.zl.myappbasicframe.utils.ToastUtil;
import com.zl.myappbasicframe.view.ManHeadView;

/**
 * Created by Ray on 2017-01-09.
 */
public class PictureClickActivity extends BaseActivity{

    private ManHeadView mImgHead;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pic_click);
        mImgHead=(ManHeadView)findViewById(R.id.img_head);
        mImgHead.setOnBodyClickListener(new ManHeadView.OnBodyClickListener() {
            @Override public void onBodyClick(int position, String[] keys) {
                String message  ="Code:" + keys[0] + ", Name:" + keys[1];
                ToastUtil.ShowMessage(PictureClickActivity.this, message);
            }
        });
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadDatas() {

    }
}
