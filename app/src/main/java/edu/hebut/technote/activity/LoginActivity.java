package edu.hebut.technote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

import edu.hebut.technote.Mailutils.Code;
import edu.hebut.technote.R;
import edu.hebut.technote.dao.UserDao;
import edu.hebut.technote.entity.User;

public class LoginActivity extends AppCompatActivity {

    public String realCode;
    public String codestr;
    public User u1;
    public UserDao userDao;
    public EditText user;
    String user1=null;
    public EditText pwd;
    String pwd1=null;
    ImageView showcode1;
    EditText phoneCodes1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        showcode1=findViewById(R.id.showcode);
        phoneCodes1=findViewById(R.id.phoneCodes);

        showcode1.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.wangjimima147).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,forgetpwdActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.registerpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,registActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        findViewById(R.id.login_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        findViewById(R.id.imageButton_gitee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,giteeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        showcode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcode1.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
            }
        });
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user=(EditText)findViewById(R.id.username);
                user1=user.getText().toString();

                pwd=(EditText)findViewById(R.id.password);
                pwd1=pwd.getText().toString();

                codestr=phoneCodes1.getText().toString();

                if(Objects.equals(user1, "")){
                    Toast.makeText(getApplicationContext(), "账号不能为空!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pwd1.equals("")) {
                        Toast.makeText(getApplicationContext(), "密码不能为空!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        if(codestr.equals("")){
                            Toast.makeText(getApplicationContext(), "验证码不能为空!!!", Toast.LENGTH_SHORT).show();
                        }else{
                                userDao = MyApplication.getInstance().getmyDataBase().userDao();
                                u1 = userDao.chabyzm(user1, pwd1);
                                if (u1 == null) {
                                    Toast.makeText(getApplicationContext(), "账号密码错误!!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    MyApplication.getInstance().setUser_local(u1);
                                    Intent intent = new Intent(LoginActivity.this, LoginsuccessActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                        }
                    }
                }
            }
        });
    }
}