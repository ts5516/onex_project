package com.onex.onexproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.onex.onexproject.Frag_Menu.Frag1_Home;
import com.onex.onexproject.Frag_Menu.Frag2_Exhibit;
import com.onex.onexproject.Frag_Menu.Frag3_Creator;
import com.onex.onexproject.Frag_Menu.Frag4_Profile;

public class MenuActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    public BottomNavigationView bottomNavigationView;
    private Button btn;
    private Frag1_Home frag1 = new Frag1_Home();
    private Frag2_Exhibit frag2 = new Frag2_Exhibit();
    private Frag3_Creator frag3 = new Frag3_Creator();
    private Frag4_Profile frag4 = new Frag4_Profile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());

                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);


    }


    private void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
        String tag = String.valueOf(id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            if (id == R.id.action_home) {
                fragment = new Frag1_Home();

            } else if (id == R.id.action_exhibition) {

                fragment = new Frag2_Exhibit();
            } else if (id == R.id.action_creator) {
                fragment = new Frag3_Creator();
            } else if (id == R.id.action_login) {
                fragment = new Frag4_Profile();
            }
            fragmentTransaction.add(R.id.layout_main_frame, fragment, tag);
        } else {
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
    }

    public void onFragmentChange(int index)
    {
        if(index==0)
        {
        getSupportFragmentManager().beginTransaction().replace(R.id.action_exhibition,frag2).commit();
        }
        else if(index==1)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.action_creator,frag3).commit();
        }
        else if(index ==2)
        {getSupportFragmentManager().beginTransaction().replace(R.id.action_login,frag4).commit();

        }
    }

}