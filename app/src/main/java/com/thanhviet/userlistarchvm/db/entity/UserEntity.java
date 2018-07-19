package com.thanhviet.userlistarchvm.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.thanhviet.userlistarchvm.model.User;

/**
 * Created by FRAMGIA\bui.dinh.viet on 19/07/18.
 */

@Entity(tableName = "user_table")
public class UserEntity implements User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    @NonNull
    private String name;
    @ColumnInfo(name = "job")
    @NonNull
    private String job;
    @ColumnInfo(name = "idImage")
    @NonNull
    private int idImage;

    public UserEntity(String name, String job, int idImage) {
        this.name = name;
        this.job = job;
        this.idImage = idImage;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getJob() {
        return job;
    }

    @Override
    public int getIdImage() {
        return idImage;
    }

    public void setId(int id) {
        this.id = id;
    }
}
