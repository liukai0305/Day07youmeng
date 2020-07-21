package com.example.day07youmeng.wxapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.day07youmeng.R;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEnterActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_x_enter);
    }
}
