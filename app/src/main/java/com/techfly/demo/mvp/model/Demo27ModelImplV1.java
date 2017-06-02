package com.techfly.demo.mvp.model;

import com.google.gson.Gson;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.bean.ShopOrderListBean;
import com.techfly.demo.interfaces.GetResultCallBack;
import com.techfly.demo.network.AppClient;
import com.techfly.demo.util.CommonUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * Model 主要负责获取数据
 * Created by ssm on 2017/1/16.
 */
public class Demo27ModelImplV1 implements Demo27Model,GetResultCallBack{

    private Demo27OnLoadLisener lisener;

//    private List<ShopOrderListBean.DataEntity.DatasEntity> list;

    @Override
    public void loadLocalData(Demo27OnLoadLisener lisener) {

    }

    @Override
    public void loadNetData(Demo27OnLoadLisener lisener) {
        this.lisener = lisener;
        AppClient.getShopOrderList("37", "260ade21aff4b8cd1c743c3753360fda", 1, 100, "ACCEPT_WAITTING,PS_WAITTING,RECEIVE_WAITTING", this);
    }

    @Override
    public void getResult(String result, int type) {

        Gson gson = new Gson();
        result = CommonUtils.removeBrace(result);

        if (type == Constant.API_GET_SHOP_ORDER_LIST_SUCCESS) {
            ShopOrderListBean data = gson.fromJson(result, ShopOrderListBean.class);
            if (data != null) {
                lisener.completed(data.getData().getDatas());
            }
        }
    }
}
