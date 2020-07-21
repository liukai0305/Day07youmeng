package com.example.zy_1.presnter;

import com.example.zy_1.base.BasePresernter;
import com.example.zy_1.bean.JavaBean;
import com.example.zy_1.model.HomeModel;
import com.example.zy_1.net.HomeCallBack;
import com.example.zy_1.view.HomeView;

public class Homepresnter extends BasePresernter<HomeView> implements HomeCallBack {
    private HomeModel homeModel;
    @Override
    protected void initModel() {
        homeModel=new HomeModel();
        addModel(homeModel);
    }

    public void getData(){
        homeModel.getData(this);
    }

    @Override
    public void getData(JavaBean javaBean) {
        mView.setData(javaBean);
    }

    @Override
    public void showToast(String str) {
        mView.showToast(str);
    }
}
