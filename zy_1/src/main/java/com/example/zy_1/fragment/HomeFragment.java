package com.example.zy_1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zy_1.LoingActivity;
import com.example.zy_1.R;
import com.example.zy_1.adapter.RcyAdapter;
import com.example.zy_1.base.BaseFragment;
import com.example.zy_1.bean.JavaBean;
import com.example.zy_1.presnter.Homepresnter;
import com.example.zy_1.view.HomeView;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<Homepresnter>implements HomeView {

    @BindView(R.id.rcy)
    RecyclerView rcy;
    private ArrayList<JavaBean.DataBean.DatasBean> list;
    private RcyAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresmter() {
        mp=new Homepresnter();

    }

    @Override
    protected void initView() {
        rcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcy.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        list = new ArrayList<>();
        adapter = new RcyAdapter(list, getActivity());
        rcy.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mp.getData();
    }

    @Override
    protected void initLis() {
        adapter.setOnItemClickLis(new RcyAdapter.OnItemClickLis() {
            @Override
            public void click(int position) {
                startActivity(new Intent(getActivity(), LoingActivity.class));
            }
        });
    }

    @Override
    public void setData(JavaBean javaBean) {
        list.addAll(javaBean.getData().getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

//    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                             Bundle savedInstanceState) {
////        // Inflate the layout for this fragment
////        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
////        return inflate;
////    }
}
