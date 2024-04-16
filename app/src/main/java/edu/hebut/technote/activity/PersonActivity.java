package edu.hebut.technote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.hebut.technote.R;


public class PersonActivity extends AppCompatActivity {
    TextView zhanghao456;
    TextView name456;
    TextView showemail456;
    TextView address456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        findViewById(R.id.fh123).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonActivity.this,LoginsuccessActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        findViewById(R.id.changemsg123).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonActivity.this, UpdatepersonActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        zhanghao456=(TextView)findViewById(R.id.zhanghao456);
        name456=(TextView)findViewById(R.id.name456);
        showemail456=(TextView)findViewById(R.id.showemail456);
        address456=(TextView)findViewById(R.id.address456);
        zhanghao456.setText(MyApplication.getInstance().getUser_local().getZhanghao());
        name456.setText(MyApplication.getInstance().getUser_local().getName());
        showemail456.setText(MyApplication.getInstance().getUser_local().getYouxiang());
        address456.setText(MyApplication.getInstance().getUser_local().getAddress());
    }
}