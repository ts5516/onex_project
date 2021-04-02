package com.onex.onexproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.onex.onexproject.databinding.ActivityLoginBinding;
import com.onex.onexproject.databinding.ActivityResigterBinding;

public class Register extends AppCompatActivity {

    private ActivityResigterBinding activityResigterBinding;
    private static final String TAG = "Register";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityResigterBinding = ActivityResigterBinding.inflate(getLayoutInflater());
        View view = activityResigterBinding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        activityResigterBinding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = activityResigterBinding.registerEmail.getText().toString().trim();
                String pwd = activityResigterBinding.registerPWD.getText().toString().trim();
                String confirmPwd = activityResigterBinding.confirmPWD.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    activityResigterBinding.registerEmail.setError(getString(R.string.emailEmpty));
                    return;
                }

                if(TextUtils.isEmpty(pwd)){
                    activityResigterBinding.registerPWD.setError(getString(R.string.pwdEmpty));
                    return;
                }

                if(TextUtils.equals(pwd, confirmPwd)){
                    activityResigterBinding.confirmPWD.setError(getString(R.string.pwdNOMatch));
                }


                mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("Register", getString(R.string.signUpSuccess));
                            startActivity(new Intent(getApplicationContext(), Profile.class));
                        }else{
                            Log.e("Register", task.getException().getMessage());
                        }
                    }
                });




            }
        });
    }
}