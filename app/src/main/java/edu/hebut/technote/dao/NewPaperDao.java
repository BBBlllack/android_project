package edu.hebut.technote.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.hebut.technote.entity.NewPaper;


@Dao
public interface NewPaperDao {

    @Insert
    void insert(NewPaper... newpaper);

    @Delete
    Void delete(NewPaper... newPaper);

    @Query("select * from newpapers where id=:id")
    NewPaper chabyid(Integer id);

    @Query("select * from newpapers where biaoti=:biaoti")
    NewPaper chabybiaoti(String biaoti);

    @Update
    int update(NewPaper... newPaper);

    @Query("select * from NewPapers")
    LiveData<List<NewPaper>> chaallnews();
}