package edu.hebut.technote.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import edu.hebut.technote.R;
import edu.hebut.technote.dao.NewPaperDao;
import edu.hebut.technote.entity.NewPaper;


public class LishiActivity extends AppCompatActivity {

    private LiveData<List<NewPaper>> allNews;
    public NewPaperDao newPaperDao;
    private ArrayAdapter<NewPaper> adapter_new;
    ListView listView_new;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishi);
        listView_new = findViewById(R.id.lishiliebiao);
        newPaperDao = MyApplication.getInstance().getmyDataBase().newPaperDao();
        allNews = newPaperDao.chaallnews();
        allNews.observe(LishiActivity.this, new Observer<List<NewPaper>>() {
            @Override
            public void onChanged(List<NewPaper> newPapers) {
                adapter_new = new ArrayAdapter<>(LishiActivity.this, R.layout.items, newPapers);
                listView_new.setAdapter(adapter_new);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.back2).setOnClickListener(view -> {
            Intent intent = new Intent(LishiActivity.this, LoginsuccessActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}