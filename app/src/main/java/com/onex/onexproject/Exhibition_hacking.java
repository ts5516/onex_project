package com.onex.onexproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Exhibition_hacking extends AppCompatActivity {
    ImageView btn_backexhibition;
    Button btn_hacking2;
    MenuActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibition_hacking);
        setInit();
    }

public void setInit()
{
    btn_backexhibition = (ImageView)findViewById(R.id.btn_backexhibition);
    btn_hacking2 = (Button)findViewById(R.id.btn_hacking2);

    btn_hacking2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Exhibition_hacking.this, Exhibition_hacking2.class);
            startActivity(intent);
            finish();
        }
    });

}






}