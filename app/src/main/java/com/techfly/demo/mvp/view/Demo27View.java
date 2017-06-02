package com.techfly.demo.mvp.view;

import com.techfly.demo.bean.ShopOrderListBean;

import java.util.List;

/**
 * Created by ssm on 2017/1/16.
 */
public interface Demo27View {

    /*
     * 加载进度条
     */
    void showLoading();

    /*
     * 显示数据
     */
    void showData(List<ShopOrderListBean.DataEntity.DatasEntity> list);

}
