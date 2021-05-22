package com.onex.onexproject.Frag_Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.onex.onexproject.Frag2_Exhibit;
import com.onex.onexproject.R;

public class FragProfile3 extends Fragment {
    private View view;

    public FragProfile3(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_profile3, container, false);
        return view;
    }
}
