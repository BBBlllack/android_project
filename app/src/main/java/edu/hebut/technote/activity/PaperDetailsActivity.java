package edu.hebut.technote.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
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
import edu.hebut.technote.response.GPTSummaryResponse;
import edu.hebut.technote.response.NewsListResponse;
import edu.hebut.technote.response.RecommendNewsResponse;
import edu.hebut.technote.utils.TypeTextView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class PaperDetailsActivity extends BaseActivity {

    private int id;
    private boolean isFavor;

    private TextView category;
    private TextView titleZh;
    private TextView titleEn;
    private TextView link;
    private TextView detailEn;
    private TextView detailZh;
    private TextView createdAt;
    private ImageView favor;
    private ImageView preview;
    private ImageView ciyun;
    private ImageView gpt;

    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetBehavior mDialogBehavior;
    private TypeTextView mTypeTextView_keywords;
    private TypeTextView mTypeTextView_summary;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<NewsEntity> datas = new ArrayList<>();
    private String keywords;
    private String summary;

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
        return R.layout.activity_paper_details;
    }

    @Override
    protected void initView() {
        titleZh = findViewById(R.id.titleZh);
        category = findViewById(R.id.category);
        createdAt = findViewById(R.id.time);
        favor = findViewById(R.id.collect);
        preview = findViewById(R.id.preview);
        titleEn = findViewById(R.id.titleEn);
        detailZh = findViewById(R.id.detailZh);
        detailEn = findViewById(R.id.detailEn);
        link = findViewById(R.id.link);
        ciyun = findViewById(R.id.ciyun);
        gpt = findViewById(R.id.ai);
        recyclerView = findViewById(R.id.recyclerView);
        mTypeTextView_keywords = findViewById(R.id.text_keyword);
        mTypeTextView_summary = findViewById(R.id.text_summary);
    }

    @Override
    protected void initData() {
        NewsEntity newsEntity= (NewsEntity)getIntent().getExtras().getSerializable("newsEntity");
        id = newsEntity.getId();
        String str = newsEntity.getTitleEn();
        if(MyApplication.getInstance().isLanguage()){
            titleZh.setText(newsEntity.getTitleZh());
        }else{
            titleZh.setText(str.substring(str.indexOf('】')+2));
        }
        titleEn.setText(str.substring(str.indexOf('】')+2));
//        titleEn.setText(str.substring(str.indexOf('】')+2));
//        titleZh.setText(newsEntity.getTitleZh());
        category.setText(newsEntity.getFname()+"-"+newsEntity.getPname());
        createdAt.setText(newsEntity.getCreatedAt());
        detailZh.setText("    "+newsEntity.getDetailZh());
        detailEn.setText("    "+newsEntity.getDetailEn());
        link.setText(newsEntity.getLink());
        getCiYunImage(true);

        //收藏
        if(newsEntity.getFavor()==0)
            isFavor = false;
        else isFavor = true;
        if (isFavor)
        favor.setImageResource(R.mipmap.collect_select);
        else favor.setImageResource(R.mipmap.collect);

        favor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavor) { //已收藏
                    favor.setImageResource(R.mipmap.collect);
                    // API
                } else {//未收藏
                    favor.setImageResource(R.mipmap.collect_select);
                    //API
                }
                isFavor = !isFavor;
                changeFavorState();
            }
        });


        //词云
        ciyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCiYunImage(true);
            }
        });

        //预览
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                intent.setData(Uri.parse(link.getText().toString()));
//                Intent intent = new Intent(PaperDetailsActivity.this, PdfViewActivity.class);
//                String pdfUrl = link.getText().toString();
//                intent.putExtra("pdfUrl", pdfUrl);
//                startActivity(intent);
                //http://49.232.244.90/word/preimg?url=http://arxiv.org/pdf/2404.07106v1
//                String url = ApiConfig.CIYUN_URl+ApiConfig.PDF_PREVIEW+"?url="+link.getText().toString();
//                String url = link.get
                String url = link.getText().toString();
//                String url = ApiConfig.PRE_URL+ApiConfig.PDF_PREVIEW;
//                String url = "http://192.168.43.154/word/preimg?url="+link.getText().toString();
//                Toast.makeText(PaperDetailsActivity.this,url,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaperDetailsActivity.this, PdfViewActivity.class);
                intent.putExtra("pdfUrl", url);
                startActivity(intent);
            }
        });

        //GPT
        gpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //第一个弹窗
                bottomSheet();
                HashMap<String, Object> params = new HashMap<>();
                params.put("id",id);
                getGPTSummary(true,params);
