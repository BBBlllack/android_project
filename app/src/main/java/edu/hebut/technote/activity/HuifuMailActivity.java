package edu.hebut.technote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import edu.hebut.technote.dao.EmailDao;
import edu.hebut.technote.entity.Email;
import edu.hebut.technote.R;

public class HuifuMailActivity extends AppCompatActivity implements View.OnClickListener{

    public EmailDao emailDao;
    private EditText et;
    String ets=null;
    private EditText et11;
    String ets11=null;
    private EditText et_title;
    String ets_title=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huifu_mail);
        findViewById(R.id.back2).setOnClickListener(this);
        et=(EditText)findViewById(R.id.et_description);
        et11=(EditText)findViewById(R.id.et11);
        et_title=(EditText)findViewById(R.id.et_title);
        findViewById(R.id.btn_publish).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back2:
                Intent intent = new Intent(HuifuMailActivity.this,MailXiangqingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.btn_publish:
                ets=et.getText().toString();
                ets11=et11.getText().toString();
                ets_title=et_title.getText().toString();
                if(ets11==""){
                    Toast.makeText(getApplicationContext(), "收件人不能为空！", Toast.LENGTH_SHORT).show();
                }else{
                    if(ets_title==""){
                        Toast.makeText(getApplicationContext(), "标题不能为空！", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(ets==""){
                            Toast.makeText(getApplicationContext(), "内容不能为空!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            emailDao=MyApplication.getInstance().getmyDataBase().emailDao();
                            Email email=new Email();
                            email.setFromw(MyApplication.getInstance().getUser_local().getYouxiang());
                            email.setTitleemail(ets_title+"\n"+ets);
                            email.setUserid(MyApplication.getInstance().getmyDataBase().userDao().chabyyouxiang(ets11).getId());
                            emailDao.insert(email);
                            Toast.makeText(getApplicationContext(), "邮件发送成功！！！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        }
    }
}