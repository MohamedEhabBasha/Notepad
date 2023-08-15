package com.bdev.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdev.final_project.NoteRetrofit.Note;
import com.bdev.final_project.NoteRetrofit.NoteRepo;
import com.bdev.final_project.NoteRetrofit.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TodoFragment extends Fragment {

    static String title="",des="";
    private static final String GETALL = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InJva2F5YTFAZ21haWwuY29tIiwiaWQiOiI2MzMzNTcxNWRiOWYzMDAwMjFmY2E4YTIiLCJleHBpcmF0aW9uRGF0ZSI6MTY2OTQ5MzU1MCwiaWF0IjoxNjY0MzA5NTUwfQ.C_44_7tiZ1-Vp1nDB_TAYCorO-iUruihMuQfelW5nuw";
    private static final String FINAL_TOKEN_DELETE = "Bearer "+GETALL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_todo, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.todo_recycler);
        FloatingActionButton add = v.findViewById(R.id.floatingActionButton);
        FloatingActionButton delete = v.findViewById(R.id.floatingDeleteButton);
        NoteViewModel noteViewModel = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
        ToDo_recycler todo_recycler = new ToDo_recycler(id -> {
            noteViewModel.getId().setValue(id);
            Intent i = new Intent(getContext(),NoteDetails.class);
            startActivity(i);
        });
        noteViewModel.getTodo_recyclerMutableLiveData().observe(getViewLifecycleOwner(), toDo_recycler -> {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
            recyclerView.setAdapter(toDo_recycler);
        });

        add.setOnClickListener((dead)->{
            Intent vertical = new Intent(getContext(),AddNoteActivity.class);
            startActivity(vertical);
        });
        delete.setOnClickListener((deleteAll)->{
            ArrayList<Note> empty = new ArrayList<>();
            ToDo_recycler em = new ToDo_recycler();
            em.setArrayList(empty);
            todo_recycler.setArrayList(empty);
            noteViewModel.setTodo_recyclerMutableLiveData(em);
            NoteRepo noteRepo = new NoteRepo();
            Call<Void> call = noteRepo.deleteAll(FINAL_TOKEN_DELETE);
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
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(todo_recycler);
        return v;
    }
    public static void update(String titlee,String dess){
        title = titlee;
        des = dess;
    }
}