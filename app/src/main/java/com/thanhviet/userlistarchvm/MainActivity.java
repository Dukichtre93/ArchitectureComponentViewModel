package com.thanhviet.userlistarchvm;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import com.thanhviet.userlistarchvm.adapter.UserAdapter;
import com.thanhviet.userlistarchvm.databinding.ActivityMainBinding;
import com.thanhviet.userlistarchvm.model.User;
import com.thanhviet.userlistarchvm.viewmodel.UserViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserAdapter mAdapter;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mAdapter = new UserAdapter();
        mBinding.mainList.setLayoutManager(new LinearLayoutManager(this));
        mBinding.mainList.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mBinding.mainList.setAdapter(mAdapter);
        // Comment it when use Architecture Components ViewModel
        //        mAdapter.setUserList(loadUsers());
        final UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mAdapter.setUserList(userViewModel.getUserList());
    }

    /**
     * You only use this method when
     * You want to check rotation screen
     */
    private List<User> loadUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Khahoo Ajish", "Front-End Developer", R.drawable.khahoo));
        userList.add(new User("John Covey", "Back-End Developer", R.drawable.john));
        userList.add(new User("Loges Vamber", "iOS Developer", R.drawable.loges));
        userList.add(new User("Jung Kim Bap", "Android Developer", R.drawable.jung));
        userList.add(new User("Seeng Luse", "AI Developer", R.drawable.seeng));
        //Log for easily see
        Log.i("MainActivity", "loadUsers: ---------> size:" + userList.size());
        return userList;
    }
}
