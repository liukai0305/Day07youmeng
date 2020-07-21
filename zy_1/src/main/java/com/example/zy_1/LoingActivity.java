package com.example.zy_1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.zy_1.server.MyService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoingActivity extends AppCompatActivity implements View.OnClickListener {

    private SeekBar sb;
    private TextView tv;
    private Button but;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loing);
        initView();
        checkpersion();
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMsg(PbMessage ms) {
        if (ms.getFlag() == 0) {
            sb.setMax(ms.getMax());
        } else if (ms.getFlag() == 1) {
            sb.setProgress(ms.getProgress());
            int progress = ms.getProgress();
            int max = ms.getMax();
            //int b = (int) (((float) progress / max) * 100);
            int jd = (int) (((float) progress / max) * 100);
            tv.setText(jd + "%");
            if (jd == 1) {
                Toast.makeText(this, "下载完成", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void checkpersion() {
        int i = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (i != PackageManager.PERMISSION_GRANTED) {//没有授权，申请权限
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 100);
        }
    }

    private void initView() {
        sb = (SeekBar) findViewById(R.id.sb);
        tv = (TextView) findViewById(R.id.tv);
        but = (Button) findViewById(R.id.but);

        but.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but:
                intent = new Intent(LoingActivity.this, MyService.class);
                startService(intent);
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService(intent);
       EventBus.getDefault().unregister(intent);
    }
}
