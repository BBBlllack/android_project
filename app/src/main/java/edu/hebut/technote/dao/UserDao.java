package edu.hebut.technote.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.hebut.technote.entity.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User... user);

    @Delete
    void Delete(User... user);

    @Update
    int update(User... user);

    @Query("select * from Users")
    List<User> chaall();

    @Query("select * from Users where zhanghao=:zhanghao and password=:password")
    User chabyzm(String zhanghao,String password);

    @Query("select * from Users where zhanghao=:zhanghao")
    User chabyzhanghao(String zhanghao);

    @Query("select password from Users where zhanghao=:zhanghao and youxiang=:youxiang")
    String chabyzhanghaoyouxiang(String zhanghao,String youxiang);

    @Query("select * from Users where youxiang=:youxiang")
    User chabyyouxiang(String youxiang);


}
