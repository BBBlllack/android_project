package edu.hebut.technote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import edu.hebut.technote.R;

public class giteeActivity extends AppCompatActivity implements View.OnClickListener{


    Button login_gitee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gitee);
        login_gitee=findViewById(R.id.login_gitee);
        login_gitee.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login_gitee:
                Intent intent = new Intent(giteeActivity.this, ShouquanActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}