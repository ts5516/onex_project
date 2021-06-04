package com.onex.onexproject.Frag_Profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.onex.onexproject.Adapter.ArtAdapter;
import com.onex.onexproject.Model.Post;
import com.onex.onexproject.Profile;
import com.onex.onexproject.R;

public class FragProfile_intro extends Fragment { //소개
    private View view;
    private String profileID;

    FirebaseFirestore db;
    RecyclerView imageRecycler;
    public FragProfile_intro() {}


    public void onAttach(Context context) {

        super.onAttach(context);
        if(getActivity().getClass().getSimpleName().trim().equals("Profile"))
            profileID = ((Profile)getActivity()).getID();

        else
            profileID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_frag_intro, container, false);

        return view;
    }
}
