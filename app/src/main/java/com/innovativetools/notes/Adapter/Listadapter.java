package com.innovativetools.notes.Adapter;

import android.content.Context;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.innovativetools.notes.Models.Notes;
import com.innovativetools.notes.Models.NotesClicklistners;
import com.innovativetools.notes.R;
import java.util.List;

public class Listadapter extends RecyclerView.Adapter<ListvieHolder> {



    private Context context;
    private List<Notes> list;
    private NotesClicklistners clicklistners;
    private ActionMode actionMode;



    public Listadapter(Context context, List<Notes> list, NotesClicklistners clicklistners) {
        this.context = context;
        this.list = list;
        this.clicklistners = clicklistners;
    }


    @NonNull
    @Override
    public ListvieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListvieHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListvieHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.title.setSelected(true);
        holder.notes.setText(list.get(position).getData());
        holder.date.setText(list.get(position).getDate());


        if(list.get(position).getPin()){
            holder.pin.setImageResource(R.drawable.pin);
        }
        else{
            holder.pin.setImageResource(0);
        }

//        int colorcode = getRandomcolor();
//        holder.notescontainer.setCardBackgroundColor(holder.itemView.getResources().getColor(colorcode));


        holder.notescontainer.setOnClickListener(view -> {
            clicklistners.onClick(list.get(holder.getAdapterPosition()));

        });

        holder.notescontainer.setOnLongClickListener(view -> {
            clicklistners.onLongClick(list.get(holder.getAdapterPosition()), holder.notescontainer);
            return  true;
        });
    }



    public void filteradapter(List<Notes> filterlist){
        list = filterlist;
        notifyDataSetChanged();
    }


//    private int getRandomcolor(){
//        List<Integer> colorcode =  new ArrayList<>();
//
//        colorcode.add(R.color.c1);
//        colorcode.add(R.color.c2);
//        colorcode.add(R.color.c3);
//        colorcode.add(R.color.c4);
//        colorcode.add(R.color.c5);
//        colorcode.add(R.color.c6);
//        colorcode.add(R.color.c7);
//
//        Random random = new Random();
//        int randomcolor = random.nextInt(colorcode.size());
//        return colorcode.get(randomcolor);
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class ListvieHolder extends RecyclerView.ViewHolder{

    CardView notescontainer;
    TextView title , notes ,date;
    ImageView pin;
    public ListvieHolder(@NonNull View itemView) {
        super(itemView);

        notescontainer = itemView.findViewById(R.id.notes_container);
        title = itemView.findViewById(R.id.text_title);
        notes = itemView.findViewById(R.id.text_notes);
        date = itemView.findViewById(R.id.text_date);
        pin = itemView.findViewById(R.id.img_pin);
    }

}
