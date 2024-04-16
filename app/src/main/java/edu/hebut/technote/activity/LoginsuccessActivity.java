package edu.hebut.technote.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import edu.hebut.technote.fragment.HomeFragment;
import edu.hebut.technote.fragment.NewsFragment;
import edu.hebut.technote.fragment.ShouyeFragment;
import edu.hebut.technote.fragment.XiaoxiFragment;
import edu.hebut.technote.fragment.myFragment;
import edu.hebut.technote.R;


public class LoginsuccessActivity extends AppCompatActivity {

    public Fragment fragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsuccess);

        if(savedInstanceState==null){
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.fr1,new ShouyeFragment()).
                    commit();
        }
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;

                switch (item.getItemId()){
                    case R.id.home1:
                        fragment=new ShouyeFragment();
                        break;
                    case R.id.fenlei:
//                        fragment=new FenleiFragment();
                        fragment = new HomeFragment();
                        break;
                    case R.id.tuijian:
//                        fragment=new TuijianFragment();
                        fragment = new NewsFragment();
                        break;
                    case R.id.xiaoxi:
                        fragment=new XiaoxiFragment();
                        break;
                    case R.id.my:
                        fragment=new myFragment();
                        break;
                }
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fr1,fragment).
                        commit();
                return  true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}