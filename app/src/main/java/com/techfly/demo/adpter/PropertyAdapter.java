package com.techfly.demo.adpter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.bean.LableBean;
import com.techfly.demo.interfaces.ItemMoreClickListener;
import com.techfly.demo.selfview.flowlayout.FlowLayout;
import com.techfly.demo.selfview.flowlayout.TagAdapter;
import com.techfly.demo.selfview.flowlayout.TagFlowLayout;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.RegexUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PropertyAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<HashMap<String, Object>> mList;
	private ItemMoreClickListener mItemClickListener;

	// 用于保存用户的属性集合
//	public HashMap<String, String> selectProMap = new HashMap<String, String>();
	public List<LableBean> selectProMap = new ArrayList<>();

	/**
	 * 返回选中的属性
	 * @return
	 */
	public List<LableBean> getSelectProMap() {
		return selectProMap;
	}

	/*public void setSelectProMap(HashMap<String, String> selectProMap) {
		this.selectProMap = selectProMap;
	}*/


	/*
	 * 初始化
	 */
	public void initSelectProMap(ArrayList<HashMap<String, Object>> list){
		for(int i = 0;i < list.size();i++){
			String type = (String) list.get(i).get("type");
			LableBean bean = new LableBean(type,"");
			selectProMap.add(bean);
		}
		LogsUtil.normal("initSelectProMap.size=" + selectProMap.size());
	}

	public PropertyAdapter(Context context,
						   ArrayList<HashMap<String, Object>> list) {
		super();
		this.mContext = context;
		this.mList = list;
	}

	public void setItemClickListener(ItemMoreClickListener mItemClickListener) {
		this.mItemClickListener = mItemClickListener;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			// 获取list_item布局文件的视图
			convertView = LayoutInflater.from(this.mContext).inflate(
					R.layout.item_property_lv, null, true);
			holder = new ViewHolder();
			// 获取控件对象
			holder.tvPropName = (TextView) convertView
					.findViewById(R.id.tv_property_name);
			// 设置控件集到convertView
			holder.tagFlowLayout = (TagFlowLayout)convertView.findViewById(R.id.id_flowlayout);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (this.mList != null) {
			ArrayList<String> dataList = (ArrayList<String>) this.mList.get(
					position).get("lable");
			final String type = (String) this.mList.get(position).get("type");
			holder.tvPropName.setText(type);// 规格名称

//			LogsUtil.normal("selectProMap.size()="+selectProMap.size());

			final LayoutInflater mInflater = LayoutInflater.from(mContext);

			final String[] dataArray = (String[])dataList.toArray(new String[dataList.size()]);
			holder.tagFlowLayout.setAdapter(new TagAdapter<String>(dataArray) {
				@Override
				public View getView(FlowLayout parent, int position, String s) {
					TextView tv = (TextView) mInflater.inflate(R.layout.item_flowlayout_tv,
							holder.tagFlowLayout, false);
					tv.setText(s);
					return tv;
				}
			});

			holder.tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
				@Override
				public void onSelected(Set<Integer> selectPosSet) {
					//位置，可能为空
					String subPosition = RegexUtil.matcherReg(selectPosSet + "");
					LableBean bean = null;
					if(!TextUtils.isEmpty(subPosition)){
						bean = new LableBean(type,dataArray[Integer.parseInt(RegexUtil.matcherReg(selectPosSet+""))]);
					}else{
						bean = new LableBean(type,"");
					}
					selectProMap.set(position,bean);
					if(mItemClickListener != null){
						mItemClickListener.onItemSubViewClick(position, position);
					}
				}
			});

		}
		return convertView;
	}

	/* 定义item对象 */
	public class ViewHolder {
		TextView tvPropName;
		TagFlowLayout tagFlowLayout;
	}

	/*class LableClickListener implements OnClickListener {
		private String type;

		public LableClickListener(String type) {

			this.type = type;
		}

		@Override
		public void onClick(View v) {
			TextView[] textViews = (TextView[]) v.getTag();
			TextView tv = (TextView) v;
			for (int i = 0; i < textViews.length; i++) {
				// 让点击的标签背景变成橙色，字体颜色变为白色
				if (tv.equals(textViews[i])) {
					textViews[i]
							.setBackgroundColor(Color.parseColor("#EE5500"));
					textViews[i].setTextColor(Color.parseColor("#FFFFFF"));
					selectProMap.put(type, textViews[i].getText().toString());
				} else {
					// 其他标签背景变成白色，字体颜色为黑色
					// textViews[i].setBackgroundDrawable(drawableNormal);
					textViews[i].setBackgroundColor(Color.WHITE);
					textViews[i].setTextColor(Color.parseColor("#000000"));
				}
			}

		}

	}*/

}