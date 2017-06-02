package com.techfly.demo.activity.qq_demo.adapt;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.techfly.demo.R;
import com.techfly.demo.activity.qq_demo.view.DragBall;
import com.techfly.demo.activity.qq_demo.view.DragBallListener;
import com.techfly.demo.activity.qq_demo.view.DragLayout;
import com.techfly.demo.activity.qq_demo.view.SlideLayout;

import java.util.ArrayList;


/**
 * Created by Root on 2016/6/22.
 */


public class MyListAdapt extends BaseAdapter {

    private ArrayList<String> mData;

    Activity mContext;
    private final Toast mToast;

    private ArrayList<DragLayout> openlayout;
    private ArrayList<Integer> mRmoveList;

    private SlideLayout mSlideLayout;

    public MyListAdapt(Context context, ArrayList<String> data, SlideLayout slideLayout) {

        mData = data;
        mContext = (Activity) context;

        mToast = Toast.makeText(context, "sda", Toast.LENGTH_SHORT);

        openlayout = new ArrayList<DragLayout>();         //

        mRmoveList = new ArrayList<Integer>();        //

        mSlideLayout = slideLayout;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder mViewHolder;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_list, null);
            mViewHolder = new ViewHolder(convertView);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();

        }

        mViewHolder.txt.setText(mData.get(position));


        final int temp = position + 1;

        boolean isVisable = !mRmoveList.contains(temp);
        mViewHolder.msgPoint.setVisibility(isVisable ? View.VISIBLE : View.INVISIBLE);
        if (isVisable){

            mViewHolder.msgPoint.setText(""+temp);
            mViewHolder.msgPoint.setTag(temp);

            DragBallListener listener = new DragBallListener(mContext,mViewHolder.msgPoint){

                @Override
                public void onDisappear(DragBall dragBall, float x, float y) {
                    super.onDisappear(dragBall, x, y);

                    mRmoveList.add(temp);

                    showToast(""+temp);
                }

                @Override
                public void onReset() {
                    super.onReset();

                    showToast(""+temp);
                }
            };
            mViewHolder.msgPoint.setOnTouchListener(listener);
        }




        mViewHolder.zhiding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(""+temp);
            }
        });

        mViewHolder.weidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(""+temp);
            }
        });

        mViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(""+temp);
            }
        });


        mViewHolder.draglayout.setOnDraeListener(new DragLayout.onDraeListener() {

            @Override
            public void onStartOpen(DragLayout dragLayout) {

                Log.d("myAdapt", "==========onStartOpen");
                for (DragLayout op : openlayout) {
                    op.close();

                }

                openlayout.clear();
            }

            @Override
            public void onDrag(View view, int left) {


            }

            @Override
            public void onOpen(DragLayout dragLayout) {

                Log.d("myAdapt", "====================onOpen");
                openlayout.add(dragLayout);
                mSlideLayout.setCelaIsopen(true);   //
            }

            @Override
            public void onclose(DragLayout dragLayout) {
                Log.d("myAdapt", "===================onClose");
                openlayout.remove(dragLayout);
                mSlideLayout.setCelaIsopen(false);     //
            }


        });

        return convertView;
    }

    private void showToast(String string) {
        mToast.setText(string);
        mToast.show();
    }


    public ArrayList<DragLayout> getOpListItem() {

        return openlayout;
    }

    public class ViewHolder {
        public final TextView zhiding;
        public final TextView weidu;
        public final TextView delete;
        public final TextView txt;
        public final DragLayout draglayout;
        public final View root;

        public final TextView msgPoint;

        public ViewHolder(View root) {
            zhiding = (TextView) root.findViewById(R.id.zhiding);
            weidu = (TextView) root.findViewById(R.id.weidu);
            delete = (TextView) root.findViewById(R.id.delete);
            txt = (TextView) root.findViewById(R.id.txt);
            draglayout = (DragLayout) root.findViewById(R.id.drag_layout);

            msgPoint = (TextView)root.findViewById(R.id.msg_point);

            this.root = root;
        }
    }
}
