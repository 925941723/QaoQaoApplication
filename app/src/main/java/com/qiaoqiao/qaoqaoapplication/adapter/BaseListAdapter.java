package com.qiaoqiao.qaoqaoapplication.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 作者：VULCAN
 * 版本：1.0
 * 创建日期：2016/10/13
 * 描述：adapter父类，简化adapter，ViewHolder的使用
 * 修订历史：
 */

public abstract class BaseListAdapter<T> extends BaseAdapter {

    private List<T> list;
    public LayoutInflater layoutInflater;

    public BaseListAdapter(Context context, List<T> list) {
        super();
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    public List<T> getList(){
        return this.list;
    }

    @Override
    public int getCount() {
        if (null!=list) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = bindView(position, convertView, parent, new ViewHolder());
        return convertView;
    }

    public abstract View bindView(int position, View convertView, ViewGroup parent, ViewHolder viewHolder);

    public class ViewHolder{
        public SparseArray<View> views = new SparseArray<>();

        public <T extends View> T get(View convertView, int resId){
            View view = views.get(resId);
            if (null == view){
                view = convertView.findViewById(resId);
                views.put(resId, view);
            }
            return (T)view;
        }
    }
}
