package edu.hebut.technote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import edu.hebut.technote.entity.Email;
import edu.hebut.technote.R;


public class MailXiangqingActivity extends AppCompatActivity implements View.OnClickListener{

    String tv_descriptionstr=null;
    String list_commentstr=null;
    private TextView tv_description;
    private TextView list_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_xiangqing);

        findViewById(R.id.huifu1).setOnClickListener(this);
        findViewById(R.id.buy).setOnClickListener(this);
        findViewById(R.id.back2).setOnClickListener(this);
        tv_description=(TextView)findViewById(R.id.tv_description);
        list_comment=(TextView)findViewById(R.id.list_comment);

//        Email email123=MyApplication.getInstance().getEmail();
//        tv_descriptionstr=email123.getFromw();
//        list_commentstr=email123.getTitleemail();
//
//        tv_description.setText(tv_descriptionstr);
//        list_comment.setText(list_commentstr);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Email email123=MyApplication.getInstance().getEmail();
        if (email123 != null) {
            Log.e("MailXiangqing", "不是0");
            tv_descriptionstr=email123.getFromw();
            list_commentstr=email123.getTitleemail();
        } else {
            Log.e("MailXiangqing", "Received email object is null");
        }
        tv_description.setText(tv_descriptionstr);
        list_comment.setText(list_commentstr);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.huifu1:
                Intent intent = new Intent(MailXiangqingActivity.this,HuifuMailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.buy:
            case R.id.back2:
                Intent intent2 = new Intent(MailXiangqingActivity.this, LoginsuccessActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                break;
        }
    }
}