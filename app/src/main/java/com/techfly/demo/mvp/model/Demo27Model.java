package com.techfly.demo.mvp.model;

import com.techfly.demo.bean.ShopOrderListBean;

import java.util.List;

/**
 * Model 主要负责获取数据
 * Created by ssm on 2017/1/16.
 */
public interface Demo27Model {

    void loadLocalData(Demo27OnLoadLisener lisener);

    void loadNetData(Demo27OnLoadLisener lisener);

    /*
     * 加载完成监听
     */
    interface  Demo27OnLoadLisener{
        void completed(List<ShopOrderListBean.DataEntity.DatasEntity> list);
    };

}
