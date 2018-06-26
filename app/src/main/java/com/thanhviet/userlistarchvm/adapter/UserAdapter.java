package com.thanhviet.userlistarchvm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.thanhviet.userlistarchvm.databinding.ItemUserBinding;
import com.thanhviet.userlistarchvm.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> mUserList;

    public void setUserList(List<User> userList) {
        if (mUserList == null) {
            mUserList = new ArrayList<>();
        }
        mUserList.clear();
        mUserList.addAll(userList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (getItemCount() > 0) holder.bindData(mUserList.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserList != null ? mUserList.size() : 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private ItemUserBinding mBinding;

        public UserViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(User user) {
            if (mBinding.getUser() == null && user != null) {
                mBinding.setUser(user);
            }
        }
    }
}
