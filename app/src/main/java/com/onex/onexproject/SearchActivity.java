package com.onex.onexproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.onex.onexproject.Adapter.SearchAdapter;

import org.jetbrains.annotations.NotNull;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ViewPager2 viewPager2 = findViewById(R.id.search_viewPager);
        viewPager2.setAdapter(new SearchAdapter(this));

        TabLayout tabLayout = findViewById(R.id.searchTab);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:{
                        tab.setText("인기");
                        break;
                    }
                    case 1:
                        tab.setText("유저");
                        break;
                    case 2:
                        tab.setText("태그");
                        break;
                    case 3:
                        tab.setText("전시회");
                        break;
                }
            }
        }
        );
        tabLayoutMediator.attach();
    }
}