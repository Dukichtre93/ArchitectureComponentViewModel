package com.thanhviet.userlistarchvm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.thanhviet.userlistarchvm.databinding.ItemUserBinding;
import com.thanhviet.userlistarchvm.model.User;
import java.util.List;
import java.util.Objects;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<? extends User> mUserList;

    public void setUserList(final List<? extends User> userList) {
        if (mUserList == null) {
            mUserList = userList;
            notifyItemRangeInserted(0, userList.size());
            return;
        }
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return mUserList.size();
            }

            @Override
            public int getNewListSize() {
                return userList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return mUserList.get(oldItemPosition).getId() == userList.get(newItemPosition)
                        .getId();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                User newUser = userList.get(newItemPosition);
                User oldUser = mUserList.get(oldItemPosition);
                return newUser.getId() == oldUser.getId()
                        && Objects.equals(newUser.getJob(), oldUser.getJob())
                        && Objects.equals(newUser.getName(), oldUser.getName())
                        && newUser.getIdImage() == oldUser.getIdImage();
            }
        });
        mUserList = userList;
        result.dispatchUpdatesTo(this);
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
                mBinding.setUser(user);
            if (mBinding.getUser() == null && user != null) {
            }
        }
    }
}
