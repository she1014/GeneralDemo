package com.techfly.demo.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.interfaces.ItemMultClickListener;

import java.util.ArrayList;
import java.util.List;

/*
 * 选择列表
 */
public class SelectorLvAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> listData;
//    private List<T> mDatas;
    private LayoutInflater layoutInflater;
    private ItemMultClickListener mItemClickListener;

    private List<Integer> countList = new ArrayList<Integer>();//解决ListView重复数据问题

    public void setItemClickListener(ItemMultClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public SelectorLvAdapter(Context context, List<T> list) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.listData = list;
    }

    public void addAll(List<T> info) {
        listData.addAll(info);
        notifyDataSetChanged();
    }

    public void clearAll() {
        listData.clear();
        notifyDataSetChanged();
    }

    public int getCurrentId(int position){
        return position;
    }

    @Override
    public int getCount() {
        return listData != null ? listData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_center_lv, null);

            holder.title_tv = (TextView) convertView.findViewById(R.id.item_descrip_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title_tv.setText(listData.get(position).toString());

        holder.title_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(holder.title_tv,position);
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        private TextView title_tv;
    }

}
