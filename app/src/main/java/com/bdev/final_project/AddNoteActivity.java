package com.bdev.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdev.final_project.NoteRetrofit.Note;
import com.bdev.final_project.NoteRetrofit.NoteViewModel;

import java.util.ArrayList;

public class AddNoteActivity extends AppCompatActivity {
    EditText title,description1;
    Button add;

    private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InJva2F5YTFAZ21haWwuY29tIiwiaWQiOiI2MzMzNTcxNWRiOWYzMDAwMjFmY2E4YTIiLCJleHBpcmF0aW9uRGF0ZSI6MTY2OTQ5MzAyOSwiaWF0IjoxNjY0MzA5MDI5fQ.-oFPzPRldPO-B8DNMN3XXfLTUp_dgLkCO_qAnYZviZA";
    private static final String GET_NOTES_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InJva2F5YTFAZ21haWwuY29tIiwiaWQiOiI2MzMzNTcxNWRiOWYzMDAwMjFmY2E4YTIiLCJleHBpcmF0aW9uRGF0ZSI6MTY2OTQ5MzAyOSwiaWF0IjoxNjY0MzA5MDI5fQ.-oFPzPRldPO-B8DNMN3XXfLTUp_dgLkCO_qAnYZviZA";
    private static final String FINAL_TOKEN_POST = "Bearer "+TOKEN;
    private static final String FINAL_TOKEN_GET = "Bearer "+GET_NOTES_TOKEN;
    Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        add = findViewById(R.id.button);
        title = findViewById(R.id.titleaddnote);
        description1 = findViewById(R.id.getDescription);

        Switch sw = findViewById(R.id.switch1);
        NoteViewModel noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        Note_Recycler note_recycler = new Note_Recycler((s) -> {
            noteViewModel.getId().setValue(s);
            Intent i = new Intent(this.getApplicationContext(),NoteDetails.class);
            startActivity(i);
        });
        ToDo_recycler todo_recycler = new ToDo_recycler(id -> {
            noteViewModel.setId(id);
            Intent i = new Intent(this.getApplicationContext(),NoteDetails.class);
            startActivity(i);
        });
        description1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(description1.getText().toString().length()>5){
                    note = new Note(title.getText().toString(), description1.getText().toString());
                    noteViewModel.postNotes(note,FINAL_TOKEN_POST).observe(AddNoteActivity.this,(obs)-> note.setId(obs.getId()));
                }

            }
        });

        add.setOnClickListener(s->{


            Boolean switchState = sw.isChecked();
            if(switchState){
                ArrayList<Note> arrayListt = ToDo_recycler.getArrayList();
                arrayListt.add(note);
                noteViewModel.getList(FINAL_TOKEN_GET).observe(this, new Observer<ArrayList<Note>>() {
                    @Override
                    public void onChanged(ArrayList<Note> arrayList) {
                        todo_recycler.setArrayList(arrayListt);
                        noteViewModel.setTodo_recyclerMutableLiveData(todo_recycler);
                    }
                });
            }else{
                ArrayList<Note> arrayListt = Note_Recycler.getArrayList();
                arrayListt.add(note);
                noteViewModel.getList(FINAL_TOKEN_GET).observe(this, arrayList -> {
                    note_recycler.setArrayList(arrayListt);
                    noteViewModel.setNote_recyclerMutableLiveData(note_recycler);
                });
            }


            finish();
        });
    }
}