package com.bdev.final_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdev.final_project.NoteRetrofit.Note;

import java.util.ArrayList;


public class Note_Recycler extends RecyclerView.Adapter<Note_Recycler.NoteHolder> {
    private  static ArrayList<Note> arrayList = new ArrayList<>();
    private static String title = "";
    private static String body = "";
    static transfer trans;

    public Note_Recycler() {

    }
    public Note_Recycler(transfer trans) {
        Note_Recycler.trans = trans;
    }

    public void setArrayList(ArrayList<Note> arrayList){
        Note_Recycler.arrayList = arrayList;
        notifyDataSetChanged();
    }
    public static ArrayList<Note> getArrayList(){
        return arrayList;
    }
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new Note_Recycler.NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note note = arrayList.get(position);
        holder.textView.setText(note.getTitle());
        holder.editText.setText(note.getBody());
        holder.itemView.setOnClickListener((f)-> trans.trans(note.getId()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    static class NoteHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{
        private final TextView textView;
        private final EditText editText;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title_card);
            editText = itemView.findViewById(R.id.getDescription);
        }
    }

}
