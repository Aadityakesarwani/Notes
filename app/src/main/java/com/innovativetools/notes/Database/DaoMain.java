package com.innovativetools.notes.Database;

import static android.icu.text.MessagePattern.ArgType.SELECT;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.innovativetools.notes.Models.Notes;

import java.util.List;

//data access object
@androidx.room.Dao
public interface DaoMain {

    @Insert(onConflict = REPLACE)
    void insert(Notes data);

    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Notes> getall();

    @Query("UPDATE notes SET title = :title , data = :notes WHERE ID = :id")
    void update( int id ,String title , String notes);

    @Delete
    void delete(Notes data);

}
