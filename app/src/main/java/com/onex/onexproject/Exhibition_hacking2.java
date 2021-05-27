package com.onex.onexproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Exhibition_hacking2 extends AppCompatActivity {

    ImageView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibition_hacking2);
        button = (ImageView)findViewById(R.id.btn_hacking3);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exhibition_hacking2.this, Exhibition_hacking3.class);
                startActivity(intent);
                finish();
            }
        });
    }
}