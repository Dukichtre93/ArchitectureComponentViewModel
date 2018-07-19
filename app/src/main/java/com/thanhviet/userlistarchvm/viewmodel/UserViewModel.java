package com.thanhviet.userlistarchvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.thanhviet.userlistarchvm.BaseApp;
import com.thanhviet.userlistarchvm.db.entity.UserEntity;
import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<UserEntity>> mObservableUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mObservableUsers = new MediatorLiveData<>();
        mObservableUsers.setValue(null);

        LiveData<List<UserEntity>> products = BaseApp.getInstance().getRepository().getUserEntity();

        // observe the changes of the products from the database and forward them
        mObservableUsers.addSource(products, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(@Nullable List<UserEntity> userEntities) {
                mObservableUsers.setValue(userEntities);
            }
        });
    }

    public LiveData<List<UserEntity>> getUserList() {
        return mObservableUsers;
    }
}
