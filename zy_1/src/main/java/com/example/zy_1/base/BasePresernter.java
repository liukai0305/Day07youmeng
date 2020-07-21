package com.example.zy_1.base;

import java.util.ArrayList;

public abstract class BasePresernter<V extends BaseView> {
    public V mView;
    public  ArrayList<BaseModel>models=new ArrayList<>();
    public BasePresernter(){
        initModel();
    }
    public void bindView(V view){
        this.mView=view;
    }

    protected abstract void initModel();

    public void addModel(BaseModel baseModel){
        models.add(baseModel);
    }
    public  void Doryot(){
        mView=null;
        for (int i = 0; i < models.size(); i++) {
            models.get(i).disposable();
        }
    }
}
