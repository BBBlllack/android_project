package edu.hebut.technote.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users",indices = {@Index(value = {"id"})})
public class User {
    //序号
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int Id;
    //账号
    @NonNull
    private String zhanghao;

    public String getZhanghao() {
        return zhanghao;
    }

    public void setZhanghao(String zhanghao) {
        this.zhanghao = zhanghao;
    }

    //姓名
    private String name;
    //密码
    @NonNull
    private String password;
    //邮箱
    @NonNull
    private String youxiang;
    //地址
    private String address;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYouxiang() {
        return youxiang;
    }

    public void setYouxiang(String youxiang) {
        this.youxiang = youxiang;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "user{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", youxiang='" + youxiang + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}