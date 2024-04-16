package edu.hebut.technote.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import edu.hebut.technote.R;
import edu.hebut.technote.activity.CollectActivity;
import edu.hebut.technote.activity.LishiActivity;
import edu.hebut.technote.activity.ShoucangActivity;
import edu.hebut.technote.activity.UpdatePasswordActivity;
import edu.hebut.technote.activity.UpdatepersonActivity;
import edu.hebut.technote.adapter.ImagePagerAdapter;

public class ShouyeFragment extends Fragment {

    private ViewPager viewPager;
    private List<Integer> imageResources = new ArrayList<>();
    private final int[] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
    };
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=null;
        if(view==null){
            view=inflater.inflate(R.layout.fragment_shouye, container, false);
            textView=view.findViewById(R.id.tv_refresh1);
            view.findViewById(R.id.card1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), CollectActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            view.findViewById(R.id.card2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), LishiActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            view.findViewById(R.id.card3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), UpdatePasswordActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            view.findViewById(R.id.card4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), UpdatepersonActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });

            viewPager = view.findViewById(R.id.view_pager);
            for (int image : images) {
                imageResources.add(image);
            }
            viewPager.setAdapter(new ImagePagerAdapter(getActivity(), imageResources));
            // 设置自动轮播
            final Handler handler = new Handler();
            final Runnable updateTask = new Runnable() {
                @Override
                public void run() {
                    int currentItem = viewPager.getCurrentItem();
                    if (currentItem == imageResources.size() - 1) { // 当前是最后一张图片
                        viewPager.setCurrentItem(0, true); // 跳转到第一张
                    } else {
                        viewPager.setCurrentItem(currentItem + 1, true);
                    }
                    handler.postDelayed(this, 3000); // 不论是否跳转，都保持每3秒切换一次
                }
            };
            handler.postDelayed(updateTask, 3000); // 首次延迟3秒后开始切换



            textView.setSelected(true);
            textView.requestFocus(); // 请求焦点
            final Handler handler1 = new Handler();
            Runnable scrollRunnable = new Runnable() {
                private boolean shouldReset = false;
                @Override
                public void run() {
                    int width = textView.getWidth();
                    int currentScrollX = textView.getScrollX();

                    // 判断是否需要重置回起始位置
                    if (currentScrollX + 1 >= width || shouldReset) {
                        textView.scrollTo(0, 0);
                        shouldReset = false;
                    } else {
                        textView.scrollBy(1, 0);
                    }

                    // 如果已经到达边界，则设置标志以便下次滚动时重置位置
                    if (currentScrollX + 1 >= width) {
                        shouldReset = true;
                    }

                    // 重新调度任务，形成循环滚动效果
                    handler1.postDelayed(this, 5); // 5毫秒滚动一次，可根据需求调整
                }
            };
            // 启动滚动
            handler1.post(scrollRunnable);
        }
        return view;
    }
}