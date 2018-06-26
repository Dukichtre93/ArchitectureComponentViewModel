package com.thanhviet.userlistarchvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import com.thanhviet.userlistarchvm.R;
import com.thanhviet.userlistarchvm.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private List<User> userList;

    public UserViewModel(@NonNull Application application) {
        super(application);
        if (userList == null) {
            setupUserList();
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    private void setupUserList() {
        userList = new ArrayList<>();
        userList.add(new User("Khahoo Ajish", "Front-End Developer", R.drawable.khahoo));
        userList.add(new User("John Covey", "Back-End Developer", R.drawable.john));
        userList.add(new User("Loges Vamber", "iOS Developer", R.drawable.loges));
        userList.add(new User("Jung Kim Bap", "Android Developer", R.drawable.jung));
        userList.add(new User("Seeng Luse", "AI Developer", R.drawable.seeng));
        Log.i("UserViewModel", "setupUserList: ---------> size: "+userList.size());
    }
}
