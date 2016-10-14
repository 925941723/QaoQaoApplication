package com.qiaoqiao.qaoqaoapplication.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * 作者：VULCAN
 * 版本：1.0
 * 创建日期：2016/9/11
 * 描述：
 * 修订历史：
 */

public abstract class QAOBaseActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void initView();

    public abstract void initData();

    public abstract void initEvent();
}
