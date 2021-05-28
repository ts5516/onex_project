package com.onex.onexproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.onex.onexproject.Frag_Search.searchUserFragment;
import com.onex.onexproject.Model.User;
import com.onex.onexproject.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends FirestoreRecyclerAdapter<User, UserAdapter.UserViewHolder> {

    public UserAdapter(@NonNull @NotNull FirestoreRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull UserViewHolder holder, int position, @NonNull @NotNull User model) {
        holder.userName.setText(model.getName());
        holder.userDesc.setText(model.getDescription());
        Glide.with(holder.itemView).load(model.getImageUri()).into(holder.userImage);
    }

    @NonNull
    @NotNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_user_item, parent, false);
        return new UserViewHolder(userView);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView userName, userDesc;
        private CircleImageView userImage;
        public UserViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.SeUserName);
            userDesc = itemView.findViewById(R.id.SeUserDesc);
            userImage = itemView.findViewById(R.id.PFUserImage);


        }
    }
}
