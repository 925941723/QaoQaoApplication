package com.qiaoqiao.qaoqaoapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.qiaoqiao.qaoqaoapplication.R;
import com.qiaoqiao.qaoqaoapplication.ui.QAOBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：VULCAN
 * 版本：1.0
 * 创建日期：2016/9/11
 * 描述：
 * 修订历史：
 */

public class LoginActivity extends QAOBaseActivity {

    @BindView(R.id.iv_loginactivity_logo)
    ImageView iv_loginactivity_logo;
    @BindView(R.id.et_loginactivity_account)
    EditText et_loginactivity_account;
    @BindView(R.id.et_loginactivity_key)
    EditText et_loginactivity_key;
    @BindView(R.id.bt_loginactivity_login)
    Button bt_loginactivity_login;
    private boolean isFirstLogin = false;

    @BindView(R.id.ll_loginactivity_inputlayout)
    LinearLayout ll_loginactivity_inputlayout;
    @BindView(R.id.rl_loginactivity_logininglayout)
    RelativeLayout rl_loginactivity_logininglayout;

    @OnClick(R.id.bt_loginactivity_login)
    void login() {
        if (!isFirstLogin) {
            isFirstLogin = true;
            AlphaAnimation animation_out = new AlphaAnimation(1, 0);
            animation_out.setDuration(2000);
            animation_out.setFillAfter(true);
            AlphaAnimation animation_in = new AlphaAnimation(0, 1);
            animation_in.setDuration(2000);
            animation_in.setFillAfter(true);
            ll_loginactivity_inputlayout.startAnimation(animation_out);
            rl_loginactivity_logininglayout.startAnimation(animation_in);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

}