//                mTypeTextView.start(str);
            }
        });

        //相关推荐
        HashMap<String, Object> params = new HashMap<>();
        params.put("type","rembyid");
        params.put("pid",id);
        getRecommendPaperList(true,params);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        newsAdapter = new NewsAdapter(this);
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Serializable obj) {
                NewsEntity newsEntity = (NewsEntity) obj;
//                String url = "http://192.168.31.32:8089/newsDetail?title=" + newsEntity.getAuthorName();
                Bundle bundle = new Bundle();
                bundle.putSerializable("newsEntity",newsEntity);
                navigateToWithBundle(PaperDetailsActivity.class, bundle);
            }
        });
    }

    private void getCiYunImage(final boolean isRefresh) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id",id);
        //API
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ApiConfig.PRE_URL+ApiConfig.CIYUN+"?id="+id)
                .build();
        Log.e("url",ApiConfig.PRE_URL+ApiConfig.CIYUN+"?id="+id);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        InputStream inputStream = responseBody.byteStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        // 在 UI 线程更新 ImageView
                        PaperDetailsActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ciyun.setImageBitmap(bitmap);
                            }
                        });
                    }
                }
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void getGPTSummary(final boolean isRefresh,HashMap<String, Object> params) {
        Api.config_ai(ApiConfig.GPT, params).getRequest(this, new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                GPTSummaryResponse response = new Gson().fromJson(res, GPTSummaryResponse.class);
                if (response != null) {
                    String keywords = response.getKeywords().getContent();
                    String summary = response.getSummary().getContent();
                    if (keywords != null && summary != null) {
                        if (isRefresh) {
                            PaperDetailsActivity.this.keywords = keywords;
                            PaperDetailsActivity.this.summary = summary;
                            // 在 UI 线程更新 ImageView
                            PaperDetailsActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTypeTextView_keywords.start(keywords);
                                    mTypeTextView_summary.start("       "+summary);
//                                    showToastSync("success");
                                }
                            });
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
                showToastSync("GPTerror");
            }
        });
    }

    private void getRecommendPaperList(final boolean isRefresh,HashMap<String, Object> params) {
        Api.config(ApiConfig.RECOM_PAPER_LIST, params).getRequest(this, new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                RecommendNewsResponse response = new Gson().fromJson(res, RecommendNewsResponse.class);
                if (response != null && response.getCode() == 200) {
                    List<NewsEntity> list = response.getData();
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
                showToastSync("error");
            }
        });
    }

    public void changeFavorState() {
        HashMap<String, Object> params= new HashMap<String, Object>();
        params.put("id",id);
        Api.config(ApiConfig.UPDATE_FAVOR, params).getRequest(this, new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                NewsListResponse response = new Gson().fromJson(res, NewsListResponse.class);
                if (response != null && response.getCode() == 200) {
                    showToastSync(response.getMsg());
                }
            }
            @Override
            public void onFailure(Exception e) {
                showToastSync("error");
            }
        });
    }

    private void bottomSheet() {
        if (bottomSheetDialog == null) {
            //创建布局
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_bottomsheet, null, false);
            bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialog);
            //设置点击dialog外部不消失
            bottomSheetDialog.setCanceledOnTouchOutside(true);
            //核心代码 解决了无法去除遮罩问题
            bottomSheetDialog.getWindow().setDimAmount(0f);
            //设置布局
            bottomSheetDialog.setContentView(view);
            //用户行为
            mDialogBehavior = BottomSheetBehavior.from((View) view.getParent());
            //dialog的高度
            mDialogBehavior.setPeekHeight(getWindowHeight());
        }
        mTypeTextView_keywords = (TypeTextView)bottomSheetDialog.findViewById(R.id.text_keyword);
        mTypeTextView_summary = (TypeTextView)bottomSheetDialog.findViewById(R.id.text_summary);
        mTypeTextView_keywords.setOnTypeViewListener( new TypeTextView.OnTypeViewListener( ) {
            @Override
            public void onTypeStart() {
            }

            @Override
            public void onTypeOver() {
            }
        });
        mTypeTextView_summary.setOnTypeViewListener( new TypeTextView.OnTypeViewListener( ) {
            @Override
            public void onTypeStart() {
            }

            @Override
            public void onTypeOver() {
            }
        });
        //展示
        bottomSheetDialog.show();
        //重新用户的滑动状态
        mDialogBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                //监听BottomSheet状态的改变
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetDialog.dismiss();
                    mDialogBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
            @Override
            public void onSlide(@NonNull View view, float v) {
                //监听拖拽中的回调，根据slideOffset可以做一些动画
            }
        });

    }
    /**
     * 计算高度(初始化可以设置默认高度)
     *
     * @return
     */
    private int getWindowHeight() {
        Resources res = this.getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        int heightPixels = displayMetrics.heightPixels;
        //设置弹窗高度为屏幕高度的3/4
        return heightPixels-heightPixels/4;
    }


}
