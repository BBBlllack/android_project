package edu.hebut.technote.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Emails",foreignKeys=@ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "user_id"),indices = {@Index(value = {"user_id"})})
public class Email {
    //编号
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    //发件人
    @NonNull
    private String fromw;
    //内容
    private String titleemail;
    //收件用户id
    @ColumnInfo(name = "user_id")
    private int userid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getFromw() {
        return fromw;
    }

    public void setFromw(@NonNull String fromw) {
        this.fromw = fromw;
    }

    public String getTitleemail() {
        return titleemail;
    }

    public void setTitleemail(String titleemail) {
        this.titleemail = titleemail;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "id:" + id + "\n接/发邮箱:" + fromw + '\n';
    }
}
