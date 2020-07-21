package com.example.zy_1.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseModel {
    public CompositeDisposable compositeDisposable=null;
    public void  addDisposable(Disposable disposable){
        if (compositeDisposable==null){
            synchronized (this){
                if (compositeDisposable==null){
                    compositeDisposable=new CompositeDisposable();
                }
            }
        }
        compositeDisposable.add(disposable);
    }
    public void removeDisposable(Disposable disposable){
        compositeDisposable.remove(disposable);

    }
    public void disposable(){
        compositeDisposable.dispose();
    }
}
