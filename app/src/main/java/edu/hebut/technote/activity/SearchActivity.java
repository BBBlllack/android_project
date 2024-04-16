package edu.hebut.technote.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

public class SearchActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private EditText editText;
    private ImageView btn_search;
    private NewsAdapter newsAdapter;
    private List<NewsEntity> datas = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private int pageNum = 1;
    private String key;
    HashMap<String, Object> params;

    public void setKey(String key) {
        this.key = key;
    }
    public void setParams() {
        this.params.put("type","latest");
        this.params.put("page", pageNum);
        this.params.put("size", ApiConfig.PAGE_SIZE);
        this.params.put("key",key);
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
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        refreshLayout = findViewById(R.id.refreshLayout);
        editText = findViewById(R.id.et_search);
        btn_search = findViewById(R.id.btn_search);
    }

    @Override
    protected void initData() {
        params = new HashMap<String, Object>();
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入框的内容
                SearchActivity.this.setKey(editText.getText().toString().trim());
                setParams();
                getNewsList(true,params);
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //点击搜索的时候隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    setParams();
                    getNewsList(true,params);
                    // 在这里写搜索的操作,一般都是网络请求数据
                    return true;
                }

                return false;
            }
        });
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
//                String url = "http://192.168.31.32:8089/newsDetail?title=" + newsEntity.getAuthorName();
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
                getNewsList(true,SearchActivity.this.params);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                pageNum++;
                setParams();
                getNewsList(false,SearchActivity.this.params);
            }
        });
    }

    private void getNewsList(final boolean isRefresh,HashMap<String, Object> params) {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("type","random");
////        params.put("pid","19");
//        params.put("page", pageNum);
//        params.put("size", ApiConfig.PAGE_SIZE);
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
                        showToastSync("搜索成功");
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