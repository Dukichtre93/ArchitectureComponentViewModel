package com.thanhviet.userlistarchvm.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.thanhviet.userlistarchvm.db.entity.UserEntity;
import java.util.List;

/**
 * Created by FRAMGIA\bui.dinh.viet on 19/07/18.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY name ASC")
    LiveData<List<UserEntity>> loadAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<UserEntity> users);

    @Query("select * from user_table where id = :userId")
    LiveData<UserEntity> loadUser(int userId);

    @Query("DELETE FROM user_table WHERE id = :userId")
    void deleteUser(int userId);
}
