package com.onex.onexproject.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onex.onexproject.Frag_Search.searchExhibitionFragment;
import com.onex.onexproject.Frag_Search.searchPopularityFragment;
import com.onex.onexproject.Frag_Search.searchTagFragment;
import com.onex.onexproject.Frag_Search.searchUserFragment;

import org.jetbrains.annotations.NotNull;

public class SearchAdapter extends FragmentStateAdapter {
    public SearchAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new searchUserFragment();
            case 1:
                return new searchExhibitionFragment();
            case 2:
                return new searchTagFragment();
            default:
                return new searchPopularityFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
