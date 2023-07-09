package com.innovativetools.notes.Models;
import androidx.cardview.widget.CardView;

public interface NotesClicklistners {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);
}
