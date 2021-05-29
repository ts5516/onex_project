package com.onex.onexproject.Frag_Search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.onex.onexproject.Adapter.TagAdapter;
import com.onex.onexproject.Adapter.UserAdapter;
import com.onex.onexproject.Model.Tag;
import com.onex.onexproject.Model.User;
import com.onex.onexproject.Profile;
import com.onex.onexproject.R;


public class searchTagFragment extends Fragment {
    private RecyclerView tagRecycler;
    private FirebaseFirestore firestore;

    private EditText searchBar;
    private ViewGroup view;

    private TagAdapter adapter;

    public searchTagFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (ViewGroup)inflater.inflate(R.layout.search_frag_tag, container, false);


        firestore = FirebaseFirestore.getInstance();
        tagRecycler = view.findViewById(R.id.SETagRecylcer);
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
                Query query = firestore.collection("Tag").orderBy("name")
                        .startAt(s.toString()).endAt(s.toString() + "\uf8ff");
                FirestoreRecyclerOptions<Tag> options = new FirestoreRecyclerOptions.Builder<Tag>()
                        .setQuery(query, Tag.class)
                        .build();

                adapter.updateOptions(options);
            }
        });
        return view;
    }

    private void setUpReyclerView(){
        Query query = firestore.collection("Tag").orderBy("name");
        FirestoreRecyclerOptions<Tag> options = new FirestoreRecyclerOptions.Builder<Tag>()
                .setQuery(query, Tag.class)
                .build();
        adapter = new TagAdapter(options);

        tagRecycler.setHasFixedSize(true);
        tagRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        tagRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new TagAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String id = documentSnapshot.getId();
                Intent intent = new Intent(getActivity().getApplicationContext(), Profile.class);
                intent.putExtra("tagName", id.toString());
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