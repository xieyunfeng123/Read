package com.yun.read.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.yun.read.R;
import com.yun.read.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class WeleComeActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welecome);
        ButterKnife.bind(this);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(WeleComeActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }).start();




    }
}
