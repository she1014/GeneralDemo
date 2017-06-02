package com.techfly.demo.activity.demo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.adpter.GroceriesLvAdapter;
import com.techfly.demo.bean.DemoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class Demo2Activity extends BaseActivity {

    @InjectView(R.id.base_slv)
    SwipeMenuListView base_slv;

    private GroceriesLvAdapter adapter;
    private List<DemoBean> dataEnty = new ArrayList<DemoBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        ButterKnife.inject(this);
//        EventBus.getDefault().register(this);

        setTranslucentStatus(R.color.main_bg);

        initBaseView();
        setBaseTitle("Demo2", 0);
        initBackButton(R.id.top_back_iv);

        initView();

        initData();

    }

    private void initData() {
        for(int i = 0;i < 10;i++){
            DemoBean bean = new DemoBean("标题"+i,(++i)+"");
            dataEnty.add(bean);
        }
    }

    private void initView() {

        base_slv = (SwipeMenuListView) findViewById(R.id.base_slv);

        adapter = new GroceriesLvAdapter(this, dataEnty);
        base_slv.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                openItem.setWidth(180);
                // set item title
                openItem.setTitle("Sub");
                // set item title fontsize
                openItem.setTitleSize(16);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // qq_add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                /*SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(240);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_launcher);
                // qq_add to menu
                menu.addMenuItem(deleteItem);*/

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
//                deleteItem.setBackground(getResources().getColor(R.color.text_font_blue));
//                 set item width
                deleteItem.setWidth(180);
                // set item title
                deleteItem.setTitle("Add");
                // set item title fontsize
                deleteItem.setTitleSize(16);
                // set item title font color
                deleteItem.setTitleColor(Color.WHITE);
                // qq_add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        base_slv.setMenuCreator(creator);

        base_slv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open

                        break;
                    case 1:
                        // delete


                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        // Right
//        groc_smlv.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);

        // Left
        base_slv.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

    }


}
