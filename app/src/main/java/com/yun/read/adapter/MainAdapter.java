package com.yun.read.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yun.read.R;
import com.yun.read.activity.PictureActivity;
import com.yun.read.bean.ImageInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    private List<ImageInfo> mlist;

    OnItemClickListener onItemClickListener;

    private List<Integer> mHeights;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ImageInfo> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyle, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ImageInfo imageInfo = mlist.get(position);
        ((MyHolder) holder).imageInfo = imageInfo;
        int thumbWidth = mlist.get(position).getThumbWidth();
        int thumbHeight = mlist.get(position).getThumbHeight();
        if (thumbWidth > 0 && thumbHeight > 0) {
            int width = ((MyHolder) holder).img.getMeasuredWidth();
            int height = width * (thumbHeight / thumbWidth);
            ((MyHolder) holder).img.getLayoutParams().height = height;
            ViewGroup.LayoutParams layoutParams = ((MyHolder) holder).img.getLayoutParams();
            layoutParams.height = height;
            ((MyHolder) holder).img.setLayoutParams(layoutParams);
            Glide.with(context).load(mlist.get(position).getUrl()).placeholder(R.mipmap.loading)
                    .error(R.mipmap.loadfail).animate(R.anim.zoom_in).into(((MyHolder) holder).img);
        } else
            Glide.with(context).load(mlist.get(position).getUrl()).placeholder(R.mipmap.loading)
                    .error(R.mipmap.loadfail).animate(R.anim.zoom_in).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    mlist.get(position).setThumbWidth(resource.getIntrinsicWidth());
                    mlist.get(position).setThumbHeight(resource.getIntrinsicHeight());
                    int width = ((MyHolder) holder).img.getMeasuredWidth();
                    int height = width * (resource.getIntrinsicHeight() / resource.getIntrinsicWidth());
                    ((MyHolder) holder).img.getLayoutParams().height = height;
                    ViewGroup.LayoutParams layoutParams = ((MyHolder) holder).img.getLayoutParams();
                    layoutParams.height = height;
                    ((MyHolder) holder).img.setLayoutParams(layoutParams);
                    Glide.with(context).load(mlist.get(position).getUrl()).placeholder(R.mipmap.loading)
                            .error(R.mipmap.loadfail).animate(R.anim.zoom_in).into(((MyHolder) holder).img);
                }
            });
        ((MyHolder) holder).text.setText(mlist.get(position).getDesc());
    }


    @Override
    public int getItemCount() {
        return null != mlist ? mlist.size() : 0;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageInfo imageInfo;
        @BindView(R.id.home_item_img)
        ImageView img;
        @BindView(R.id.home_item_title)
        TextView text;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.home_item_img)
        public void imageOnClick() {
            if (imageInfo != null) {
                Intent i = new Intent(context, PictureActivity.class);
                i.putExtra(PictureActivity.EXTRA_IMAGE_URL, imageInfo.getUrl());
                i.putExtra(PictureActivity.EXTRA_IMAGE_TITLE, imageInfo.getDesc());

                if (context instanceof Activity) {
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            (Activity) context, img, PictureActivity.TRANSIT_PIC
                    );
                    ActivityCompat.startActivity((Activity) context, i, optionsCompat.toBundle());
                } else {
                    context.startActivity(i);
                }
            }
        }


    }
}
