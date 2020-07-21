package com.example.zy_1.net;

import com.example.zy_1.bean.JavaBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface ApiServer {
//    https://www.wanandroid.com/
    String WAN_PROJECT="https://www.wanandroid.com/";
    @GET("project/list/1/json?cid=294")
    Observable<JavaBean>getJava();
    String DOWNLOAD_URL = "https://alissl.ucdl.pp.uc.cn/";
    @GET("fs08/2019/07/05/1/110_17e4089aa3a4b819b08069681a9de74b.apk")
    Observable<ResponseBody> getFile();
}
