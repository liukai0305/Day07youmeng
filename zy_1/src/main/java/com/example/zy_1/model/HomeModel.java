package com.example.zy_1.model;

import com.example.zy_1.base.BaseModel;
import com.example.zy_1.bean.JavaBean;
import com.example.zy_1.net.ApiServer;
import com.example.zy_1.net.HomeCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModel extends BaseModel {
    public void getData(HomeCallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.WAN_PROJECT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<JavaBean> java = apiServer.getJava();
        java.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JavaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(JavaBean javaBean) {
                        callBack.getData(javaBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.showToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
