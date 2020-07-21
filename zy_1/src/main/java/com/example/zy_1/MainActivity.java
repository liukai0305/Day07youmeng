package com.example.zy_1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.zy_1.adapter.FragmentVpAdapter;
import com.example.zy_1.fragment.CollFragment;
import com.example.zy_1.fragment.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager vp;

    private ArrayList<Fragment> list;
    private FragmentVpAdapter fragmentVpAdapter;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new CollFragment());
        fragmentVpAdapter = new FragmentVpAdapter(getSupportFragmentManager(), 0, list);
        vp.setAdapter(fragmentVpAdapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页");
        tab.getTabAt(1).setText("我的");

    }
}
