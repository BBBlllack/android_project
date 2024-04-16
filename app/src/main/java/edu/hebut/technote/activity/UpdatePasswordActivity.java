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

public class UpdatePasswordActivity extends AppCompatActivity {

    public UserDao userDao;
    EditText oldpwd;
    EditText newpwd;
    EditText newpwd1;
    public String oldpwdstr=null;
    public String newpwdstr=null;
    public String newpwd1str=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        findViewById(R.id.back1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdatePasswordActivity.this, LoginsuccessActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldpwd=(EditText)findViewById(R.id.oldpwd);
                newpwd=(EditText)findViewById(R.id.newpwd);
                newpwd1=(EditText)findViewById(R.id.newpwd1);
                oldpwdstr=oldpwd.getText().toString();
                newpwdstr=newpwd.getText().toString();
                newpwd1str=newpwd1.getText().toString();
                if(Objects.equals(oldpwdstr, "")){
                    Toast.makeText(getApplicationContext(), "旧密码不能为空!!!", Toast.LENGTH_SHORT).show();
                }else{
                    if(Objects.equals(newpwdstr, "")){
                        Toast.makeText(getApplicationContext(), "新密码不能为空!!!", Toast.LENGTH_SHORT).show();
                    }else{
                        if(newpwd1str.equals("")){
                            Toast.makeText(getApplicationContext(), "再次确认密码不能为空!!!", Toast.LENGTH_SHORT).show();
                        }
                        else if(!Objects.equals(newpwdstr, newpwd1str)){
                            Toast.makeText(getApplicationContext(), "两次密码不一致!!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            userDao=MyApplication.getInstance().getmyDataBase().userDao();
                            MyApplication.getInstance().getUser_local().setPassword(newpwdstr);
                            userDao.update(MyApplication.mApp.getUser_local());
                            Toast.makeText(getApplicationContext(), "修改成功！！！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}