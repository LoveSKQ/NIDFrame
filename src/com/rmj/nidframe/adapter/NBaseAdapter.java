package com.rmj.nidframe.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Adapter基类，通用方法已经实现，重点关注getView方法即可
 * Created by G11 on 2014/9/12.
 */
public class NBaseAdapter<T> extends BaseAdapter {

    List<T> mList;

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
