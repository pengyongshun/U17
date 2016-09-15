package com.example.u17.module_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class AbsBaseAdapter<T> extends BaseAdapter{

    protected List<T>  data;

    protected Context context;

    protected LayoutInflater inflater;

    public List<T> getData() {
        return data;
    }

    public AbsBaseAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int position) {
        return data!=null?data.get(position):null;
}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public  abstract  View getView(int position, View convertView, ViewGroup parent);
}
