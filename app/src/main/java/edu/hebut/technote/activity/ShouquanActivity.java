package edu.hebut.technote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import edu.hebut.technote.R;
import edu.hebut.technote.dao.UserDao;
import edu.hebut.technote.entity.User;
import edu.hebut.technote.activity.MyApplication;

public class ShouquanActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouquan);

        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);

// 支持缩放控制
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);

// 支持加载图片资源
        webView.getSettings().setLoadsImagesAutomatically(true);

// 支持自动加载 JavaScript 弹窗
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("http://localhost:8080/user/auth/success")) {
                    // 解析URL中的code参数或其他必要信息
                    Uri uri = Uri.parse(url);
                    String code = uri.getQueryParameter("code");

                    UserDao userdao=MyApplication.getInstance().getmyDataBase().userDao();
                    User user=userdao.chabyzhanghao("shj");
                    if(user==null){
                        User user_shj = new User();
                        user_shj.setZhanghao("shj");
                        user_shj.setPassword("111111");
                        user_shj.setYouxiang("1327325289@qq.com");
                        UserDao userDao=MyApplication.getInstance().getmyDataBase().userDao();
                        userDao.insert(user_shj);
                        MyApplication.getInstance().setUser_local(user_shj);
                    }
                    else{
                        MyApplication.getInstance().setUser_local(user);
                    }
                    Intent intent = new Intent(ShouquanActivity.this, LoginsuccessActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                    // 关闭当前的WebView
                    webView.setVisibility(View.GONE);
                    Toast.makeText(ShouquanActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                    return true;
                }

                // 其他链接仍在WebView中打开
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        // 加载Gitee OAuth授权页面
        String giteeOAuthUrl = "https://gitee.com/oauth/authorize?" +
                "client_id=9525505dca256e3d45533fa95c28a86c00f219121dcf2fdd5a5da1aceb17888d" +
                "&redirect_uri=http://localhost:8080/user/auth/success" +
                "&response_type=code";
        webView.loadUrl(giteeOAuthUrl);
    }
}