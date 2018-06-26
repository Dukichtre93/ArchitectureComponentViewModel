package com.thanhviet.userlistarchvm;

import android.app.Application;

public class BaseApp extends Application {
    private static BaseApp self;

    public static BaseApp getInstance() {
        if (self == null) {
            self = new BaseApp();
        }
        return self;
    }
}
