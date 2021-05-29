package com.onex.onexproject;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.onex.onexproject.Adapter.ProfileAdapter;
import com.onex.onexproject.databinding.ProfileActivityBinding;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    private ProfileActivityBinding activityProfileBinding;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    DocumentReference doref, followerRef, followingRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding = ProfileActivityBinding.inflate(getLayoutInflater());
        View view = activityProfileBinding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        String profileId = intent.getStringExtra("profileId");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        doref = db.collection("users").document(profileId);
        followerRef = db.collection("users").document(profileId);
        followingRef = db.collection("users").document(firebaseUser.getUid());
        isFollowing(profileId, activityProfileBinding.PFsetBtn);

        doref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    activityProfileBinding.PFuserName.setText(documentSnapshot.get("name").toString());
                    Glide.with(view).load(documentSnapshot.get("imageUri").toString()).into(
                            activityProfileBinding.PFUserImage);
                    followingRef.collection("following").document("counter").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                activityProfileBinding.PFuserfollowing.setText(task.getResult().get("count").toString());
                            }
                        }
                    });

                    followerRef.collection("followers").document("counter").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                activityProfileBinding.PFuserfollower.setText(task.getResult().get("count").toString());
                            }
                        }
                    });

                }
            }
        });

        activityProfileBinding.PFsetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btn = activityProfileBinding.PFsetBtn.getText().toString();
                Query query;
                if(btn.equals(getString(R.string.follow))){
                    // 사용자를 팔로우 한다.
                    followingRef.collection("following").add(profileId);
                    followingRef.collection("following").document("counter").update("count", FieldValue.increment(1));

                    followerRef.collection("followers").add(firebaseUser.getUid());
                    followerRef.collection("followers").document("counter").update("count", FieldValue.increment(1));
                }else if (btn.equals(getString(R.string.following))){
                    //팔로우를 해제한다.
                    followingRef.collection("following").document(profileId).delete();
                    followingRef.collection("following").document("counter").update("count", FieldValue.increment(-1));

                    followerRef.collection("followers").document(firebaseUser.getUid()).delete();
                    followerRef.collection("followers").document("counter").update("count", FieldValue.increment(-1));
                }
                followerRef.collection("follewers").document("counter").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            activityProfileBinding.PFuserfollower.setText(task.getResult().get("count").toString());
                        }
                    }
                });
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        ViewPager2 viewPager2 = findViewById(R.id.PF_viewPager);
        viewPager2.setAdapter(new ProfileAdapter(fm, getLifecycle()));

        TabLayout tabLayout = view.findViewById(R.id.PF_tablayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0:
                        tab.setText("소개");
                        break;
                    case 1:
                        tab.setText("작품");
                        break;
                    case 2:
                        tab.setText("전시회");
                        break;
                    case 3:
                        tab.setText("컬렉션");
                        break;
                }
            }
        }
        );
        tabLayoutMediator.attach();

    }

    private void isFollowing(String profileId, Button button){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Query query = firestore.collection("Follow")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("following");
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot item: queryDocumentSnapshots.getDocuments()) {
                    if(item.toString().equals(profileId))
                        button.setText(R.string.following);
                    else
                        button.setText(R.string.follow);
                }

            }
        });
    }

}
