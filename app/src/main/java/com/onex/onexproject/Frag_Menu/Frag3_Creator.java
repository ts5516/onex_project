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

import com.onex.onexproject.CreatorPagerAdapter;
import com.onex.onexproject.Exhibition_hacking;
import com.onex.onexproject.R;
import com.onex.onexproject.SearchActivity;
import com.onex.onexproject.ViewPagerAdapter;
import com.pm10.library.LineIndicator;

public class Frag3_Creator extends Fragment {
    ImageView button;
    Activity activity;
    Context context;
    CreatorPagerAdapter adapter;
    ViewPager viewPager;
    ImageView btn_lemon;
    private ViewGroup rootView;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.menu_frag_creator, container, false);
        activity =getActivity();
        context =getContext();
        viewPager = rootView.findViewById(R.id.viewPager3);
        adapter = new CreatorPagerAdapter(context);
        viewPager.setAdapter(adapter);
        LineIndicator lineIndicator = rootView.findViewById(R.id.line_indicator);
        lineIndicator.setupWithViewPager(viewPager);
        return rootView;

    }
}
