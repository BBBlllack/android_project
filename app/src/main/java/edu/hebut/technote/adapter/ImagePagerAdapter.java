package edu.hebut.technote.adapter;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import java.util.ArrayList;
import java.util.List;

import edu.hebut.technote.R;

public class ImagePagerAdapter extends PagerAdapter {

    private Context context;
    private List<Integer> imageResources;

    public ImagePagerAdapter(Context context, List<Integer> imageResources) {
        this.context = context;
        this.imageResources = imageResources;
    }

    @Override
    public int getCount() {
        return imageResources.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.image_item_layout, container, false);

        ImageView imageView = itemView.findViewById(R.id.image_view);
        imageView.setImageResource(imageResources.get(position));

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    // 如果你需要自动轮播，可以在MainActivity中添加计时器和切换页面的逻辑
}