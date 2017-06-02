package com.techfly.demo.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.bean.LogisticsDetailBean;
import com.techfly.demo.interfaces.ItemMultClickListener;
import com.techfly.demo.util.LogsUtil;

import java.util.List;

/*
 * 物流列表
 */
public class LogisticsInfoLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<LogisticsDetailBean.DataEntity.InfosEntity> listData;
    private LayoutInflater layoutInflater;
    private ItemMultClickListener mItemClickListener;


    public LogisticsInfoLvAdapter(Context context, List<LogisticsDetailBean.DataEntity.InfosEntity> list) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.listData = list;
    }

    public void addAll(List<LogisticsDetailBean.DataEntity.InfosEntity> info) {
        listData.addAll(info);
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemMultClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public void clearAll() {
        listData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listData.size();
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
    public View getView(final int position, View convertView, ViewGroup arg2) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_time_axis, null);

            holder.descrip_tv = (TextView) convertView.findViewById(R.id.axis_descrip_tv);
            holder.time_tv = (TextView) convertView.findViewById(R.id.axis_time_tv);
            holder.point_tv = (TextView) convertView.findViewById(R.id.axis_left_point_tv);

            holder.point_top_view = (View) convertView.findViewById(R.id.axis_left_top_view);
            holder.point_bottom_view = (View) convertView.findViewById(R.id.axis_left_bottom_view);
            holder.bottom_view = (View) convertView.findViewById(R.id.axis_bottom_view);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.descrip_tv.setText(listData.get(position).getAcceptStation());
        holder.time_tv.setText(listData.get(position).getAcceptTime());

        if (listData.size() == 1) {

            holder.descrip_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_blue));
            holder.time_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_blue));
            holder.point_tv.setBackgroundResource(R.drawable.shape_circle_solid_red_double);

            holder.point_top_view.setVisibility(View.INVISIBLE);
            holder.bottom_view.setVisibility(View.INVISIBLE);

        } else {

            if (position == 0) { //最上面

                holder.point_top_view.setVisibility(View.INVISIBLE);

                holder.point_bottom_view.setVisibility(View.VISIBLE);///
                holder.bottom_view.setVisibility(View.VISIBLE);

                holder.point_tv.setBackgroundResource(R.drawable.shape_circle_solid_red_double);
                holder.descrip_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_blue));
                holder.time_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_blue));


            } else if (position == listData.size() - 1) {  //最下面

                LogsUtil.normal("position...5=" + position);
                holder.point_top_view.setVisibility(View.VISIBLE);

                holder.point_bottom_view.setVisibility(View.VISIBLE);///
                holder.bottom_view.setVisibility(View.INVISIBLE);

                holder.point_tv.setBackgroundResource(R.drawable.shape_circle_solid_gray_point);
                holder.descrip_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_gray_light));
                holder.time_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_gray_light));


            } else { //中间
                LogsUtil.normal("position...4=" + position);
                holder.point_top_view.setVisibility(View.VISIBLE);
                holder.point_bottom_view.setVisibility(View.VISIBLE);
                holder.bottom_view.setVisibility(View.VISIBLE);

                holder.point_tv.setBackgroundResource(R.drawable.shape_circle_solid_gray_point);
                holder.descrip_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_gray_light));
                holder.time_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_gray_light));

                //  holder.point_tv.setBackgroundResource(R.drawable.shape_circle_solid_gray_point);

            }
        }

        return convertView;
    }

    public class ViewHolder {

        public TextView descrip_tv;
        public TextView time_tv;
        public TextView point_tv;

        public View point_top_view;
        public View point_bottom_view;
        public View bottom_view;
    }

}
