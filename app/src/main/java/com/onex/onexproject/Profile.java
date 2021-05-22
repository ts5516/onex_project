package com.onex.onexproject;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.onex.onexproject.databinding.ActivityProfileBinding;
import com.onex.onexproject.Adapter.ProfileAdapter;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.onex.onexproject.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {

    private ActivityProfileBinding activityProfileBinding;
    private FragmentPagerAdapter fragmentPagerAdapter;

    ProfileAdapter adapter;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    DocumentReference db;
    String profileid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityProfileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = activityProfileBinding.getRoot();
        setContentView(view);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        activityProfileBinding.btnPfSet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String btn = activityProfileBinding.btnPfSet.getText().toString();

                if(btn.equals(getString(R.string.profileSet))){
                    // go to editprofile
                }else if(btn.equals(getString(R.string.follow))){
                    // 사용자를 팔로우 한다.
                }else if (btn.equals(getString(R.string.following))){
                    //팔로우를 해제한다.
                }
            }
        });
        FragmentManager fm = getSupportFragmentManager();
        adapter = new ProfileAdapter(fm, getLifecycle());
        activityProfileBinding.PFViewPager.setAdapter(adapter);

        activityProfileBinding.PFTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                activityProfileBinding.PFViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        activityProfileBinding.PFViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                activityProfileBinding.PFTablayout.selectTab(activityProfileBinding.PFTablayout.getTabAt(position));
            }
        });

    }

}