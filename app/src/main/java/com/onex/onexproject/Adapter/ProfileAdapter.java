package com.onex.onexproject.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onex.onexproject.Frag_Profile.FragProfile_intro;
import com.onex.onexproject.Frag_Profile.FragProfile_art;
import com.onex.onexproject.Frag_Profile.FragProfile_exhibit;
import com.onex.onexproject.Frag_Profile.FragProfile_collection;

import org.jetbrains.annotations.NotNull;

public class ProfileAdapter extends FragmentStateAdapter {

    public ProfileAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragProfile_intro();
            case 1:
                return new FragProfile_art();
            case 2:
                return new FragProfile_exhibit();
            case 3:
                return new FragProfile_collection();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
