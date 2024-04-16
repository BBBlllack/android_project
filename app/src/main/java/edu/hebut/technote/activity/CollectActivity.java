package edu.hebut.technote.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.hebut.technote.R;
import edu.hebut.technote.adapter.NewsAdapter;
import edu.hebut.technote.api.Api;
import edu.hebut.technote.api.ApiConfig;
import edu.hebut.technote.api.TtitCallback;
import edu.hebut.technote.entity.NewsEntity;
import edu.hebut.technote.response.NewsListResponse;

public class CollectActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private NewsAdapter newsAdapter;
    private List<NewsEntity> datas = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private int pageNum = 1;
    HashMap<String, Object> params;

    public void setParams() {
        this.params.put("type","latest");
        this.params.put("page", pageNum);
        this.params.put("size", ApiConfig.PAGE_SIZE);
        this.params.put("favor",1);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    newsAdapter.setDatas(datas);
                    newsAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected int initLayout() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        refreshLayout = findViewById(R.id.refreshLayout);
    }

    @Override
    protected void initData() {
        params = new HashMap<String, Object>();
        setParams();
        getNewsList(true,params);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        newsAdapter = new NewsAdapter(this);
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Serializable obj) {
                showToast("点击");
                NewsEntity newsEntity = (NewsEntity) obj;
                Bundle bundle = new Bundle();
                bundle.putSerializable("newsEntity",newsEntity);
                navigateToWithBundle(PaperDetailsActivity.class, bundle);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                setParams();
                getNewsList(true,CollectActivity.this.params);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                pageNum++;
                setParams();
                getNewsList(false,CollectActivity.this.params);
            }
        });
    }

    private void getNewsList(final boolean isRefresh,HashMap<String, Object> params) {
        Api.config(ApiConfig.NEWS_LIST, params).getRequest(this, new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                if (isRefresh) {
                    refreshLayout.finishRefresh(true);
                } else {
                    refreshLayout.finishLoadMore(true);
                }
                NewsListResponse response = new Gson().fromJson(res, NewsListResponse.class);
                if (response != null && response.getCode() == 200) {
                    List<NewsEntity> list = response.getData().getRecords();
                    if (list != null && list.size() > 0) {
                        if (isRefresh) {
                            datas = list;
                        } else {
                            datas.addAll(list);
                        }
                        mHandler.sendEmptyMessage(0);
                    } else {
                        if (isRefresh) {
                            showToastSync("暂时无数据");
                        } else {
                            showToastSync("没有更多数据");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Exception e) {
                if (isRefresh) {
                    refreshLayout.finishRefresh(true);
                } else {
                    refreshLayout.finishLoadMore(true);
                }
                showToastSync("error");
            }
        });
    }
}