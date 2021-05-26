package com.onex.onexproject.Frag_Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.onex.onexproject.R;

public class FragProfile_exhibit extends Fragment {
    private View view;

    public FragProfile_exhibit(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_frag_exhibit, container, false);
        return view;
    }
}
