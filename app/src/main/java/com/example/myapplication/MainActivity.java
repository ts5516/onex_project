package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private frag1 fr1;
    private frag2 fr2;
    private frag3 fr3;
    private frag4 fr4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_photo:
                        setFrag(0);
                        break;
                    case R.id.action_like:
                        setFrag(1);
                        break;
                    case R.id.action_home:
                        setFrag(2);
                        break;
                    case R.id.action_id:
                        setFrag(3);
                        break;
                }

                return false;

                }
        });

        fr1= new frag1();
        fr2= new frag2();
        fr3= new frag3();
        fr4= new frag4();
        setFrag(0);//첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택

    }
    //프래그먼트 교체가 일어나는 실행문
    private void setFrag(int n)
    {
        fm= getSupportFragmentManager();
        ft=fm.beginTransaction();
        switch (n)
        {
            case 0:
                ft.replace(R.id.main_frame,fr1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame,fr2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame,fr3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame,fr4);
                ft.commit();
                break;

        }

    }


}
