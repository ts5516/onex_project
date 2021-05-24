package com.onex.onexproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class Frag2_Exhibit extends Fragment {
    ImageView button;
    Activity activity;
  Context context;
    ViewPagerAdapter adapter;
    ViewPager viewPager;
    private ViewGroup rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.frag2_exhibit, container, false);
        activity =getActivity();
        context =getContext();
        viewPager = rootView.findViewById(R.id.viewPager2);
        adapter = new ViewPagerAdapter(context);
        viewPager.setAdapter(adapter);
        button =  rootView.findViewById(R.id.btn_hacking);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity.getApplicationContext(),Exhibition_hacking.class);
                startActivity(intent);
            }
        });
        return rootView;

    }



}
