package com.techfly.demo.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.bean.MyFileBean;
import com.techfly.demo.interfaces.ItemClickListener;
import com.techfly.demo.util.DateUtil;
import com.techfly.demo.util.ToastUtil;

import java.util.List;

/**
 * 我的视力档案
 */
public class FileLvAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<MyFileBean.TestEntity> listData;
    private ItemClickListener mItemClickListener;

//	private List<VisionBean> visionBeanList;

    private int isGone = View.GONE;

    public FileLvAdapter(Context mContext, List<MyFileBean.TestEntity> mList) {
        super();
        layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.listData = mList;
    }

	/*public FileLvAdapter(Context mContext, List<FileBean> mList,List<VisionBean> vList) {
        super();
		layoutInflater = LayoutInflater.from(mContext);
		this.mContext = mContext;
		this.listData = mList;
		this.visionBeanList = vList;
	}*/

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

	public void addAll(List<MyFileBean.TestEntity> info) {
		listData.addAll(info);
		notifyDataSetChanged();
	}

    public void clearAll(){
        listData.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public RelativeLayout file_rl;

        public ImageView m_picIv;
        public TextView m_dateTv;
        public LinearLayout m_linear;

        public TextView l_tv1;  //左眼-裸眼视力
        public TextView l_tv2;  //矫正视力
        public TextView l_tv3;  //屈光度
        public TextView l_tv4;  //散光度数
//		public TextView l_tv5;  //闪光度数

        public TextView r_tv1;  //右眼
        public TextView r_tv2;
        public TextView r_tv3;
        public TextView r_tv4;
//		public TextView r_tv5;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_mine_file, null);

            viewHolder.file_rl = (RelativeLayout)convertView.findViewById(R.id.file_rl);

            viewHolder.m_picIv = (ImageView) convertView.findViewById(R.id.file_dropIv);
            viewHolder.m_dateTv = (TextView) convertView.findViewById(R.id.file_dateTv);
            viewHolder.m_linear = (LinearLayout) convertView.findViewById(R.id.file_detail_linear);

            viewHolder.l_tv1 = (TextView) convertView.findViewById(R.id.left_tv1);
            viewHolder.l_tv2 = (TextView) convertView.findViewById(R.id.left_tv2);
            viewHolder.l_tv3 = (TextView) convertView.findViewById(R.id.left_tv3);
            viewHolder.l_tv4 = (TextView) convertView.findViewById(R.id.left_tv4);
//			viewHolder.l_tv5 = (TextView)convertView.findViewById(R.id.left_tv5);

            viewHolder.r_tv1 = (TextView) convertView.findViewById(R.id.right_tv1);
            viewHolder.r_tv2 = (TextView) convertView.findViewById(R.id.right_tv2);
            viewHolder.r_tv3 = (TextView) convertView.findViewById(R.id.right_tv3);
            viewHolder.r_tv4 = (TextView) convertView.findViewById(R.id.right_tv4);
//			viewHolder.r_tv5 = (TextView)convertView.findViewById(R.id.right_tv5);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Long time = Long.parseLong(listData.get(position).getCheck_time());
        viewHolder.m_dateTv.setText(DateUtil.second1Date(time));

        viewHolder.m_picIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewHolder.m_linear.getVisibility() == isGone) {

                    if (listData != null) {

                        viewHolder.m_picIv.setImageResource(R.drawable.icon_arrow_top);

                        viewHolder.l_tv1.setText("" + listData.get(position).getUncorrected_visual_l());
                        viewHolder.l_tv2.setText("" + listData.get(position).getCorrected_visual_l());
                        viewHolder.l_tv3.setText("" + listData.get(position).getDiopter_l());
                        viewHolder.l_tv4.setText("" + listData.get(position).getFlash_degree_l());
//						viewHolder.l_tv5.setText(""+visionBeanList.get(position).getL_sg());

                        viewHolder.r_tv1.setText("" + listData.get(position).getUncorrected_visual_r());
                        viewHolder.r_tv2.setText("" + listData.get(position).getCorrected_visual_r());
                        viewHolder.r_tv3.setText("" + listData.get(position).getDiopter_r());
                        viewHolder.r_tv4.setText("" + listData.get(position).getFlash_degree_r());
//						viewHolder.r_tv5.setText(""+visionBeanList.get(position).getR_sg());

                        viewHolder.m_linear.setVisibility(View.VISIBLE);
                    } else {
                        ToastUtil.DisplayToast(mContext, "暂无数据!");
                    }

                } else {
                    viewHolder.m_picIv.setImageResource(R.drawable.icon_arrow_down);
                    viewHolder.m_linear.setVisibility(View.GONE);
                }
            }
        });

        /*viewHolder.file_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(mItemClickListener != null){
                    mItemClickListener.onItemClick(viewHolder.file_rl,position);
                }
            }
        });*/

        viewHolder.file_rl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mItemClickListener != null){
                    mItemClickListener.onItemLongClick(viewHolder.file_rl,position);
                }
                return false;
            }
        });

        return convertView;
    }


}
