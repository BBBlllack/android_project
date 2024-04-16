package edu.hebut.technote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.hebut.technote.Mailutils.QQMailSender;

import edu.hebut.technote.dao.EmailDao;
import edu.hebut.technote.dao.UserDao;
import edu.hebut.technote.entity.Email;
import edu.hebut.technote.entity.User;
import edu.hebut.technote.R;

public class MailActivity extends AppCompatActivity implements View.OnClickListener {
    public UserDao userDao;
    public EmailDao emailDao;
    public User u3;
    private EditText logintwo_email;
    String logintwoemail=null;
    private EditText yz_code;
    String yzcode=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        logintwo_email=(EditText)findViewById(R.id.logintwo_email);
        yz_code=(EditText)findViewById(R.id.yz_code);
        findViewById(R.id.logintwo).setOnClickListener(this);
        findViewById(R.id.send_code).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logintwo:
                logintwoemail=logintwo_email.getText().toString();
                yzcode=yz_code.getText().toString();
                if(yzcode==null){
                    Toast.makeText(getApplicationContext(), "请输入验证码!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(yzcode.equals("ABCDEFG")){
                        userDao = MyApplication.getInstance().getmyDataBase().userDao();
                        u3=userDao.chabyyouxiang(logintwoemail);
                        MyApplication.getInstance().setUser_local(u3);
                        Intent intent = new Intent(MailActivity.this, LoginsuccessActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "验证码错误!!!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.send_code:
                            logintwoemail=logintwo_email.getText().toString();
                            if(logintwoemail==null){
                                Toast.makeText(getApplicationContext(), "邮箱不能为空!!!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                userDao=MyApplication.getInstance().getmyDataBase().userDao();
                                User e=userDao.chabyyouxiang(logintwoemail);
                                if (e==null){
                                    Toast.makeText(getApplicationContext(), "该邮箱未注册", Toast.LENGTH_SHORT).show();
                                }else {
                                    new SendEmailAsyncTask().execute();
                                    QQMailSender.sendEmail("2429415887@qq.com","vywjfrgslcujdibj",logintwoemail,"邮箱登录","验证码是:ABCDEFG");
                                    Toast.makeText(getApplicationContext(), "验证码已发至邮箱", Toast.LENGTH_SHORT).show();
                                    emailDao=MyApplication.getInstance().getmyDataBase().emailDao();
                                    Email email=new Email();
                                    email.setFromw("2429415887@qq.com");
                                    email.setTitleemail("邮箱登录。\n验证码是:ABCDEFG");
                                    email.setUserid(userDao.chabyyouxiang(logintwoemail).getId());
                                    emailDao.insert(email);
                                }
                            }
                break;
            case  R.id.back22:
                Intent intent = new Intent(MailActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
    private class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            QQMailSender.sendEmail("2429415887@qq.com","vywjfrgslcujdibj",logintwoemail,"邮箱登录","验证码是:ABCDEFG");
            return true;
        }
        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(MailActivity.this, "邮件已成功发送", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MailActivity.this, "发送邮件失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}