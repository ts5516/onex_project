package com.onex.onexproject.Frag_Profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.onex.onexproject.Adapter.ArtAdapter;
import com.onex.onexproject.Adapter.UserAdapter;
import com.onex.onexproject.ArtinfoActivity;
import com.onex.onexproject.Frag_Menu.Frag4_Profile;
import com.onex.onexproject.MenuActivity;
import com.onex.onexproject.Model.Post;
import com.onex.onexproject.Profile;
import com.onex.onexproject.R;

import org.jetbrains.annotations.NotNull;

public class FragProfile_art extends Fragment {
    private View view;
    private ArtAdapter adapter;
    private FirebaseFirestore firestore;
    private DocumentReference doref;
    private RecyclerView imageRecycler;
    private Query query;

    private String profileID;

    public FragProfile_art(){}

    public void onAttach(Context context) {

        super.onAttach(context);
        if(getActivity().getClass().getSimpleName().trim().equals("Profile"))
            profileID = ((Profile)getActivity()).getID();

        else
            profileID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (ViewGroup)inflater.inflate(R.layout.profile_frag_artpiece, container, false);

        firestore = FirebaseFirestore.getInstance();
        imageRecycler = view.findViewById(R.id.art_recycler);

        Query query = firestore.collection("users").document(profileID).collection("artPiece");
        FirestoreRecyclerOptions<Post> options = new FirestoreRecyclerOptions.Builder<Post>()
                .setQuery(query, Post.class)
                .build();

        adapter = new ArtAdapter(options);

        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        imageRecycler.setHasFixedSize(true);
        imageRecycler.setLayoutManager(glm);
        imageRecycler.addItemDecoration(new GridSpacingItemDecoration(3, 15, false));
        imageRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new ArtAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position, ImageView imageView) {
                String id = documentSnapshot.getId();
                Intent intent = new Intent(getActivity(), ArtinfoActivity.class);
                intent.putExtra("artID", id);
                intent.putExtra("profileID", profileID);
                startActivity(intent);
            }
        });

        return view;
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

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
