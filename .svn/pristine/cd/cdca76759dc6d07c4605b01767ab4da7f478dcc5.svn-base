package com.techfly.demo.adpter;

/*
 * @data:  2015年6月5日 下午2:57:16
 * @version:  V1.0
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.techfly.demo.R;
import com.techfly.demo.bean.StepBean;
import com.techfly.demo.interfaces.ItemMultClickListener;
import com.techfly.demo.util.ImageLoaderUtil;

import java.util.List;

/**
 * TODO<>
 *
 * @data: 2015年6月5日 下午2:57:16
 * @version: V1.0
 */
public class GoodBannerRvAdapter extends RecyclerView.Adapter<GoodBannerRvAdapter.MViewHolder> {

    private Context mContext;
    private List<StepBean> listData;
    private ItemMultClickListener mItemClickListener;

    private Boolean isModify = false;

    public GoodBannerRvAdapter(Context mContext, List<StepBean> mList) {
        super();
        this.mContext = mContext;
        this.listData = mList;
    }

    public void setItemClickListener(ItemMultClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return listData.size();
    }

    public void insert(StepBean bean, int position) {
        isModify = false;
        listData.add(bean);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        listData.remove(position);

        if(listData.size() == 0){
            clearAll();
        }
        notifyDataSetChanged();
    }

    public void addAll(List<StepBean> data) {
        isModify = true;
        listData.addAll(data);
        notifyDataSetChanged();
    }

    public void clearAll() {
        int size = listData.size();
        listData.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void updata(StepBean bean, int position) {
        isModify = false;
        listData.set(position, bean);
        notifyItemChanged(position);
    }

    public void updataPic(StepBean bean, int position, Boolean isUrl) {
//        isUrlPic = isUrl;
        listData.set(position, bean);
        notifyItemChanged(position);
    }

    public List<StepBean> getStepBean() {
        return listData;
    }

    public StepBean getCurBean(int position) {
        return listData.get(position);
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {

        /*View view = View.inflate(viewGroup.getContext(),
                R.layout.item_release_banner_rv, null);
        // 创建一个ViewHolder
        MViewHolder holder = new MViewHolder(view);*/
        MViewHolder holder = new MViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_release_banner_rv,
                viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MViewHolder mViewHolder,
                                 final int postion) {

//        LogsUtil.normal(postion + "=>" + isModify + ",getUrl=" + listData.get(postion).getUrl() + ",getPic=" + listData.get(postion).getPic());


        if(isModify){
            mViewHolder.m_picIv.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.getInstance().displayImage(listData.get(postion).getUrl(),  mViewHolder.m_picIv, ImageLoaderUtil.mEmptyIconLoaderOptions);
        }else{
            mViewHolder.m_picIv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if(TextUtils.isEmpty(listData.get(postion).getPic())){
            }else{
                ImageLoader imageLoader = ImageLoader.getInstance();///
                imageLoader.displayImage("file://" + listData.get(postion).getPic(), mViewHolder.m_picIv);///
            }
        }

        mViewHolder.m_picIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mItemClickListener) {
                    mItemClickListener.onItemSubViewClick(0, postion);
                }
            }
        });

        mViewHolder.m_delIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mItemClickListener) {
                    mItemClickListener.onItemSubViewClick(1, postion);
                }
            }
        });
    }

    public class MViewHolder extends ViewHolder {
        public ImageView m_picIv;
        public ImageView m_delIv;

        public MViewHolder(final View view) {
            super(view);
            this.m_picIv = (ImageView) view.findViewById(R.id.item_pic_iv);
            this.m_delIv = (ImageView) view.findViewById(R.id.item_del_iv);
        }
    }

}
