package com.qiaoqiao.qaoqaoapplication.asynctask;

import android.os.AsyncTask;

/**
 * 作者：VULCAN
 * 版本：1.0
 * 创建日期：2016/9/20
 * 描述：
 * 修订历史：
 */

public abstract class SimpleTask extends AsyncTask {

    @Override
    protected abstract Object doInBackground(Object[] params);

    public void execute(){
        this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}
