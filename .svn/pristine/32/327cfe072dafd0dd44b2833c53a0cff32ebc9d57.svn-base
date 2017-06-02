package com.techfly.demo.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.bean.DemoBean;
import com.techfly.demo.interfaces.ItemMoreClickListener;

import java.util.List;

/**
 * TODO<>
 */
public class Demo18RvAdapter extends RecyclerView.Adapter<Demo18RvAdapter.MViewHolder> {

    private Context mContext;

    private List<DemoBean> listData;// = new ArrayList<ShopBean>();
    private LayoutInflater layoutInflater;
    private ItemMoreClickListener mItemClickListener;

    //1-未收货；2-已收货
    private int mCurStatus = 0;

    public Demo18RvAdapter(Context context, List<DemoBean> shopBeans, int status) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.listData = shopBeans;
        this.mCurStatus = status;
    }

    public void addAll(List<DemoBean> list, int status) {
        listData.addAll(list);
        this.mCurStatus = status;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemMoreClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void clearAll() {
        listData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return listData.size();
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {

        //子项宽度充满屏幕
        MViewHolder holder = new MViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_horizontal_lv,
                viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MViewHolder mViewHolder,
                                 final int position) {

        mViewHolder.item1_tv.setText(listData.get(position).getTv1());
        mViewHolder.item2_tv.setText(listData.get(position).getTv2());
        mViewHolder.item3_tv.setText(listData.get(position).getTv3());
        mViewHolder.item4_tv.setText(listData.get(position).getTv4());
        mViewHolder.item5_tv.setText(listData.get(position).getTv5());

        mViewHolder.item6_tv.setText(listData.get(position).getTv6());
        mViewHolder.item7_tv.setText(listData.get(position).getTv7());
        mViewHolder.item8_tv.setText(listData.get(position).getTv8());
        mViewHolder.item9_tv.setText(listData.get(position).getTv9());
        mViewHolder.item10_tv.setText(listData.get(position).getTv10());

    }

    public class MViewHolder extends ViewHolder {

        public TextView item1_tv;
        public TextView item2_tv;
        public TextView item3_tv;
        public TextView item4_tv;
        public TextView item5_tv;

        public TextView item6_tv;
        public TextView item7_tv;
        public TextView item8_tv;
        public TextView item9_tv;
        public TextView item10_tv;

        public MViewHolder(final View view) {
            super(view);

            this.item1_tv = (TextView) view.findViewById(R.id.item1_tv);
            this.item2_tv = (TextView) view.findViewById(R.id.item2_tv);
            this.item3_tv = (TextView) view.findViewById(R.id.item3_tv);
            this.item4_tv = (TextView) view.findViewById(R.id.item4_tv);
            this.item5_tv = (TextView) view.findViewById(R.id.item5_tv);

            this.item6_tv = (TextView) view.findViewById(R.id.item6_tv);
            this.item7_tv = (TextView) view.findViewById(R.id.item7_tv);
            this.item8_tv = (TextView) view.findViewById(R.id.item8_tv);
            this.item9_tv = (TextView) view.findViewById(R.id.item9_tv);
            this.item10_tv = (TextView) view.findViewById(R.id.item10_tv);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(view, getPosition());
                    }
                }
            });
        }
    }

}
