package com.bdev.final_project.first_page;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bdev.final_project.NoteActivity;
import com.bdev.final_project.NoteRetrofit.NoteViewModel;
import com.bdev.final_project.R;
import com.bdev.final_project.retrofit.ReceiveUser;


public class Login_Fragment extends Fragment {

    public Login_Fragment() {
        // Required empty public constructor
    }
    String emm="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login_, container, false);
        Button login = v.findViewById(R.id.LOGIN);
        EditText email = v.findViewById(R.id.emailLogin);
        EditText password = v.findViewById(R.id.passLogin);

        NoteViewModel noteViewModel = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
        SharedPreferences sharedPreference = this.getActivity().getPreferences(Context.MODE_PRIVATE);

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                SharedPreferences.Editor editor = sharedPreference.edit();
                ReceiveUser sendUser = new ReceiveUser(email.getText().toString(),password.getText().toString());
                noteViewModel.getLoginUser(sendUser).observe(getViewLifecycleOwner(), receiveUser -> {

                    editor.putString("pass",receiveUser.get_id());
                    editor.apply();
                });
            }
        });
        noteViewModel.getTitle().observe(getViewLifecycleOwner(), s -> {

        });
        login.setOnClickListener(s ->{

            emm = sharedPreference.getString(email.getText().toString(),"FETAL ERROR");

             if (emm.equals(sharedPreference.getString("pass", ""))) {
                Intent i = new Intent(getActivity().getApplicationContext(), NoteActivity.class);
                startActivity(i);

            } else {
                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}