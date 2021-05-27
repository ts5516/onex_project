package com.onex.onexproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Exhibition_hacking3 extends AppCompatActivity {
    Context context;
    ImageView hacking11,hacking12,hacking13,hacking14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibition_hacking3);
        hacking11 = (ImageView)findViewById(R.id.hacking11);
        hacking12 = (ImageView)findViewById(R.id.hacking12);
        hacking13 = (ImageView)findViewById(R.id.hacking13);
        hacking14 = (ImageView)findViewById(R.id.hacking14);
        setImageView(hacking11,R.drawable.hacking11);
        setImageView(hacking12,R.drawable.hacking12);
        setImageView(hacking13,R.drawable.hacking13);
        setImageView(hacking14,R.drawable.hacking14);
    }
    public  void setImageView(ImageView imageView,int source)
    {

        Glide.with(this)
                .load(source)
                .into(imageView);
    }

}