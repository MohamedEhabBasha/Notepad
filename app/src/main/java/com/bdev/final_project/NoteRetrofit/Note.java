package com.bdev.final_project.NoteRetrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Note implements Serializable {
    @SerializedName("isDone")
    @Expose
    private Boolean isDone;
    @SerializedName("_id")
    private String id;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("dateModified")
    @Expose
    private String dateModified;
    @SerializedName("alarmDate")
    @Expose
    private Object alarmDate;
    @SerializedName("tag")
    @Expose
    private Object tag;
    private final static long serialVersionUID = -1599719976855121623L;

    public Note(String title,String body){
        this.title = title;
        this.body = body;
    }
    public Note(String title,String body,String id){
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public Note(Boolean isDone, String id, String author, String title, String body, String dateCreated, String dateModified, Object alarmDate, Object tag) {
        this.isDone = isDone;
        this.id = id;
        this.author = author;
        this.title = title;
        this.body = body;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.alarmDate = alarmDate;
        this.tag = tag;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public Object getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(Object alarmDate) {
        this.alarmDate = alarmDate;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
