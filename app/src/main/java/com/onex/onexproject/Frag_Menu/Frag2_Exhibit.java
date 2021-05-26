package com.onex.onexproject.Frag_Menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.onex.onexproject.Exhibition_hacking;
import com.onex.onexproject.R;
import com.onex.onexproject.SearchActivity;
import com.onex.onexproject.ViewPagerAdapter;

public class Frag2_Exhibit extends Fragment {
    ImageView button;
    Activity activity;
  Context context;
    ViewPagerAdapter adapter;
    ViewPager viewPager;
    ImageButton SearchBtn;
    private ViewGroup rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.menu_frag_exhibit, container, false);
        activity =getActivity();
        context =getContext();
        viewPager = rootView.findViewById(R.id.viewPager2);
        adapter = new ViewPagerAdapter(context);
        viewPager.setAdapter(adapter);
        button =  rootView.findViewById(R.id.btn_hacking);
        SearchBtn = rootView.findViewById(R.id.Serachbtn);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity.getApplicationContext(), Exhibition_hacking.class);
                startActivity(intent);
            }
        });

        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        return rootView;

    }



}
