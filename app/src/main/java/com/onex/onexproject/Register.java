package com.onex.onexproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.onex.onexproject.databinding.ActivityLoginBinding;
import com.onex.onexproject.databinding.ActivityResigterBinding;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private ActivityResigterBinding activityResigterBinding;
    private static final String TAG = "Register";

    private String userID;
    private Uri imageUri;
    private static final int PICK_IMAGE = 1;

    UploadTask uploadTask;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityResigterBinding = ActivityResigterBinding.inflate(getLayoutInflater());
        View view = activityResigterBinding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        documentReference = fStore.collection("user").document(userID);
        storageReference = firebaseStorage.getInstance().getReference("profile images");

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        activityResigterBinding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });
    }

    private void registerUser(){
        String fullName = activityResigterBinding.registerName.getText().toString().trim();
        String email = activityResigterBinding.registerEmail.getText().toString().trim();
        String pwd = activityResigterBinding.registerPWD.getText().toString();
        String confirmPwd = activityResigterBinding.confirmPWD.getText().toString();

        if (fullName.isEmpty()){
            activityResigterBinding.registerName.setError(getString(R.string.nameEmpty));
            return;
        }if(email.isEmpty()){
            activityResigterBinding.registerEmail.setError(getString(R.string.emailEmpty));
            return;
        }if(pwd.isEmpty()){
            activityResigterBinding.registerPWD.setError(getString(R.string.pwdEmpty));
            return;
        }if(confirmPwd.isEmpty()){
            activityResigterBinding.confirmPWD.setError(getString(R.string.pwdEmpty));
            return;
        }if(!pwd.equals(confirmPwd)){
            activityResigterBinding.confirmPWD.setError(getString(R.string.pwdNOMatch));
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this, getString(R.string.signUpSuccess), Toast.LENGTH_SHORT).show();
                    Map<String, Object> user = new HashMap<>();
                    user.put("fName", fullName);
                    user.put("email", email);
                    user.put("pwd", pwd);
                    user.put("imageUri", null); // 초기이미지는 프로파일에서 설정한다.

                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.toString());
                        }
                    });
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                }else{
                    Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

}