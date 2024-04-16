package edu.hebut.technote.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.hebut.technote.entity.Email;

@Dao
public interface EmailDao {

    @Insert
    void insert(Email... email);

    @Delete
    Void delete(Email... email);

    @Query("select * from Emails where id=:id")
    Email chabyid(int id);

    @Update
    int update(Email... email);

    @Query("select * from Emails where user_id=:id")
    List<Email> chabyuserid(int id);

    @Query("select * from Emails")
    LiveData<List<Email>> chaallnews();

    @Query("select * from Emails where user_id=:id or fromw=:fromw")
    LiveData<List<Email>> chabyuseridall(int id,String fromw);
}
