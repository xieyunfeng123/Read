package com.yun.read.activity;

import android.app.WallpaperManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigButton;
import com.mylhyl.circledialog.callback.ConfigDialog;
import com.mylhyl.circledialog.params.ButtonParams;
import com.mylhyl.circledialog.params.DialogParams;
import com.yun.read.R;
import com.yun.read.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class PictureActivity extends BaseActivity {


    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";
    @BindView(R.id.pic_view)
    PhotoView pic_view;



    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);
        ViewCompat.setTransitionName(pic_view, TRANSIT_PIC);
        pic_view.postDelayed(new Runnable() {
            @Override
            public void run() {
                Glide.with(PictureActivity.this).load(getIntent().getStringExtra(EXTRA_IMAGE_URL)).into(pic_view);
            }
        },300);

//        WallpaperManager mWallManager= WallpaperManager.getInstance(this);
//        try {
//            mWallManager.setResource(R.mipmap.loadfail);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @OnClick(R.id.pic_view)
    public void  ImageOnClick()
    {
//        final String[] items = {"手机壁纸", "锁屏壁纸"};
//        new CircleDialog.Builder(this)
//                .configDialog(new ConfigDialog() {
//                    @Override
//                    public void onConfig(DialogParams params) {
//                        //增加弹出动画
////                        params.animStyle = R.style.dialogWindowAnim;
//                    }
//                })
//                .setItems(items, new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                    }
//                })
//                .setNegative("取消", null)
//                .configNegative(new ConfigButton() {
//                    @Override
//                    public void onConfig(ButtonParams params) {
//                        //取消按钮字体颜色
//                        params.textColor = Color.RED;
//                    }
//                })
//                .show();
    }


}
