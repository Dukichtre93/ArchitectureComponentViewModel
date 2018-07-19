package com.thanhviet.userlistarchvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import com.thanhviet.userlistarchvm.db.UserRoomDB;
import com.thanhviet.userlistarchvm.db.entity.UserEntity;
import java.util.List;

/**
 * Created by FRAMGIA\bui.dinh.viet on 19/07/18.
 */

public class UserRepository {

    private static UserRepository sInstance;
    private final UserRoomDB mDatabase;
    private MediatorLiveData<List<UserEntity>> mObservableUsers;

    public static UserRepository getInstance(final UserRoomDB database) {
        if (sInstance == null) {
            synchronized (UserRepository.class) {
                if (sInstance == null) {
                    sInstance = new UserRepository(database);
                }
            }
        }
        return sInstance;
    }

    private UserRepository(final UserRoomDB database) {
        mDatabase = database;
        mObservableUsers = new MediatorLiveData<>();

        mObservableUsers.addSource(mDatabase.mUserDao().loadAllUsers(),
                new Observer<List<UserEntity>>() {

                    @Override
                    public void onChanged(@Nullable List<UserEntity> userEntities) {
                        if (mDatabase.getDatabaseCreated().getValue() != null) {
                            mObservableUsers.postValue(userEntities);
                        }
                    }
                });
    }

    /**
     * Get all user entity data from mObservableUsers
     */
    public LiveData<List<UserEntity>> getUserEntity() {
        return mObservableUsers;
    }

    /**
     * Get user detail from id
     */
    public LiveData<UserEntity> getUserDetail(int userId) {
        return mDatabase.mUserDao().loadUser(userId);
    }
}
