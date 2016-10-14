package com.qiaoqiao.qaoqaoapplication.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qiaoqiao.qaoqaoapplication.R;
import com.qiaoqiao.qaoqaoapplication.listener.SocketListener;
import com.qiaoqiao.qaoqaoapplication.socket.QAOClient;
import com.qiaoqiao.qaoqaoapplication.socket.QAOSocket;
import com.qiaoqiao.qaoqaoapplication.ui.QAOBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：VULCAN
 * 版本：1.0
 * 创建日期：2016/9/13
 * 描述：
 * 修订历史：
 */

public class MainActivity extends QAOBaseActivity implements SocketListener{

    @BindView(R.id.tv_mainactivity_show)
    TextView tv_mainactivity_show;
    @BindView(R.id.et_mainactivity_send)
    EditText et_mainactivity_send;
    @BindView(R.id.bt_mainactivity_send)
    Button bt_mainactivity_send;
    @BindView(R.id.bt_mainactivity_clean)
    Button btMainactivityClean;

    private StringBuilder content = new StringBuilder();
    private QAOSocket qaoSocket = null;
    private QAOClient qaoClient;


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_mainactivity_show.setText(content);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        initSocket();
    }

    @OnClick({R.id.tv_mainactivity_show, R.id.et_mainactivity_send, R.id.bt_mainactivity_send,
            R.id.bt_mainactivity_clean, R.id.bt_mainactivity_reconnect})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_mainactivity_show:
                break;
            case R.id.et_mainactivity_send:
                break;
            case R.id.bt_mainactivity_send:
                String sendString = et_mainactivity_send.getText().toString();
//                qaoSocket.send(sendString);
                qaoClient.send(sendString);
                break;
            case R.id.bt_mainactivity_clean:
                content.delete(0, content.length());
                handler.sendMessage(handler.obtainMessage());
                break;
            case R.id.bt_mainactivity_reconnect:
//                qaoSocket.stop();
                initSocket();
                break;
        }
    }

    private void initSocket(){
//        qaoSocket = new QAOSocket(this);
//        qaoSocket.start();
        new Thread(){
            @Override
            public void run() {
                super.run();
                qaoClient = new QAOClient();
                qaoClient.connect();
            }
        }.start();
    }

    @Override
    public void onDataCallback(String string) {
        content.append(string);
        handler.sendMessage(handler.obtainMessage());
    }
}
