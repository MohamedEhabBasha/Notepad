package com.bdev.final_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bdev.final_project.NoteRetrofit.Note;
import com.bdev.final_project.NoteRetrofit.NoteRepo;

import com.bdev.final_project.NoteRetrofit.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoteFragment extends Fragment {

    private static final String GET_NOTES_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InJva2F5YTFAZ21haWwuY29tIiwiaWQiOiI2MzMzNTcxNWRiOWYzMDAwMjFmY2E4YTIiLCJleHBpcmF0aW9uRGF0ZSI6MTY2OTQ5MzAyOSwiaWF0IjoxNjY0MzA5MDI5fQ.-oFPzPRldPO-B8DNMN3XXfLTUp_dgLkCO_qAnYZviZA";
    private static final String FINAL_TOKEN_GET = "Bearer "+GET_NOTES_TOKEN;
    private static final String GETALL = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InJva2F5YTFAZ21haWwuY29tIiwiaWQiOiI2MzMzNTcxNWRiOWYzMDAwMjFmY2E4YTIiLCJleHBpcmF0aW9uRGF0ZSI6MTY2OTQ5MzU1MCwiaWF0IjoxNjY0MzA5NTUwfQ.C_44_7tiZ1-Vp1nDB_TAYCorO-iUruihMuQfelW5nuw";
    private static final String FINAL_TOKEN_DELETE = "Bearer "+GETALL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_note, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.note_recycler);
        FloatingActionButton add = v.findViewById(R.id.floatingaddButton);
        FloatingActionButton delete = v.findViewById(R.id.floatingDelete);
        NoteViewModel noteViewModel = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
        Note_Recycler note_recyclerr = new Note_Recycler((s) -> {
            noteViewModel.setId(s);
            Intent i = new Intent(getContext(),NoteDetails.class);
            startActivity(i);
        });

        noteViewModel.getNote_recyclerMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Note_Recycler>() {
            @Override
            public void onChanged(Note_Recycler note_recycler) {
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                recyclerView.setAdapter(note_recycler);
            }
        });
        add.setOnClickListener((s)->{
           Intent vertical = new Intent(getContext(),AddNoteActivity.class);
            startActivity(vertical);

        });
        delete.setOnClickListener((deleteAll)->{
            ArrayList<Note> empty = new ArrayList<>();
            Note_Recycler emptyn = new Note_Recycler();
            emptyn.setArrayList(empty);
            noteViewModel.setNote_recyclerMutableLiveData(emptyn);
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
            recyclerView.setAdapter(note_recyclerr);

        return v;
    }


}