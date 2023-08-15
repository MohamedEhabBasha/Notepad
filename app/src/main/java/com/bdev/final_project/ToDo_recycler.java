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

public class ToDo_recycler extends RecyclerView.Adapter<ToDo_recycler.ToDoHolder>{
    private  static ArrayList<Note> arrayList = new ArrayList<>();
    static transfer trans;
    public ToDo_recycler () {

    }
    public ToDo_recycler(transfer trans) {
        ToDo_recycler.trans = trans;
    }

    public void setArrayList(ArrayList<Note> arrayList){
        ToDo_recycler.arrayList = arrayList;
        notifyDataSetChanged();
    }
    public static ArrayList<Note> getArrayList(){
        return arrayList;
    }
    @NonNull
    @Override
    public ToDo_recycler.ToDoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ToDo_recycler.ToDoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDo_recycler.ToDoHolder holder, int position) {
        Note todo = arrayList.get(position);
        holder.textView.setText(todo.getTitle());
        holder.editText.setText(todo.getBody());
        holder.itemView.setOnClickListener((f)->{
            trans.trans(todo.getId());
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class ToDoHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{
        private final TextView textView;
        private final EditText editText;
        public ToDoHolder (@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title_card);
            editText = itemView.findViewById(R.id.getDescription);
        }
    }
}
