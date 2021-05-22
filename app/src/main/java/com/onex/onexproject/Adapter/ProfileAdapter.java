package com.onex.onexproject.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onex.onexproject.fragment.FragProfile1;
import com.onex.onexproject.fragment.FragProfile2;
import com.onex.onexproject.fragment.FragProfile3;
import com.onex.onexproject.fragment.FragProfile4;

public class ProfileAdapter extends FragmentStateAdapter {
    public ProfileAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragProfile1();
            case 1:
                return new FragProfile2();
            case 2:
                return new FragProfile3();
            case 3:
                return new FragProfile4();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
