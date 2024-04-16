package edu.hebut.technote.fragment;

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
import edu.hebut.technote.activity.MyApplication;
import edu.hebut.technote.activity.PaperDetailsActivity;
import edu.hebut.technote.adapter.NewsAdapter;
import edu.hebut.technote.api.Api;
import edu.hebut.technote.api.ApiConfig;
import edu.hebut.technote.api.TtitCallback;
import edu.hebut.technote.dao.NewPaperDao;
import edu.hebut.technote.entity.NewPaper;
import edu.hebut.technote.entity.NewsEntity;
import edu.hebut.technote.response.NewsListResponse;


//public class PapersFragment extends NewsFragment {

//    private Integer pid;
////    private int pageNum = 1;
//
//    @Override
//    protected int initLayout() {
//        return R.layout.fragment_papers;
//    }
//
//    public PapersFragment(Integer pid) {
//        this.pid = pid;
//    }
//
//    public static PapersFragment newInstance(Integer pid) {
//        PapersFragment fragment = new PapersFragment(pid);
//        return fragment;
//    }
//
//    @Override
//    protected HashMap<String, Object> getParams() {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("type","pid");
//        params.put("pid",pid);
//        params.put("page", getPageNum());
//        params.put("size", ApiConfig.PAGE_SIZE);
//        return params;
//    }
//}

public class PapersFragment extends BaseFragment {


    private Integer pid;
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private NewsAdapter newsAdapter;
    private List<NewsEntity> datas = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private int pageNum = 1;

    public PapersFragment(Integer pid) {
        this.pid = pid;
    }

    public PapersFragment() {
    }

    public int getPageNum() {
        return pageNum;
    }


    public static PapersFragment newInstance() {
        PapersFragment fragment = new PapersFragment();
        return fragment;
    }

    public static PapersFragment newInstance(Integer pid) {
        PapersFragment fragment = new PapersFragment(pid);
        return fragment;
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


    protected HashMap<String, Object> getParams() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type","pid");
        params.put("pid",pid);
        params.put("page", getPageNum());
        params.put("size", ApiConfig.PAGE_SIZE);
        return params;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_papers;
    }

    @Override
    protected void initView() {
        recyclerView = mRootView.findViewById(R.id.recyclerView);
        refreshLayout = mRootView.findViewById(R.id.refreshLayout);
    }

    @Override
    protected void initData() {
//        HashMap<String, Object> params = getParams();
        getNewsList(true,getParams());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        newsAdapter = new NewsAdapter(getActivity());
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Serializable obj) {
                NewsEntity newsEntity = (NewsEntity) obj;
                NewPaper newPaper=new NewPaper();
                newPaper.setBiaoti(newsEntity.getTitleZh());
                newPaper.setTime(newsEntity.getCreatedAt());
                newPaper.setTitle(newsEntity.getDetailZh());
                NewPaperDao newPaperDao=MyApplication.getInstance().getmyDataBase().newPaperDao();
                newPaperDao.insert(newPaper);
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
                getNewsList(true,getParams());
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                pageNum++;
                getNewsList(false,getParams());
            }
        });
    }

    private void getNewsList(final boolean isRefresh,HashMap<String, Object> params) {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("type","random");
////        params.put("pid","19");
//        params.put("page", pageNum);
//        params.put("size", ApiConfig.PAGE_SIZE);
        Api.config(ApiConfig.NEWS_LIST, params).getRequest(getActivity(), new TtitCallback() {
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