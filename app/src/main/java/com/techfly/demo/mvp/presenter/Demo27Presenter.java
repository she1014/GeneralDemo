package com.techfly.demo.mvp.presenter;

import com.techfly.demo.bean.ShopOrderListBean;
import com.techfly.demo.mvp.model.Demo27Model;
import com.techfly.demo.mvp.model.Demo27ModelImplV1;
import com.techfly.demo.mvp.view.Demo27View;

import java.util.List;

/**
 * Created by ssm on 2017/1/16.
 */
public class Demo27Presenter implements Demo27Model.Demo27OnLoadLisener{

    //Modle
    private Demo27Model modelV1 = new Demo27ModelImplV1();

    //View
    private Demo27View view;

    private void fetch(){

    };

    @Override
    public void completed(List<ShopOrderListBean.DataEntity.DatasEntity> list) {

    }
}
