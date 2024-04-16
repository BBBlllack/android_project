package edu.hebut.technote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;


import edu.hebut.technote.dao.UserDao;
import edu.hebut.technote.R;

public class forgetpwdActivity extends AppCompatActivity {

    public UserDao userDao;
    public String myzh=null;
    public EditText zhanghaomy;
    public String myyx=null;
    public EditText youxiangmy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpwd);
    }

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.zhaohuimima).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zhanghaomy=(EditText)findViewById(R.id.shuruzhanghao);
                myzh=zhanghaomy.getText().toString();
                youxiangmy=(EditText)findViewById(R.id.shuruyouxiang);
                myyx=youxiangmy.getText().toString();
                if(Objects.equals(myzh, "")){
                    Toast.makeText(getApplicationContext(), "账号不能为空！！！", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(myyx.equals("")){
                        Toast.makeText(getApplicationContext(), "邮箱不能为空!!!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        userDao= edu.hebut.technote.activity.MyApplication.getInstance().getmyDataBase().userDao();
                        Toast.makeText(getApplicationContext(), "您的密码是:"+userDao.chabyzhanghaoyouxiang(myzh,myyx)+"。", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        findViewById(R.id.huiqu159).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgetpwdActivity.this, edu.hebut.technote.activity.LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}