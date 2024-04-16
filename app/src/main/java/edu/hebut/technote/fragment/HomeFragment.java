package edu.hebut.technote.fragment;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import edu.hebut.technote.R;
import edu.hebut.technote.activity.CollectActivity;
import edu.hebut.technote.activity.PapersPreviewActivity;
import edu.hebut.technote.utils.TypeTextView;


public class HomeFragment extends BaseFragment {

    private View btn_1;
    private View btn_2;
    private View btn_3;
    private View btn_4;
    private View btn_5;
    private View btn_6;
    private View btn_7;
    private View btn_8;
    private View btn_9;
    private View btn_10;
    private View btn_collect;
    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetBehavior mDialogBehavior;
    private TypeTextView mTypeTextView;
////    private View[] views;
//    private HashMap<Integer, Integer> params;

//    private void initMap(){
//        params = new HashMap<Integer, Integer>();
//        params.put(1,2);
//        params.put(2,8);
//        params.put(3,9);
//        params.put(4,10);
//        params.put(5,11);
//        params.put(6,12);
//        params.put(7,13);
//        params.put(8,14);
//        params.put(9,15);
//        params.put(10,16);
//    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        btn_1 = mRootView.findViewById(R.id.mokuai1);
        btn_2 = mRootView.findViewById(R.id.mokuai2);
        btn_3 = mRootView.findViewById(R.id.mokuai3);
        btn_4 = mRootView.findViewById(R.id.mokuai4);
        btn_5 = mRootView.findViewById(R.id.mokuai5);
        btn_6 = mRootView.findViewById(R.id.mokuai6);
        btn_7 = mRootView.findViewById(R.id.mokuai7);
        btn_8 = mRootView.findViewById(R.id.mokuai8);
        btn_9 = mRootView.findViewById(R.id.mokuai9);
        btn_10 = mRootView.findViewById(R.id.mokuai10);

        btn_collect = mRootView.findViewById((R.id.collect));
//        mTypeTextView = (TypeTextView)bottomSheetDialog.findViewById(R.id.typeTxtId);
    }

    @Override
    protected void initData() {
//        initMap();
//        views = new View[10];
//        views[0] = mRootView.findViewById(R.id.mokuai1);
//        views[1] = mRootView.findViewById(R.id.mokuai2);
//        for (int i = 0; i < views.length; i++) {
//            int finalI = i;
//            views[finalI].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    navigateTo(PapersPreviewActivity.class,new Bundle(params.get(finalI)));
//                }
//            });
//        }
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToWithFlag(PapersPreviewActivity.class,2);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToWithFlag(PapersPreviewActivity.class,8);
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToWithFlag(PapersPreviewActivity.class,9);
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToWithFlag(PapersPreviewActivity.class,10);
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToWithFlag(PapersPreviewActivity.class,11);
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToWithFlag(PapersPreviewActivity.class,12);
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToWithFlag(PapersPreviewActivity.class,13);
            }
        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToWithFlag(PapersPreviewActivity.class,14);
            }
        });
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToWithFlag(PapersPreviewActivity.class,15);
            }
        });
        btn_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToWithFlag(PapersPreviewActivity.class,16);
            }
        });

        btn_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("跳转到收藏界面");
                navigateTo(CollectActivity.class);
            }
        });
    }

//    private void bottomSheet() {
//        if (bottomSheetDialog == null) {
//            //创建布局
//            View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_bottomsheet, null, false);
//            bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialog);
//            //设置点击dialog外部不消失
//            bottomSheetDialog.setCanceledOnTouchOutside(true);
//            //核心代码 解决了无法去除遮罩问题
//            bottomSheetDialog.getWindow().setDimAmount(0f);
//            //设置布局
//            bottomSheetDialog.setContentView(view);
//            //用户行为
//            mDialogBehavior = BottomSheetBehavior.from((View) view.getParent());
//            //dialog的高度
//            mDialogBehavior.setPeekHeight(getWindowHeight());
//        }
//
//        mTypeTextView = (TypeTextView)bottomSheetDialog.findViewById(R.id.text_summary);
//        mTypeTextView.setOnTypeViewListener( new TypeTextView.OnTypeViewListener( ) {
//            @Override
//            public void onTypeStart() {
//            }
//
//            @Override
//            public void onTypeOver() {
//            }
//        });
//
//        //展示
//        bottomSheetDialog.show();
//        //重新用户的滑动状态
//        mDialogBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View view, int newState) {
//                //监听BottomSheet状态的改变
//                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
//                    bottomSheetDialog.dismiss();
//                    mDialogBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                }
//            }
//            @Override
//            public void onSlide(@NonNull View view, float v) {
//                //监听拖拽中的回调，根据slideOffset可以做一些动画
//            }
//        });
//
//    }

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