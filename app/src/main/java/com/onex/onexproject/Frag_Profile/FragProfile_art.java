package com.onex.onexproject.Frag_Profile;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.onex.onexproject.Frag_Search.searchUserFragment;
import com.onex.onexproject.Model.Post;
import com.onex.onexproject.Model.User;
import com.onex.onexproject.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragProfile_art extends Fragment {
    private View view;
    private FirestoreRecyclerAdapter adapter;
    private FirebaseFirestore firestore;
    private DocumentReference doref;
    private RecyclerView imageRecycler;
    private Query query;
    public FragProfile_art(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (ViewGroup)inflater.inflate(R.layout.profile_frag_artpiece, container, false);

        firestore = FirebaseFirestore.getInstance();
        imageRecycler = view.findViewById(R.id.art_recycler);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = firestore.collection("users").document(uid).collection("artPiece");
        FirestoreRecyclerOptions<Post> options = new FirestoreRecyclerOptions.Builder<Post>()
                .setQuery(query, Post.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Post, FragProfile_art.ImageViewHolder>(options) {
            @NonNull
            @NotNull
            @Override
            public FragProfile_art.ImageViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_art_item, parent, false);
                return new FragProfile_art.ImageViewHolder(userView);
            }

            @Override
            protected void onBindViewHolder(@NonNull @NotNull FragProfile_art.ImageViewHolder holder, int position, @NonNull @NotNull Post model) {
                Picasso.get().load(model.getUri()).into(holder.image);
            }
        };

        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        imageRecycler.setHasFixedSize(true);
        imageRecycler.setLayoutManager(glm);
        imageRecycler.setAdapter(adapter);


        return view;
    }

    private class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        public ImageViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.art_image);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
