package edu.hebut.technote.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import edu.hebut.technote.dao.EmailDao;
import edu.hebut.technote.dao.NewPaperDao;
import edu.hebut.technote.dao.UserDao;
import edu.hebut.technote.entity.Email;
import edu.hebut.technote.entity.NewPaper;
import edu.hebut.technote.entity.User;

@Database(entities = {User.class, NewPaper.class, Email.class},version = 7)
public abstract class MyDataBase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract NewPaperDao newPaperDao();
    public abstract EmailDao emailDao();
}
