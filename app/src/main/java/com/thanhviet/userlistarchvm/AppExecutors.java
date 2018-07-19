package com.thanhviet.userlistarchvm;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by FRAMGIA\bui.dinh.viet on 19/07/18.
 */

public class AppExecutors {

    private final Executor mDiskIO;

    private AppExecutors(Executor diskIO) {
        this.mDiskIO = diskIO;
    }

    public AppExecutors() {
        this(Executors.newSingleThreadExecutor());
    }

    public Executor diskIO() {
        return mDiskIO;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
