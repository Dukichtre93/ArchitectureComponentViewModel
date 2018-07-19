package com.thanhviet.userlistarchvm.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import com.thanhviet.userlistarchvm.AppExecutors;
import com.thanhviet.userlistarchvm.db.dao.UserDao;
import com.thanhviet.userlistarchvm.db.entity.UserEntity;
import java.util.List;

/**
 * Created by FRAMGIA\bui.dinh.viet on 19/07/18.
 */

@Database(entities = { UserEntity.class }, version = 1)
public abstract class UserRoomDB extends RoomDatabase {

    public abstract UserDao mUserDao();

    public static final String DATABASE_NAME = "user-list-archvm-db";
    private static UserRoomDB sInstance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static UserRoomDB getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (UserRoomDB.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static UserRoomDB buildDatabase(final Context appContext, final AppExecutors executors) {
        return Room.databaseBuilder(appContext, UserRoomDB.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                UserRoomDB database = UserRoomDB.getInstance(appContext, executors);
                                List<UserEntity> userEntities = DataGenerator.generateUsers();
                                insertData(database, userEntities);
                                database.setDatabaseCreated();
                            }
                        });
                    }
                })
                .build();
    }

    private static void insertData(final UserRoomDB database, final List<UserEntity> userEntities) {
        database.runInTransaction(new Runnable() {
            @Override
            public void run() {
                database.mUserDao().insertAll(userEntities);
            }
        });
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
}
