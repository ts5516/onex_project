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

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ArtinfoActivity extends AppCompatActivity {

    FirebaseFirestore db;
    DocumentReference ref;
    ImageView artImage, artLike;
    TextView artTitle, artSize, artShape, artWorkingYear, artTag, artType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artinfo);

        Intent intent = getIntent();
        String artID = intent.getStringExtra("artID");

        artLike = findViewById(R.id.artLike);
        artImage = findViewById(R.id.artImage);
        artTitle = findViewById(R.id.artTitle);
        artWorkingYear = findViewById(R.id.artWorkingYear);
        artSize = findViewById(R.id.artSize);
        artShape = findViewById(R.id.artShape);
        artTag = findViewById(R.id.artTag);
        artType = findViewById(R.id.artType);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        ref = db.collection("users").document(firebaseUser.getUid());
        ref.collection("artPiece").document(artID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            Glide.with(getApplicationContext()).load(task.getResult().get("uri").toString()).into(artImage);
                            ref.collection("likes").document(artID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                                    if(task.getResult().getId().equals(artID)){
                                        artLike.setImageResource(R.drawable.outline_favorite_24);
                                    }
                                }
                            });
                        }
                    }
                });

        artLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLikes(artID, artLike);
            }
        });
    }

    private void isLikes(String artID, ImageView imageView){

        ref.collection("likes").document(artID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if(task.getResult().getId().equals(artID)){
                    ref.collection("likes").document(artID).delete();
                    imageView.setImageResource(R.drawable.outline_favorite_border_24);
                }
                else{
                    Map<String, String> likes = new HashMap<>();
                    likes.put("uri", task.getResult().get("uri").toString());
                    ref.collection("likes").document(artID).set(likes);
                    imageView.setImageResource(R.drawable.outline_favorite_24);
                }
            }
        });
    }


}