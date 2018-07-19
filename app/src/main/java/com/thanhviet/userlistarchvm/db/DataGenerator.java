package com.thanhviet.userlistarchvm.db;

import com.thanhviet.userlistarchvm.R;
import com.thanhviet.userlistarchvm.db.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\bui.dinh.viet on 19/07/18.
 */

public class DataGenerator {

    public static List<UserEntity> generateUsers() {
        List<UserEntity> userList = new ArrayList<>();
        userList.add(new UserEntity("Khahoo Ajish", "Front-End Developer", R.drawable.khahoo));
        userList.add(new UserEntity("John Covey", "Back-End Developer", R.drawable.john));
        userList.add(new UserEntity("Loges Vamber", "iOS Developer", R.drawable.loges));
        userList.add(new UserEntity("Jung Kim Bap", "Android Developer", R.drawable.jung));
        userList.add(new UserEntity("Seeng Luse", "AI Developer", R.drawable.seeng));
        return userList;
    }
}
