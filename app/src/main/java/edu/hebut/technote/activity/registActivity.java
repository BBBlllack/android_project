package edu.hebut.technote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import edu.hebut.technote.R;
import edu.hebut.technote.dao.UserDao;
import edu.hebut.technote.entity.User;

public class registActivity extends AppCompatActivity {

    EditText userid;
    EditText email;
    EditText password;
    EditText qrpassword;
    String userid1=null;
    String email1=null;
    String password1=null;
    String qrpassword1=null;
    public UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
    }

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.fanhui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        findViewById(R.id.zhuce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userid=(EditText)findViewById(R.id.userid);
                email=(EditText)findViewById(R.id.email);
                password=(EditText)findViewById(R.id.password);
                qrpassword=(EditText)findViewById(R.id.qrpassword);
                userid1=userid.getText().toString();
                email1=email.getText().toString();
                password1=password.getText().toString();
                qrpassword1=qrpassword.getText().toString();
                if(Objects.equals(userid1, "")){
                    Toast.makeText(getApplicationContext(), "输入账号不能为空!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(Objects.equals(email1, "")){
                        Toast.makeText(getApplicationContext(), "输入邮箱不能为空!!!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(Objects.equals(password1, "")){
                            Toast.makeText(getApplicationContext(), "输入密码不能为空!!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(qrpassword1.equals("")){
                                Toast.makeText(getApplicationContext(), "再次输入密码不能为空!!!", Toast.LENGTH_SHORT).show();
                            }
                            else if(!password1.equals(qrpassword1)){
                                Toast.makeText(getApplicationContext(), "请保持密码一致！！！", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                userDao = MyApplication.getInstance().getmyDataBase().userDao();
                                User u=new User();
                                u.setZhanghao(userid1);
                                u.setYouxiang(email1);
                                u.setPassword(password1);
                                userDao.insert(u);
                                Toast.makeText(getApplicationContext(), "用户注册成功！！！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
    }
}