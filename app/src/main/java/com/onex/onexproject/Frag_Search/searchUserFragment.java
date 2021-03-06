package com.onex.onexproject.Frag_Search;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.onex.onexproject.Adapter.UserAdapter;
import com.onex.onexproject.Frag_Menu.Frag4_Profile;
import com.onex.onexproject.Model.User;
import com.onex.onexproject.Profile;
import com.onex.onexproject.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;


public class searchUserFragment extends Fragment implements  UserAdapter.OnItemClickListener {
    private RecyclerView userRecycler;
    private FirebaseFirestore firestore;
    private EditText searchBar;
    private String TAG = "searchUserFrag";
    private ViewGroup view;
    private UserAdapter adapter;

    @Override
    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {

    }

    public searchUserFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (ViewGroup)inflater.inflate(R.layout.search_frag_user, container, false);


        firestore = FirebaseFirestore.getInstance();
        userRecycler = view.findViewById(R.id.SeUserRecycler);
        searchBar = getActivity().findViewById(R.id.searchBar);


        setUpReyclerView();

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



    private void setUpReyclerView(){
        Query query = firestore.collection("users").orderBy("name");
        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();
        adapter = new UserAdapter(options);

        userRecycler.setHasFixedSize(true);
        userRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        userRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String id = documentSnapshot.getId();
                Intent intent = new Intent(getActivity().getApplicationContext(), Profile.class);
                intent.putExtra("profileId", id);
                startActivity(intent);
            }
        });
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