package com.thanhviet.userlistarchvm;

import android.app.Application;
import com.thanhviet.userlistarchvm.db.UserRoomDB;

public class BaseApp extends Application {
    private static BaseApp self;
    private AppExecutors mAppExecutors;

    public static BaseApp getInstance() {
        return self;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
        mAppExecutors = new AppExecutors();
    }

    public UserRoomDB getDatabase() {
        return UserRoomDB.getInstance(this, mAppExecutors);
    }

    public UserRepository getRepository() {
        return UserRepository.getInstance(getDatabase());
    }
}
