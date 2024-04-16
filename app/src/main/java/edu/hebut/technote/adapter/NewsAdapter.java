package edu.hebut.technote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import edu.hebut.technote.R;
import edu.hebut.technote.activity.MyApplication;
import edu.hebut.technote.entity.NewsEntity;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<NewsEntity> datas;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setDatas(List<NewsEntity> datas) {
        this.datas = datas;
    }

    public NewsAdapter(Context context) {
        this.mContext = context;
    }

    public NewsAdapter(Context context, List<NewsEntity> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    public interface OnItemClickListener {
        void onItemClick(Serializable obj);
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {
        private TextView title;
//        private TextView author;
        private TextView theme;
        private TextView time;
        private TextView detail;
        private NewsEntity newsEntity;

        public ViewHolderOne(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title);
//            author = view.findViewById(R.id.author);
            theme = view.findViewById(R.id.theme);
            time = view.findViewById(R.id.time);
            detail = view.findViewById(R.id.detail);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(newsEntity);
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsEntity newsEntity = datas.get(position);
        ViewHolderOne vh = (ViewHolderOne) holder;
        if (MyApplication.getInstance().isLanguage()){
            vh.title.setText(newsEntity.getTitleZh());
            vh.detail.setText(newsEntity.getDetailZh());
        }else{
            String str = newsEntity.getTitleEn();
            vh.title.setText(str.substring(str.indexOf('】')+2));
            vh.detail.setText(newsEntity.getDetailEn());
        }
        vh.theme.setText(newsEntity.getFname()+"-"+newsEntity.getPname());
        vh.time.setText(newsEntity.getCreatedAt());
        vh.newsEntity = newsEntity;
    }

    @Override
    public int getItemCount() {
        if (datas != null && datas.size() > 0) {
            return datas.size();
        } else {
            return 0;
        }
    }
}
