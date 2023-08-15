package com.bdev.final_project.NoteRetrofit;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bdev.final_project.Note_Recycler;
import com.bdev.final_project.ToDo_recycler;
import com.bdev.final_project.retrofit.ReceiveUser;


import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoteViewModel extends ViewModel {
    private static final MutableLiveData<Note_Recycler> note_recyclerMutableLiveData = new MutableLiveData<>();
    private static final MutableLiveData<ToDo_recycler> todo_recyclerMutableLiveData = new MutableLiveData<>();
    private static final MutableLiveData<ArrayList<Note>> list_notes = new MutableLiveData<>();
    private static final MutableLiveData<Note> addNote = new MutableLiveData<>();
    private static final MutableLiveData<Note> update = new MutableLiveData<>();
    private static final MutableLiveData<String> title = new MutableLiveData<>();
    private static final MutableLiveData<String> body = new MutableLiveData<>();
    private static final MutableLiveData<String> name = new MutableLiveData<>();
    private static final MutableLiveData<String> id = new MutableLiveData<>("Null");
    private final MutableLiveData<ReceiveUser> send_user = new MutableLiveData<>();
    private final MutableLiveData<ReceiveUser> login_user = new MutableLiveData<>();
    ReceiveUser receiveUser = new ReceiveUser("null","null","null","null","null");
    ReceiveUser receiveUser2 = new ReceiveUser("null","null2","null","null","null");
    private NoteRepo noteRepo = new NoteRepo();
    ArrayList<Note> mvm = new ArrayList<>();
    Note mmm = new Note("null","null","null");
    Note mmmt = new Note("null","null","null2");
    public MutableLiveData<ReceiveUser> getSendUser(ReceiveUser sendUser){
        Call<ReceiveUser> receiveUserCall = noteRepo.getSendUser(sendUser);
        receiveUserCall.enqueue(new Callback<ReceiveUser>() {
            @Override
            public void onResponse(Call<ReceiveUser> call, Response<ReceiveUser> response) {
                if(!response.isSuccessful()) send_user.setValue(receiveUser);
                else send_user.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ReceiveUser> call, Throwable t) {
                send_user.setValue(receiveUser2);
            }
        });
        return send_user;
    }
    public MutableLiveData<ReceiveUser> getLoginUser(ReceiveUser sendUser){

        Call<ReceiveUser> receiveUserCall = noteRepo.getReceiveUser(sendUser);
        receiveUserCall.enqueue(new Callback<ReceiveUser>() {
            @Override
            public void onResponse(Call<ReceiveUser> call, Response<ReceiveUser> response) {
                if(!response.isSuccessful()) login_user.setValue(receiveUser);
                else login_user.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ReceiveUser> call, Throwable t) {
                //t.getMessage();
                login_user.setValue(receiveUser2);
            }
        });
        return login_user;
    }
    public  MutableLiveData<ArrayList<Note>> getList(String token){
        //NoteRepo noteRepo = new NoteRepo();
        Call<ArrayList<Note>> notes = noteRepo.getAllNotes(token);
        notes.enqueue(new Callback<ArrayList<Note>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Note>> call, @NonNull Response<ArrayList<Note>> response) {
                if (!response.isSuccessful()) {
                    mvm.add(new Note("null","null"));
                    list_notes.setValue(mvm);
                    return;
                }
                list_notes.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Note>> call, @NonNull Throwable t) {
                mvm.add(new Note("null","null"));
                list_notes.setValue(mvm);
            }
        });
        return list_notes;
    }
    public  MutableLiveData<Note> postNotes(Note note,String token){

        Call<Note> notes = noteRepo.addNote(note,token);
        notes.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                if (!response.isSuccessful()) {
                    addNote.setValue(mmm);
                    return;
                }
                addNote.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {
                addNote.setValue(mmmt);
            }
        });
        return addNote;
    }
    public MutableLiveData<Note> updateNote(Note note,String token){
        //NoteRepo noteRepo = new NoteRepo();
        Call<Note> call = noteRepo.update(note,token);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if(!response.isSuccessful()){
                    update.setValue(mmm);
                    return;
                }
                update.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {

            }
        });
        return update;
    }
    public static MutableLiveData<Note_Recycler> getNote_recyclerMutableLiveData(){
        return note_recyclerMutableLiveData;
    }
    public static void setNote_recyclerMutableLiveData(Note_Recycler note_recycler){
        note_recyclerMutableLiveData.setValue(note_recycler);
    }
    public static MutableLiveData<ToDo_recycler> getTodo_recyclerMutableLiveData(){
        return todo_recyclerMutableLiveData;
    }
    public static void setTodo_recyclerMutableLiveData(ToDo_recycler todo_recycler){
        todo_recyclerMutableLiveData.setValue(todo_recycler);
    }
    public static MutableLiveData<String> getTitle(){
        return title;
    }
    public static MutableLiveData<String> getBody(){
        return body;
    }
    public static MutableLiveData<String> getId(){
        return id;
    }
    public static void setId(String s){
        id.setValue(s);
    }
    public static MutableLiveData<String> getName(){
        return name;
    }
    public static void setName(String s){
        name.setValue(s);
    }
}
