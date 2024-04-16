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

public class UpdatepersonActivity extends AppCompatActivity {

    public UserDao userDao;
    EditText showUser;
    EditText name;
    EditText showemail;
    EditText address;
    public String showUserstr=null;
    public String namestr=null;
    public String showmailstr=null;
    public String addressstr=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateperson);
        findViewById(R.id.usermsg_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdatepersonActivity.this,PersonActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        findViewById(R.id.changemsg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showUser=(EditText)findViewById(R.id.showUser);
                name=(EditText)findViewById(R.id.name);
                showemail=(EditText)findViewById(R.id.showemail);
                address=(EditText)findViewById(R.id.address);

                showUserstr=showUser.getText().toString();
                namestr=name.getText().toString();
                showmailstr=showemail.getText().toString();
                addressstr=address.getText().toString();

                userDao=MyApplication.getInstance().getmyDataBase().userDao();
                if(!Objects.equals(showUserstr, "")){
                    MyApplication.getInstance().getUser_local().setZhanghao(showUserstr);
                }
                if(!Objects.equals(namestr, "")){
                    MyApplication.getInstance().getUser_local().setName(namestr);
                }
                if(!Objects.equals(showmailstr, "")){
                    MyApplication.getInstance().getUser_local().setYouxiang(showmailstr);
                }
                if(!Objects.equals(addressstr, "")){
                    MyApplication.getInstance().getUser_local().setAddress(addressstr);
                }
                userDao.update(MyApplication.getInstance().getUser_local());
                Toast.makeText(getApplicationContext(), "更新成功！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}