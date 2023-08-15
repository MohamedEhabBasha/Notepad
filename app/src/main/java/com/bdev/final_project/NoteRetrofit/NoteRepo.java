package com.bdev.final_project.NoteRetrofit;



import com.bdev.final_project.retrofit.ReceiveUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoteRepo {
    private NoteService noteService;

    public NoteRepo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://noteify-service.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        noteService = retrofit.create(NoteService.class);
    }
    public Call<ReceiveUser> getSendUser(ReceiveUser sendUser){
        return noteService.sendUser(sendUser);
    }
    public Call<ReceiveUser> getReceiveUser(ReceiveUser sendUser){
        return noteService.loginUser(sendUser);
    }
    public Call<Note> addNote(Note note,String token){
        return noteService.postNote(note,token);
    }
    public Call<ArrayList<Note>> getAllNotes(String token){
        return noteService.getAllNotes(token);
    }
    public Call<Note> update(Note note,String token){
        return noteService.updateNote(note,token);
    }
    public Call<Void> deleteAll(String token){
        return noteService.deleteAllNotes(token);
    }
    public Call<Void> deleteNote(Note note,String token){
        return noteService.deleteNote(note.getId(),token);
    }
}
