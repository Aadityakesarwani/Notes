package com.innovativetools.notes.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


//serializable class FOR move/send data from one activity to another

@Entity(tableName = "notes")
public class Notes implements Serializable {
     @PrimaryKey(autoGenerate = true)
     int ID = 0;

     @ColumnInfo(name = "title")
     String title = "";

    @ColumnInfo(name = "data")
     String data = "";

    @ColumnInfo(name = "date")
     String date  = "";

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getPin() {
        return pin;
    }

    public void setPin(Boolean pin) {
        this.pin = pin;
    }

    @ColumnInfo(name = "pin")
     boolean pin = false;

}
