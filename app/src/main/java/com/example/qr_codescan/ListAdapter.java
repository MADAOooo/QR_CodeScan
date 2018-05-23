package com.example.qr_codescan;

import android.content.Context;
import android.graphics.Color;
import android.os.ParcelUuid;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import static android.R.id.list;

/**
 * Created by x on 2018/5/9.
 */

public class ListAdapter extends BaseAdapter {

    private List<Hbinfo> list;
    private LayoutInflater inflater;

    public ListAdapter(Context context, List<Hbinfo> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(list!=null){
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Hbinfo hbinfo = (Hbinfo) this.getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            //view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            convertView = inflater.inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textId = (TextView) convertView.findViewById(R.id.text_id);
            viewHolder.textPh = (TextView) convertView.findViewById(R.id.text_ph);
            viewHolder.textCpmc = (TextView) convertView.findViewById(R.id.text_cpmc);
            viewHolder.textDate = (TextView) convertView.findViewById(R.id.text_date);
            viewHolder.textJth = (TextView) convertView.findViewById(R.id.text_jth);
            viewHolder.textCzy = (TextView) convertView.findViewById(R.id.text_czy);
            viewHolder.textLpsl = (TextView) convertView.findViewById(R.id.text_lpsl);
            viewHolder.textBlpsl = (TextView) convertView.findViewById(R.id.text_blpsl);
            convertView.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textId.setText(Integer.toString(hbinfo.getId()));
        viewHolder.textPh.setText(hbinfo.getPh());
        viewHolder.textCpmc.setText(hbinfo.getCpmc());
        viewHolder.textCzy.setText(hbinfo.getCzy());
        viewHolder.textDate.setText(hbinfo.getDate());
        viewHolder.textJth.setText(hbinfo.getJth());
        viewHolder.textLpsl.setText(hbinfo.getLpsl());
        viewHolder.textBlpsl.setText(hbinfo.getBlpsl());

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.parseColor("#90D3FE"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        return convertView;
    }

    public static class ViewHolder {
        public TextView textId;
        public TextView textPh;
        public TextView textCpmc;
        public TextView textDate;
        public TextView textJth;
        public TextView textCzy;
        public TextView textLpsl;
        public TextView textBlpsl;
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }
}
