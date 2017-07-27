package com.yun.read.activity;

import android.app.WallpaperManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;
import com.yun.read.R;
import com.yun.read.base.BaseActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        Glide.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE_URL)).into(pic_view);
//        WallpaperManager mWallManager= WallpaperManager.getInstance(this);
//        try {
//            mWallManager.setResource(R.mipmap.loadfail);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
