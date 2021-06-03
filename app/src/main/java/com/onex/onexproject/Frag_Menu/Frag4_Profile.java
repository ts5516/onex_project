package com.onex.onexproject.Frag_Menu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.onex.onexproject.Adapter.ProfileAdapter;
import com.onex.onexproject.Adapter.SearchAdapter;
import com.onex.onexproject.LoginActivity;
import com.onex.onexproject.MenuActivity;
import com.onex.onexproject.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Frag4_Profile extends Fragment {

    private FirebaseFirestore db;
    private FirebaseUser firebaseUser;
    private DocumentReference doref;
    private TextView userName, userFollower, userFollowing;
    private CircleImageView cirImage;
    private Button profileSetBtn, logoutBtn;
    MenuActivity activity;

    public void onAttach(Context context) {

        super.onAttach(context);
        activity = (MenuActivity) getActivity();
    }

    public void onDetach() {

        super.onDetach();
        activity = null;
    }

    public String getID(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.menu_frag_profile,container,false);

        userName = view.findViewById(R.id.PFuserName);
        userFollower = view.findViewById(R.id.PFuserfollower);
        userFollowing = view.findViewById(R.id.PFuserfollowing);
        cirImage = view.findViewById(R.id.PFUserImage);
        profileSetBtn = view.findViewById(R.id.PFsetBtn);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null){
            db = FirebaseFirestore.getInstance();
            doref = db.collection("users").document(firebaseUser.getUid());
            doref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        userName.setText(documentSnapshot.get("name").toString());
                        Glide.with(view).load(documentSnapshot.get("imageUri").toString()).into(cirImage);
                    }
                }
            });
       //     doref = db.collection("Follow").document(firebaseUser.getUid()).collection("followers").

        }

        FragmentManager fm = getFragmentManager();
        ViewPager2 viewPager2 = view.findViewById(R.id.PF_viewPager);
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
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        profileSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageReference storageReference = firebaseStorage.getReferenceFromUrl("gs://taesungislove.appspot.com/전시 이미지");

                storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference item: listResult.getItems()) {

                            item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    doref = db.collection("users").
                                            document(firebaseUser.getUid()).collection("artPiece").document(item.getName());
                                    Map<String, Object> exhibition = new HashMap<>();
                                    exhibition.put("uri", uri.toString());
                                    exhibition.put("size", "");
                                    exhibition.put("tag","");
                                    doref.set(exhibition);
                                }
                            });
                        }
                    }
                });
            }
        });
        return view;
    }

}
