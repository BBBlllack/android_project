package edu.hebut.technote.fragment;

import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;

import edu.hebut.technote.R;
import edu.hebut.technote.activity.SearchActivity;
import edu.hebut.technote.api.ApiConfig;


//public class NewsFragment extends BaseFragment {
//
//    private RecyclerView recyclerView;
//    private RefreshLayout refreshLayout;
//    private ImageView search;
//    private NewsAdapter newsAdapter;
//    private List<NewsEntity> datas = new ArrayList<>();
//    private LinearLayoutManager linearLayoutManager;
//    private int pageNum = 1;
//
//    public NewsFragment() {
//    }
//
//    public int getPageNum() {
//        return pageNum;
//    }
//
//
//    public static NewsFragment newInstance() {
//        NewsFragment fragment = new NewsFragment();
//        return fragment;
//    }
//
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 0:
//                    newsAdapter.setDatas(datas);
//                    newsAdapter.notifyDataSetChanged();
//                    break;
//            }
//        }
//    };
//
//    @Override
//    protected int initLayout() {
//        return R.layout.fragment_news;
//    }
//
//    @Override
//    protected void initView() {
//        recyclerView = mRootView.findViewById(R.id.recyclerView);
//        refreshLayout = mRootView.findViewById(R.id.refreshLayout);
//        search = mRootView.findViewById(R.id.search);
//    }
//
//    protected HashMap<String, Object> getParams(){
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("type","random");
////        params.put("pid","19");
//        params.put("page", pageNum);
//        params.put("size", ApiConfig.PAGE_SIZE);
//        return params;
//    }
//
//    @Override
//    protected void initData() {
////        HashMap<String, Object> params = getParams();
//        getNewsList(true,getParams());
//        linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        newsAdapter = new NewsAdapter(getActivity());
//        recyclerView.setAdapter(newsAdapter);
//        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Serializable obj) {
//                showToast("点击");
//                NewsEntity newsEntity = (NewsEntity) obj;
////                String url = "http://192.168.31.32:8089/newsDetail?title=" + newsEntity.getAuthorName();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("newsEntity",newsEntity);
//                navigateToWithBundle(PaperDetailsActivity.class, bundle);
//            }
//        });
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                pageNum = 1;
//                getNewsList(true,getParams());
//            }
//        });
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshlayout) {
//                pageNum++;
//                getNewsList(false,getParams());
//            }
//        });
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showToast("跳转搜索界面");
//                navigateTo(SearchActivity.class);
//            }
//        });
//    }
//
//    private void getNewsList(final boolean isRefresh,HashMap<String, Object> params) {
////        HashMap<String, Object> params = new HashMap<>();
////        params.put("type","random");
//////        params.put("pid","19");
////        params.put("page", pageNum);
////        params.put("size", ApiConfig.PAGE_SIZE);
//        Api.config(ApiConfig.NEWS_LIST, params).getRequest(getActivity(), new TtitCallback() {
//            @Override
//            public void onSuccess(final String res) {
//                if (isRefresh) {
//                    refreshLayout.finishRefresh(true);
//                } else {
//                    refreshLayout.finishLoadMore(true);
//                }
//                NewsListResponse response = new Gson().fromJson(res, NewsListResponse.class);
//                if (response != null && response.getCode() == 200) {
//                    List<NewsEntity> list = response.getData().getRecords();
//                    if (list != null && list.size() > 0) {
//                        if (isRefresh) {
//                            datas = list;
//                        } else {
//                            datas.addAll(list);
//                        }
//                        mHandler.sendEmptyMessage(0);
//                    } else {
//                        if (isRefresh) {
//                            showToastSync("暂时无数据");
//                        } else {
//                            showToastSync("没有更多数据");
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                if (isRefresh) {
//                    refreshLayout.finishRefresh(true);
//                } else {
//                    refreshLayout.finishLoadMore(true);
//                }
//                showToastSync("error");
//            }
//        });
//    }




//}


public class NewsFragment extends PapersFragment {

    private ImageView search;

    @Override
    protected int initLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        super.initView();
        search = mRootView.findViewById(R.id.search);
    }

    @Override
    protected HashMap<String, Object> getParams(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("type","random");
        params.put("page", getPageNum());
        params.put("size", ApiConfig.PAGE_SIZE);
//        params.put("type","rem");
        return params;
    }

    @Override
    protected void initData() {
        super.initData();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("跳转到搜索界面");
                navigateTo(SearchActivity.class);
            }
        });
    }
}


