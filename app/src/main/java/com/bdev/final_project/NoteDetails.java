package com.bdev.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bdev.final_project.NoteRetrofit.Note;
import com.bdev.final_project.NoteRetrofit.NoteRepo;
import com.bdev.final_project.NoteRetrofit.NoteViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteDetails extends AppCompatActivity {

    private static final String GETALL = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InJva2F5YTFAZ21haWwuY29tIiwiaWQiOiI2MzMzNTcxNWRiOWYzMDAwMjFmY2E4YTIiLCJleHBpcmF0aW9uRGF0ZSI6MTY2OTQ5MzAyOSwiaWF0IjoxNjY0MzA5MDI5fQ.-oFPzPRldPO-B8DNMN3XXfLTUp_dgLkCO_qAnYZviZA";
    private static final String FINAL_TOKEN_UPDATE = "Bearer "+GETALL;
    private static final String DELETE_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InJva2F5YTFAZ21haWwuY29tIiwiaWQiOiI2MzMzNTcxNWRiOWYzMDAwMjFmY2E4YTIiLCJleHBpcmF0aW9uRGF0ZSI6MTY2OTQ5MzU1MCwiaWF0IjoxNjY0MzA5NTUwfQ.C_44_7tiZ1-Vp1nDB_TAYCorO-iUruihMuQfelW5nuw";
    private static final String FINAL_DELETE_NOTE = "Bearer "+DELETE_TOKEN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        Button update = findViewById(R.id.update);
        Button delete = findViewById(R.id.delete);
        EditText title = findViewById(R.id.gettitle);
        EditText body = findViewById(R.id.getDescription);

        NoteViewModel noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        Note_Recycler note_recycler = new Note_Recycler((s) -> {
            noteViewModel.setId(s);
            Intent i = new Intent(this.getApplicationContext(),NoteDetails.class);
            startActivity(i);
        });
        ToDo_recycler todo_recycler = new ToDo_recycler(id -> {
            noteViewModel.getId().setValue(id);
            Intent i = new Intent(this.getApplicationContext(),NoteDetails.class);
            startActivity(i);
        });
        body.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                noteViewModel.getTitle().setValue(title.getText().toString());
                noteViewModel.getBody().setValue(body.getText().toString());
                Note note = new Note(title.getText().toString(),body.getText().toString(),noteViewModel.getId().getValue());
                noteViewModel.updateNote( note,FINAL_TOKEN_UPDATE).observe(NoteDetails.this, new Observer<Note>() {
                    @Override
                    public void onChanged(Note note) {

                    }
                });

            }
        });
        update.setOnClickListener((v)->{

            boolean check = false;
            String id = noteViewModel.getId().getValue();
            ArrayList<Note> note = Note_Recycler.getArrayList();
            ArrayList<Note> todo = ToDo_recycler.getArrayList();
            for(int i = 0;i<note.size();i++){
                if(id.equals(note.get(i).getId())){
                    check = true;
                    note.get(i).setTitle(title.getText().toString());
                    note.get(i).setBody(body.getText().toString());
                }
            }
            for(int i = 0;i<todo.size();i++){
                if(id.equals(todo.get(i).getId())){
                    todo.get(i).setTitle(title.getText().toString());
                    todo.get(i).setBody(body.getText().toString());
                }
            }
            if(check){
                note_recycler.setArrayList(note);
                noteViewModel.setNote_recyclerMutableLiveData(note_recycler);
            }else{
                todo_recycler.setArrayList(todo);
                noteViewModel.setTodo_recyclerMutableLiveData(todo_recycler);
            }

            finish();
        });
        delete.setOnClickListener((v)->{
            boolean check = false;
            String id = noteViewModel.getId().getValue();
            ArrayList<Note> note = Note_Recycler.getArrayList();
            ArrayList<Note> todo = ToDo_recycler.getArrayList();
            for(int i = 0;i<note.size();i++){
                if(id.equals(note.get(i).getId())){
                    check = true;
                    note.remove(i);
                }
            }
            for(int i = 0;i<todo.size();i++){
                if(id.equals(todo.get(i).getId())){
                    todo.remove(i);
                }
            }
            if(check){
                note_recycler.setArrayList(note);
                noteViewModel.setNote_recyclerMutableLiveData(note_recycler);
            }else{
                todo_recycler.setArrayList(todo);
                noteViewModel.setTodo_recyclerMutableLiveData(todo_recycler);
            }
            NoteRepo noteRepo = new NoteRepo();
            Note note2 = new Note("null","null",noteViewModel.getId().getValue());
            Call<Void> call = noteRepo.deleteNote(note2,FINAL_DELETE_NOTE);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(!response.isSuccessful()){

                        return;
                    }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });

            finish();
        });
    }
}