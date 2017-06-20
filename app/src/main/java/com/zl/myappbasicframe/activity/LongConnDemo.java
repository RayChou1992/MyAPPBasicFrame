package com.zl.myappbasicframe.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zl.myappbasicframe.R;
import com.zl.myappbasicframe.mina.MinaService;
import com.zl.myappbasicframe.mina.SessionManager;

public class LongConnDemo extends BaseActivity implements View.OnClickListener{

    private TextView start_service_tv, send_tv, receive_tv;

    private MessageBroadcastReceiver receiver;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mina_demo);

        initView();
        registerBroadcast();
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadDatas() {

    }

    private void registerBroadcast() {
        receiver = new MessageBroadcastReceiver();
        IntentFilter filter = new IntentFilter("com.zl.mina.broadcast");
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    private void initView() {
        receive_tv = (TextView) this.findViewById(R.id.receive_tv);
        start_service_tv = (TextView) this.findViewById(R.id.start_service_tv);
        start_service_tv.setOnClickListener(this);
        send_tv = (TextView) this.findViewById(R.id.send_tv);
        send_tv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.start_service_tv:
                Log.e("tag", "点击启动服务");
                Intent intent = new Intent(this, MinaService.class);
                startService(intent);
                break;
            case R.id.send_tv:
                Log.e("tag", "点击发送消息");
                SessionManager.getInstance().writeToServer("hello123");
                break;
        }
    }

    private void unregisterBroadcast(){
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MinaService.class));
        unregisterBroadcast();

    }

    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            receive_tv.setText(intent.getStringExtra("message"));
        }
    }
}