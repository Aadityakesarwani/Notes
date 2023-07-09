package com.innovativetools.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.innovativetools.notes.Models.Notes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notestaker extends AppCompatActivity {


    EditText heading;
    EditText data;
    Notes notes;


    boolean isoldenote = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notestaker);

        heading = findViewById(R.id.edit_title);
        data = findViewById(R.id.edit_notes);
        data.setSelected(true);

            notes = new Notes();

            try {
                notes = (Notes) getIntent().getSerializableExtra("old_note");
                heading.setText(notes.getTitle());
                data.setText(notes.getData());
                isoldenote = true;

            }catch (Exception e){
                e.printStackTrace();
            }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.done)
        {
           createNote();
           finish();
           return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createNote(){
        String title = heading.getText().toString();
        String notesdata = data.getText().toString();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format =  new SimpleDateFormat("MMM dd , yy HH:mm a");
        Date date = new Date();
//      String dat =DateFormat.getTimeInstance(DateFormat.AM_PM_FIELD).format(date);
        if(!isoldenote){
            notes  = new Notes();
        }
        notes.setTitle(title);
        notes.setData(notesdata);

        try{
            notes.setDate(format.format(date));
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent();
        intent.putExtra("notess",notes);
        setResult(RESULT_OK,intent);
//        finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
        createNote();
    }

    @Override
    protected void onStop() {
        super.onStop();
        createNote();
    }

    @Override
    protected void onResume() {
        super.onResume();
        createNote();
    }

    @Override
    public void onBackPressed() {
        createNote();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        createNote();
    }
}