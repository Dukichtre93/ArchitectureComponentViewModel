package com.thanhviet.userlistarchvm.model;

import android.graphics.drawable.Drawable;

public class User {
    private int id;
    private String name;
    private String job;
    private Drawable drawImage;
    private int idImage;

    public User(String name, String job, int idImage) {
        this.name = name;
        this.job = job;
        this.idImage = idImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }
}
