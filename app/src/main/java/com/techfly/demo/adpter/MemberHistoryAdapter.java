package com.techfly.demo.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.bean.MemberHistoryBean;
import com.techfly.demo.interfaces.ItemClickListener;

import java.util.List;

public class MemberHistoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<MemberHistoryBean.DataEntity.DatasEntity> listData;
    private LayoutInflater layoutInflater;
    private ItemClickListener mItemClickListener;

    public MemberHistoryAdapter(Context context, List<MemberHistoryBean.DataEntity.DatasEntity> list) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.listData = list;
    }

    public void addAll(List<MemberHistoryBean.DataEntity.DatasEntity> info) {
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
            arg1 = layoutInflater.inflate(R.layout.item_member_history, null);
            holder.item_phone_tv = (TextView) arg1.findViewById(R.id.item_phone_tv);
            holder.item_number_tv = (TextView) arg1.findViewById(R.id.item_number_tv);
            holder.item_time_tv = (TextView) arg1.findViewById(R.id.item_time_tv);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }
        holder.item_phone_tv.setText(listData.get(position).getMobile() + "");
        holder.item_number_tv.setText(listData.get(position).getCount() + "");

        String[] dateArray = listData.get(position).getCreate_time().split(" ");
        if(dateArray.length > 1){
            holder.item_time_tv.setText(dateArray[0] + "");
        }

        return arg1;
    }

    class ViewHolder {
        private TextView item_phone_tv;
        private TextView item_number_tv;
        private TextView item_time_tv;
    }
}

