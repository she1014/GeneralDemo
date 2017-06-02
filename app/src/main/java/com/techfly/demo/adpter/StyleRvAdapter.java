package com.techfly.demo.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.techfly.demo.R;
import com.techfly.demo.bean.StyleBean;
import com.techfly.demo.interfaces.ItemClickListener;

import java.util.List;

/**
 * 添加用料
 */
public class StyleRvAdapter extends RecyclerView.Adapter<StyleRvAdapter.MViewHolder> {

    private Context mContext;
    private List<StyleBean> listData;
    private ItemClickListener mItemClickListener;

    public StyleRvAdapter(Context mContext, List<StyleBean> mList) {
        super();
        this.mContext = mContext;
        this.listData = mList;
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    /*
     * 获取基本数据数目
     */
    public int getBaseDataCount(){
        int count = 0;
        for(int i = 0;i < listData.size();i++){
            if(listData.get(i).isBaseData()){
                count++;
            }
        }
        return count;
    }

    /*
     * 获取完整数据数目
     */
    public int getFullDataCount(){
        int count = 0;
        for(int i = 0;i < listData.size();i++){
            if(listData.get(i).isFullData()){
                count++;
            }
        }
        return count;
    }

    public Boolean isEmpty(){
        String empty = "";
        for(int i = 0;i < listData.size();i++){
            empty = empty + listData.get(i).toString();
        }
        if(empty.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return listData.size();
    }

    public void addAll(List<StyleBean> list) {
		listData.addAll(list);
		notifyDataSetChanged();

	}

    public void insert(StyleBean bean, int position) {
        listData.add(position, bean);
        notifyItemInserted(position);
    }

    public void clearAll() {
        listData.clear();
        notifyDataSetChanged();

    }

//	public IngredientBean.DataEntity getItem(int position){
//		return listData.get(position);
//	}

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {

        View view = View.inflate(viewGroup.getContext(),
                R.layout.item_add_good_style, null);
        // 创建一个ViewHolder
        MViewHolder holder = new MViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MViewHolder mViewHolder,
                                 final int postion) {

		if(postion == 1){
            mViewHolder.item_title_et.setHint("如:黑色,XXL");
            mViewHolder.item_price_et.setHint("如:100");
            mViewHolder.item_rest_et.setHint("如:100");
        }

        mViewHolder.item_title_et.setText(listData.get(postion).getTitle());
        mViewHolder.item_price_et.setText(listData.get(postion).getPrice());
        mViewHolder.item_rest_et.setText(listData.get(postion).getRest());

        mViewHolder.item_title_et.addTextChangedListener(new TextWatcher() {
            private CharSequence charSequence;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                charSequence = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//				mViewHolder.m_name.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
//				if (!TextUtils.isEmpty(s.toString())) {
                listData.get(postion).setTitle(s.toString());
//				}
            }
        });
        mViewHolder.item_price_et.addTextChangedListener(new TextWatcher() {
            private CharSequence charSequence;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                charSequence = s;
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//				mViewHolder.m_dosage.setText(s);
            }
            @Override
            public void afterTextChanged(Editable s) {
//				if (!TextUtils.isEmpty(s.toString())) {
                listData.get(postion).setPrice(s.toString());
//				}
            }
        });

        mViewHolder.item_rest_et.addTextChangedListener(new TextWatcher() {
            private CharSequence charSequence;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                charSequence = s;
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//				mViewHolder.m_dosage.setText(s);
            }
            @Override
            public void afterTextChanged(Editable s) {
//				if (!TextUtils.isEmpty(s.toString())) {
                listData.get(postion).setRest(s.toString());
//				}
            }
        });

	}

    public class MViewHolder extends ViewHolder {
        public EditText item_title_et;
        public EditText item_price_et;
        public EditText item_rest_et;

        public MViewHolder(final View view) {
            super(view);

            this.item_title_et = (EditText) view.findViewById(R.id.item_title_et);
            this.item_price_et = (EditText) view.findViewById(R.id.item_price_et);
            this.item_rest_et = (EditText) view.findViewById(R.id.item_rest_et);

        }
    }
}
