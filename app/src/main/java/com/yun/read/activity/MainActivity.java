package com.yun.read.activity;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.yun.read.R;
import com.yun.read.adapter.MainAdapter;
import com.yun.read.base.BaseActivity;
import com.yun.read.base.HttpListener;
import com.yun.read.bean.ImageBeans;
import com.yun.read.util.OkHttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 街角慢嗨
 * @email 773675907@qq.com
 * @createdate 2017/07/26
 */
public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.tl_custom)
    Toolbar mToolbar;

    @BindView(R.id.dl_left)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.pull_swiperefresh)
    SwipeRefreshLayout pull_swiperefresh;

    @BindView(R.id.group_fragment_list)
    RecyclerView group_fragment_list;

    private ActionBarDrawerToggle mDrawerToggle;

    private MainAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBar();
        initPull();
        initData();
    }

    /**
     * 加载数据
     */
    private void initData() {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        group_fragment_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });

        group_fragment_list.setLayoutManager(layoutManager);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        group_fragment_list.addItemDecoration(decoration);

        adapter = new MainAdapter(this);
        adapter.addOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        group_fragment_list.setAdapter(adapter);
        OkHttpUtil.getResult(1, new HttpListener() {
            @Override
            public void onFail() {
                tost("请检查网络!");
            }

            @Override
            public void onError() {

            }

            @Override
            public void onSucess(String json) {
                Gson gson = new Gson();

                final ImageBeans beans = gson.fromJson(json, ImageBeans.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!beans.isError()) {
                            adapter.setData(beans.getResults());
                        } else {
                            tost("获取失败!");
                        }

                    }
                });

            }
        });

    }

    /**
     * 下拉刷新的相关操作
     */
    private void initPull() {

        pull_swiperefresh.setColorSchemeResources(R.color.google_blue, R.color.google_green, R.color.google_red, R.color.google_yellow);
        group_fragment_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("insert", newState + "===newState=" );
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.e("insert", dx + "====" + dy);
                int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                pull_swiperefresh.setEnabled(topRowVerticalPosition >= 0);
            }
        });
        pull_swiperefresh.setOnRefreshListener(this);
    }

    /**
     * 操作toolbar和侧滑
     */
    private void initBar() {
        setSupportActionBar(mToolbar);  //将ToolBar设置成ActionBar
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("阅");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onRefresh() {
        pull_swiperefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                pull_swiperefresh.setRefreshing(false);
            }
        },300);
    }
}
