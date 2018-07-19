package com.thanhviet.userlistarchvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import com.thanhviet.userlistarchvm.adapter.UserAdapter;
import com.thanhviet.userlistarchvm.databinding.ActivityMainBinding;
import com.thanhviet.userlistarchvm.db.entity.UserEntity;
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
        subscribeUi(userViewModel);
    }

    private void subscribeUi(UserViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getUserList().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(@Nullable List<UserEntity> userEntities) {
                if (userEntities != null) {
//                    mBinding.setIsLoading(false);
                    mAdapter.setUserList(userEntities);
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }

}
