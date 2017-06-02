package com.techfly.demo.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techfly.demo.R;
import com.techfly.demo.bean.ScoreRankBean;
import com.techfly.demo.interfaces.ItemClickListener;
import com.techfly.demo.selfview.GlideCircleTransform;

import java.util.List;

/**
 * 首页-排行榜-人气榜
 */
public class IndexGridScoreAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater layoutInflater;
	private List<ScoreRankBean.DataEntity> listData;
	private ItemClickListener mItemClickListener;

	public IndexGridScoreAdapter(Context mContext, List<ScoreRankBean.DataEntity> mList) {
//		super();
		layoutInflater = LayoutInflater.from(mContext);
		this.mContext = mContext;
		this.listData = mList;
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public void setItemClickListener(ItemClickListener mItemClickListener) {
		this.mItemClickListener = mItemClickListener;
	}

	public void addAll(List<ScoreRankBean.DataEntity> data){
		listData.addAll(data);
		notifyDataSetChanged();
	}

	public class ViewHolder {
		public ImageView m_imgIv;
		public TextView m_nameTv;
	}



	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.item_rank_gridview,null);

			viewHolder.m_imgIv = (ImageView) convertView.findViewById(R.id.rank_imgIv);
			viewHolder.m_nameTv = (TextView)convertView.findViewById(R.id.rank_nameTv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		String url = String.format(listData.get(position).getAvatar());

//		LogsUtil.normal(position + "=加载图片=>" + url);

		Glide.with(mContext)
				.load(url)
				.thumbnail(0.3f)
				.placeholder(R.drawable.icon_default_avatar)
				.error(R.drawable.icon_default_avatar)
				.transform(new GlideCircleTransform(mContext))
				.into(viewHolder.m_imgIv);

		viewHolder.m_nameTv.setText(listData.get(position).getName());

		convertView.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if (null != mItemClickListener) {
					mItemClickListener.onItemClick(viewHolder.m_imgIv, position);
				}

			}
		});

		return convertView;
	}
}
