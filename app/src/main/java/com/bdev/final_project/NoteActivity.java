package com.bdev.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.bdev.final_project.NoteRetrofit.NoteViewModel;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        NoteViewModel noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        TextView textView = findViewById(R.id.welcome);
        textView.setText(noteViewModel.getName().getValue());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.mainFragment, main_fragment.class, null)
                    .commit();
        }
    }
}