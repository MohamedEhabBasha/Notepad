package com.bdev.final_project.first_page;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bdev.final_project.NoteRetrofit.NoteViewModel;
import com.bdev.final_project.R;
import com.bdev.final_project.retrofit.ReceiveUser;


public class Register_Fragment extends Fragment {

    private static SharedPreferences sharedPreference;
    public Register_Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register_, container, false);
        Button sign = v.findViewById(R.id.register);
        EditText name = v.findViewById(R.id.nameID);
        EditText email = v.findViewById(R.id.emailID);
        EditText password = v.findViewById(R.id.passID);

        NoteViewModel userViewModel = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
        sharedPreference = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        sign.setOnClickListener(s ->{
            SharedPreferences.Editor editor = sharedPreference.edit();
            ReceiveUser sendUser = new ReceiveUser(name.getText().toString(),email.getText().toString(),password.getText().toString());
            userViewModel.getSendUser(sendUser).observe(getViewLifecycleOwner(), new Observer<ReceiveUser>() {
                @Override
                public void onChanged(ReceiveUser receiveUser) {
                    userViewModel.setName(name.getText().toString());
                    editor.putString(email.getText().toString(),receiveUser.get_id());
                    editor.apply();

                }
            });
        });
        return v;
    }

}