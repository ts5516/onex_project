package com.onex.onexproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.onex.onexproject.databinding.ActivityLoginBinding;
import com.onex.onexproject.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //바인딩 객체 선언
    private ActivityMainBinding mBinding;

    FirebaseStorage firebaseStorage;
    FirebaseFirestore firebaseFirestore;
    DocumentReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //액티비티 바인딩 객체에 할당 및 뷰 설정
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        mBinding.syncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int num = 3069; num <= 3127; num++){
                    StorageReference storageReference = firebaseStorage.getReference();
                    String imageName = "IMG_" + num + ".JPG";
                    db = firebaseFirestore.collection("exhibition").document(imageName);
                    storageReference.child("전시 이미지/" + "IMG_" + num + ".JPG").getDownloadUrl()
                            .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    Map<String, Object> exhibition = new HashMap<>();
                                    exhibition.put("imageUri", uri.toString());
                                    db.set(exhibition);
                                }
                            });

                }


            }
        });

        mBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        mBinding.imageViewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = firebaseFirestore.collection("exhibition")
                        .document("IMG_3079.JPG");
                db.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String uri = task.getResult().getString("imageUri");
                        Picasso.get().load(uri).resize(900,500).into(mBinding.imageView);
                    }
                });
            }
        });

    }
}
