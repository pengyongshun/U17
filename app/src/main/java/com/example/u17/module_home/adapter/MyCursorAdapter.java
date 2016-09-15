package com.example.u17.module_home.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.u17.R;


/**
 * Created by Eline on 2016/5/26.
 */
public class MyCursorAdapter extends CursorAdapter {

    public MyCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_text_item_view, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String searchName = cursor.getString(cursor.getColumnIndex("search_name"));
        viewHolder.tvRecentSearch.setText(searchName);

    }
    class ViewHolder{
        public TextView tvRecentSearch;
        public ViewHolder(View view) {
            tvRecentSearch = (TextView) view.findViewById(R.id.tv_hint);
        }
    }


}
