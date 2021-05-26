package com.onex.onexproject.Frag_Search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.onex.onexproject.Model.User;
import com.onex.onexproject.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;


public class searchUserFragment extends Fragment {

    private RecyclerView userRecycler;
    private FirebaseFirestore firestore;
    private FirestoreRecyclerAdapter adapter;
    private EditText searchBar;
    private String TAG = "searchUserFrag";
    private ViewGroup view;

    public searchUserFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (ViewGroup)inflater.inflate(R.layout.search_frag_user, container, false);

        firestore = FirebaseFirestore.getInstance();
        userRecycler = view.findViewById(R.id.SeUserRecycler);
        searchBar = getActivity().findViewById(R.id.searchBar);

        Query query = firestore.collection("users").orderBy("name");
        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<User, UserViewHolder>(options) {
            @NonNull
            @NotNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_user_item, parent, false);
                return new UserViewHolder(userView);
            }

            @Override
            protected void onBindViewHolder(@NonNull @NotNull UserViewHolder holder, int position, @NonNull @NotNull User model) {
                holder.userName.setText(model.getName());
                holder.userDesc.setText(model.getDescription());
                Picasso.get().load(model.getImageUri()).into(holder.userImage);
            }
        };

        userRecycler.setHasFixedSize(true);
        userRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        userRecycler.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "SearchUser has changed to: " + s.toString());

                Query query = firestore.collection("users").orderBy("name")
                        .startAt(s.toString()).endAt(s.toString() + "\uf8ff");
                FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                        .setQuery(query, User.class)
                        .build();

                adapter.updateOptions(options);
            }
        });
        return view;
    }


    private class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView userName, userDesc;
        private CircleImageView userImage;
        public UserViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.SeUserName);
            userDesc = itemView.findViewById(R.id.SeUserDesc);
            userImage = itemView.findViewById(R.id.PFUserImage);
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