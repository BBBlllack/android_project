package edu.hebut.technote.activity;

import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.hebut.technote.R;
import edu.hebut.technote.adapter.PapersPreviewAdapter;
import edu.hebut.technote.api.Api;
import edu.hebut.technote.api.ApiConfig;
import edu.hebut.technote.api.TtitCallback;
import edu.hebut.technote.entity.PaperCategoryEntity;
import edu.hebut.technote.fragment.PapersFragment;
import edu.hebut.technote.response.PaperCategoryResponse;

public class PapersPreviewActivity extends BaseActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;
    private ViewPager viewPager;
    private EditText editText;
    private SlidingTabLayout slidingTabLayout;
    private int CategoryId;

    @Override
    protected int initLayout() {
        return R.layout.activity_papers_preview;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.ViewPager);
        slidingTabLayout = findViewById(R.id.slidingTabLayout);
        editText = findViewById(R.id.et_search);
    }

    @Override
    protected void initData() {
        CategoryId = getIntent().getFlags();
        getCategoryList();
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("跳转到搜索界面");
                navigateTo(SearchActivity.class);
            }
        });
    }

    private void getCategoryList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type","children");
        params.put("pid",CategoryId);
        Api.config(ApiConfig.PAPER_CATEGORY_LIST, params).getRequest(this, new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                PapersPreviewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(String.valueOf(CategoryId));
                        PaperCategoryResponse response = new Gson().fromJson(res, PaperCategoryResponse.class);
                        if (response != null && response.getCode() == 200) {
                            //二级目录集合
                            List<PaperCategoryEntity> list = response.getData();
                            if (list != null && list.size() > 0) {
                                mTitles = new String[list.size()];
                                for (int i = 0; i < list.size(); i++) {
                                    mTitles[i] = list.get(i).getName();
                                    mFragments.add(PapersFragment.newInstance(list.get(i).getPid()));
                                }
                                viewPager.setOffscreenPageLimit(mFragments.size());
                                viewPager.setAdapter(new PapersPreviewAdapter(getSupportFragmentManager(), mTitles, mFragments));
                                slidingTabLayout.setViewPager(viewPager);
                            }
                            if (list.size()==0){
                                mTitles = new String[1];
                                mTitles[0] = null;
                                mFragments.add(PapersFragment.newInstance(CategoryId));
                                viewPager.setAdapter(new PapersPreviewAdapter(getSupportFragmentManager(), mTitles, mFragments));
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }
}