package com.example.zy_1.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.zy_1.PbMessage;
import com.example.zy_1.net.ApiServer;

import org.greenrobot.eventbus.EventBus;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(){
            @Override
            public void run() {
                //设置文件的保存路径
                final String saveFilePath="/storage/emulated/0/abc.apk";
                Retrofit build = new Retrofit.Builder()
                        .baseUrl(ApiServer.DOWNLOAD_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
                ApiServer apiService = build.create(ApiServer.class);
                Observable<ResponseBody> file = apiService.getFile();
                file.observeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }
                            @Override
                            public void onNext(ResponseBody responseBody) {
                                InputStream is = responseBody.byteStream();
                                long max = responseBody.contentLength();
                                saveFile(saveFilePath,is,max);
                                Log.e("AAA","asssssssssssssssssssssssss");
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);

    }

    private void saveFile(String saveFilePath, InputStream in, long max) {
        EventBus.getDefault().post(new PbMessage(0,0,(int) max));
        FileOutputStream os=null;
        try {
            os= new FileOutputStream(saveFilePath);
            byte[] bs=new byte[1024];
            int len=0;
            int count=0;//下载进度
            while((len=in.read(bs))!=-1){
                os.write(bs,0,len);
                count+=len;//累加上传进度
                EventBus.getDefault().post(new PbMessage(1,count,(int) max));//发送最新进度
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (in!=null){
                    in.close();
                }
                if (os!=null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
