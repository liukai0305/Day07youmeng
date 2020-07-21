package com.example.zy_1.base;

import android.os.Binder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.zy_1.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresernter> extends Fragment implements BaseView {
    public P mp;
    private Unbinder bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(getLayout(), container, false);
        bind = ButterKnife.bind(this, inflate);
        initPresmter();
        if (mp != null) {
            mp.bindView(this);
        }
        initView();
        initData();
        initLis();
        return inflate;
    }

    protected abstract int getLayout();

    protected abstract void initPresmter();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initLis();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.Doryot();
        mp=null;
    }
}
