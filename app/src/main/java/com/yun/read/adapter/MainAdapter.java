package com.yun.read.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.yun.read.R;
import com.yun.read.bean.ImageInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {

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

    public void addOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyle,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder( final MyHolder holder, int position) {
        Glide.with(context).load(mlist.get(position).getUrl()).skipMemoryCache(true).diskCacheStrategy( DiskCacheStrategy.SOURCE ).into(holder.img);
        holder.text.setText(mlist.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
       return null !=mlist ? mlist.size() : 0;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.home_item_img)
        ImageView img;
        @BindView(R.id.home_item_title)
        TextView text;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


    }
}
