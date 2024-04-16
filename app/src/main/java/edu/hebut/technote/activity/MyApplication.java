package edu.hebut.technote.activity;

import android.app.Application;

import androidx.room.Room;

import java.util.HashMap;

import edu.hebut.technote.database.MyDataBase;
import edu.hebut.technote.entity.Email;
import edu.hebut.technote.entity.User;

public class MyApplication extends Application {
    private final static String TAG = "MyApplication";
    public static MyApplication mApp;
    public HashMap<String, String> infoMap = new HashMap<String, String>();
    public MyDataBase myDataBase;
    private User user_local;
    private Email email;
    private boolean language = true;//true中文；false英文；

    public boolean isLanguage() {
        return language;
    }

    public void setLanguage(boolean language) {
        this.language = language;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public User getUser_local() {
        return user_local;
    }

    public void setUser_local(User user_local) {
        this.user_local = user_local;
    }

    public MyApplication() {
        super();
    }

    public static MyApplication getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        myDataBase= Room.databaseBuilder(this,MyDataBase.class,"myDataBase.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
    public MyDataBase getmyDataBase(){
        return myDataBase;
    }
}
