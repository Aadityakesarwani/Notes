package com.innovativetools.notes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.innovativetools.notes.Models.Notes;

@Database(entities = Notes.class , version = 1 , exportSchema = false )
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    //instance of room database
    private static RoomDatabase database;
    private static final String databasename  = "NotesApp";

    public synchronized static RoomDatabase getInstance(Context context) {

        if (database == null)
        {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDatabase.class,databasename)
                    .allowMainThreadQueries().
                    fallbackToDestructiveMigration().build();

        }

        return database;
    }


    //instance of data access object
    public abstract DaoMain maindao();

}
