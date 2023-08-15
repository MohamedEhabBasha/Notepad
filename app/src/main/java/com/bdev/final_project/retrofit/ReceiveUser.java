package com.bdev.final_project.retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReceiveUser implements Serializable {
    @SerializedName("token")
    private String token;
    @SerializedName("_id")
    private String _id;
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("password")
    private String password;

    public ReceiveUser(String email,String password) {
        this.password = password;
        this.email = email;
    }

    public ReceiveUser(String token, String _id, String email, String name, String password) {
        this.token = token;
        this._id = _id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public ReceiveUser(){

    }
    public ReceiveUser(String name,String email,String password){
        this.name = name;
        this.password = password;
        this.email = email;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
