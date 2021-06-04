package com.onex.onexproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ArtinfoActivity extends AppCompatActivity {

    FirebaseFirestore db;
    DocumentReference ref;
    ImageView artImage, artLike;
    TextView artTitle, artSize, artShape, artWorkingYear, artTag, artType;
    FirebaseUser firebaseUser;
    public boolean isLike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artinfo);

        Intent intent = getIntent();
        String artID = intent.getStringExtra("artID");
        String profileID = intent.getStringExtra("profileID");
        isLike = false;
        artLike = findViewById(R.id.artLike);
        artImage = findViewById(R.id.artImage);
        artTitle = findViewById(R.id.artTitle);
        artWorkingYear = findViewById(R.id.artWorkingYear);
        artSize = findViewById(R.id.artSize);
        artShape = findViewById(R.id.artShape);
        artTag = findViewById(R.id.artTag);
        artType = findViewById(R.id.artType);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        ref = db.collection("users").document(profileID);
        if(firebaseUser != null){
            if(!profileID.equals(firebaseUser.getUid())){
                ref.collection("artPiece").document(artID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            Glide.with(getApplicationContext()).load(task.getResult().get("uri").toString()).into(artImage);
                            artTitle.setText(task.getResult().get("name").toString());
                            artShape.setText(task.getResult().get("shape").toString());
                            artWorkingYear.setText(task.getResult().get("createTime").toString());
                            artSize.setText(task.getResult().get("size").toString());
                            artTag.setText(task.getResult().get("tag").toString());
                            artType.setText(task.getResult().get("type").toString());
                            db.collection("users").document(firebaseUser.getUid()).
                                    collection("likes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                                    QuerySnapshot querySnapshot = task.getResult();
                                    for(DocumentSnapshot item : querySnapshot){
                                        if(item.getId().equals(artID)){
                                            artLike.setImageResource(R.drawable.outline_favorite_24);
                                            isLike = true;
                                        }


                                    }
                                }
                            });
                        }
                    }
                });
            }
            else{
                artLike.setVisibility(View.GONE);
                ref.collection("artPiece").document(artID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            Glide.with(getApplicationContext()).load(task.getResult().get("uri").toString()).into(artImage);
                            artTitle.setText(task.getResult().get("name").toString());
                            artShape.setText(task.getResult().get("shape").toString());
                            artWorkingYear.setText(task.getResult().get("createTime").toString());
                            artSize.setText(task.getResult().get("size").toString());
                            artTag.setText(task.getResult().get("tag").toString());
                            artType.setText(task.getResult().get("type").toString());
                        }
                    }
                });
            }
        }

        artLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLikes(artID, artLike, isLike);
                isLike = !isLike;
            }
        });
    }

    private void isLikes(String artID, ImageView artLike, boolean isLike){
        DocumentReference ref2 = db.collection("users").document(firebaseUser.getUid()).collection("likes").document(artID);
        if(isLike){
            ref2.delete();
            artLike.setImageResource(R.drawable.outline_favorite_border_24);
        }
        else{
            ref.collection("artPiece").document(artID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Map<String, String> likes = new HashMap<>();
                    likes.put("uri", documentSnapshot.get("uri").toString());
                    ref2.set(likes);
                    artLike.setImageResource(R.drawable.outline_favorite_24);

                }
            });
        }
    }


}