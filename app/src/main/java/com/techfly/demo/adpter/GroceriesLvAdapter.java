package com.techfly.demo.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.bean.DemoBean;
import com.techfly.demo.interfaces.ItemClickListener;

import java.util.List;

public class GroceriesLvAdapter extends BaseAdapter {

    private Context mContext;
    private List<DemoBean> listData;
    private LayoutInflater layoutInflater;
    private ItemClickListener mItemClickListener;

    public GroceriesLvAdapter(Context context, List<DemoBean> list) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.listData = list;
    }

    public void addAll(List<DemoBean> info) {
        listData.addAll(info);
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public void clearAll() {
        listData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listData != null ? listData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(final int position, View arg1, ViewGroup arg2) {
        final ViewHolder holder;
        if (arg1 == null) {
            holder = new ViewHolder();
            arg1 = layoutInflater.inflate(R.layout.item_groceries_lv, null);
            holder.item_tv1 = (TextView) arg1.findViewById(R.id.item_tv1);
            holder.item_tv2 = (TextView) arg1.findViewById(R.id.item_tv2);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }
        holder.item_tv1.setText(listData.get(position).getTv1() + "");
        holder.item_tv2.setText(listData.get(position).getTv2() + "");

        /*if(Integer.parseInt(listData.get(position).getSite()) <= 0){
            listData.remove(position);
            notifyDataSetChanged();
        }*/

        return arg1;
    }

    class ViewHolder {
        private TextView item_tv1;
        private TextView item_tv2;
    }
}

