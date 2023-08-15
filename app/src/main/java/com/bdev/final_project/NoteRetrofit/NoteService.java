package com.bdev.final_project.NoteRetrofit;

import com.bdev.final_project.retrofit.ReceiveUser;

import java.util.ArrayList;


import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;

import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NoteService {
    @POST("/user/register")
    Call<ReceiveUser> sendUser(@Body ReceiveUser User);
    @POST("/user/login")
    Call<ReceiveUser> loginUser(@Body ReceiveUser User);
    @POST("/note/add")
    Call<Note> postNote(@Body Note note, @Header("Authorization") String token);

    @GET("/note/getAll")
    Call<ArrayList<Note>> getAllNotes(@Header("Authorization") String token);

    @PUT("/note/update?6347107fef2cda0021605ad8=sdf")
    Call<Note> updateNote( @Body Note note, @Header("Authorization") String token);

    @DELETE("/note/deleteAll")
    Call<Void> deleteAllNotes(@Header("Authorization") String token);
    //@DELETE("/note/delete?id=63335740db9f300021fca8a3/{_id}")
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/note/delete?id=634c793bf404790021431a63/{_id}",hasBody = true)
    Call<Void> deleteNote(@Field("_id") String id, @Header("Authorization") String token);
}
