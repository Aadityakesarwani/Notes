package com.innovativetools.notes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.innovativetools.notes.Adapter.Listadapter;
import com.innovativetools.notes.Database.RoomDatabase;
import com.innovativetools.notes.Models.Notes;
import com.innovativetools.notes.Models.NotesClicklistners;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    Listadapter listadapter;
    FloatingActionButton fab;
    List<Notes> notes = new ArrayList<>();
    RoomDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SearchView searchView  = findViewById(R.id.searchview);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler);

        database = RoomDatabase.getInstance(this);
        notes = database.maindao().getall();



        //onclick start new activity and click on save
        fab.setOnClickListener(view -> {
          Intent intent = new Intent(this,Notestaker.class);
          launcher.launch(intent);
//            startActivityForResult(intent,20);
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        updateRecycler(notes);

    }

    private void filter(String newText) {

        List<Notes>filterdnotes  = new ArrayList<>();

        for(Notes singlenotes : notes){

            if(singlenotes.getTitle().toLowerCase().contains(newText.toLowerCase())
               || singlenotes.getData().toLowerCase().contains(newText.toLowerCase()))
            {
                filterdnotes.add(singlenotes);
            }
        }

        listadapter.filteradapter(filterdnotes);
    }


    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onActivityResult(ActivityResult result) {

                 if(result.getResultCode() == RESULT_OK){

                     if(result.getData() != null){

                         Notes newnotes = (Notes) result.getData().getSerializableExtra("notess");
                         database.maindao().insert(newnotes);
                         notes.clear();
                         notes.addAll(database.maindao().getall());
                         listadapter.notifyDataSetChanged();
                     }
                 }
//                 else{
//                     if(result.getResultCode() == 100){
//                         Notes newnotes = (Notes) result.getData().getSerializableExtra("notess");
//                         database.maindao().update(newnotes.getID(),newnotes.getTitle(), newnotes.getData());
//                         notes.clear();
//                         notes.addAll(database.maindao().getall());
//                         listadapter.notifyDataSetChanged();
//                     }
//                 }

                }
            });


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if(requestCode == 20) {
//            if (resultCode == RESULT_OK) {
//                assert data != null;
//                if(data.getData() != null){
//
//                    Notes newnotes = (Notes) data.getSerializableExtra("notess");
//                    database.maindao().insert(newnotes);
//                    notes.clear();
//                    notes.addAll(database.maindao().getall());
//                    listadapter.notifyDataSetChanged();
//                }
//            }
//        }
//        else

            if(requestCode == 10){
            if (resultCode == RESULT_OK) {
                Notes newnotes = (Notes) data.getSerializableExtra("notess");
                         database.maindao().update(newnotes.getID(),newnotes.getTitle(), newnotes.getData());
                         notes.clear();
                         notes.addAll(database.maindao().getall());
                         listadapter.notifyDataSetChanged();
            }
        }
    }


    private void updateRecycler(List<Notes> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        listadapter = new Listadapter(MainActivity.this,notes,notesClicklistners);
        recyclerView.setAdapter(listadapter);
    }


    private final NotesClicklistners notesClicklistners = new NotesClicklistners() {
        @Override
        public void onClick(Notes notes) {
            Intent intent = new Intent(MainActivity.this,Notestaker.class);
            intent.putExtra("old_note",notes);
            startActivityForResult(intent,10);
        }


        @Override
        public void onLongClick(Notes notes, CardView cardView) {

        }
    };
}