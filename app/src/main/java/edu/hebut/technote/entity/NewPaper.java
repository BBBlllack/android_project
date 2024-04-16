package edu.hebut.technote.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "NewPapers")
public class NewPaper {
    //编号
    @PrimaryKey(autoGenerate = true)
    private Integer Id;
    //标题
    private String biaoti;
    private String time;
    //内容
    private String title;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "标题:" + biaoti +'\n'+
                "发表时间:'" + time + '\n' +
                "内容:'" + title;
    }
}