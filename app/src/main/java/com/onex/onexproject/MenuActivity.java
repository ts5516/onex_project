package com.onex.onexproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.onex.onexproject.Frag_Menu.Frag1_Home;
import com.onex.onexproject.Frag_Menu.Frag2_Exhibit;
import com.onex.onexproject.Frag_Menu.Frag3_Creator;
import com.onex.onexproject.Frag_Menu.Frag4_Profile;

public class MenuActivity extends AppCompatActivity {

    private String value=null;
    Dialog dilaog01;
    private Button btn_yes;
    private Button btn_no;
    private Menu menu;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    public BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    private FragmentTransaction ft;
    private FragmentManager fm;

    private Frag1_Home frag1 = new Frag1_Home();
    private Frag2_Exhibit frag2 = new Frag2_Exhibit();
    private Frag3_Creator frag3 = new Frag3_Creator();
    private Frag4_Profile frag4 = new Frag4_Profile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
        Intent intent =getIntent();
        String value = intent.getStringExtra("value");
        dilaog01 = new Dialog(MenuActivity.this);       // Dialog 초기화
        dilaog01.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dilaog01.setContentView(R.layout.dialog);
        btn_no = dilaog01.findViewById(R.id.noBtn);
        btn_yes =dilaog01.findViewById(R.id.yesBtn);



        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        menu=bottomNavigationView.getMenu();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        setFrag(0);
                        menuItem.setIcon(R.drawable.ic_selected_1);
                        menu.findItem(R.id.action_exhibition).setIcon(R.drawable.ic_unselected_2);
                        menu.findItem(R.id.action_creator).setIcon(R.drawable.ic_unselected_3);
                        menu.findItem(R.id.action_login).setIcon(R.drawable.ic_unselected_4);
                        break;
                    case R.id.action_exhibition:
                        setFrag(1);
                        menuItem.setIcon(R.drawable.ic_selected_2);
                        menu.findItem(R.id.action_home).setIcon(R.drawable.ic_unselected_1);
                        menu.findItem(R.id.action_creator).setIcon(R.drawable.ic_unselected_3);
                        menu.findItem(R.id.action_login).setIcon(R.drawable.ic_unselected_4);
                        break;
                    case R.id.action_creator:
                        setFrag(2);
                        menuItem.setIcon(R.drawable.ic_selected_3);
                        menu.findItem(R.id.action_home).setIcon(R.drawable.ic_unselected_1);
                        menu.findItem(R.id.action_exhibition).setIcon(R.drawable.ic_unselected_2);
                        menu.findItem(R.id.action_login).setIcon(R.drawable.ic_unselected_4);
                        break;
                    case R.id.action_login:
                        if(FirebaseAuth.getInstance().getCurrentUser()==null)

                        {
                            dilaog01.show();
                            btn_yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dilaog01.dismiss();
                                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        btn_no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                setFrag(0);
                                Log.d("MenuActivity", "Debug");
                                dilaog01.dismiss();
                            }
                        });
                        break;
                        }
                        else {
                            setFrag(3);
                            break;
                        }
                }

                return true;
            }
        });

        frag1 = new Frag1_Home();
        frag2 = new Frag2_Exhibit();
        frag3 = new Frag3_Creator();
        frag4 = new Frag4_Profile();
        //첫 화면 설정
        setFrag(0);


    }


    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.layout_main_frame, frag1);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.layout_main_frame, frag2);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.layout_main_frame, frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.layout_main_frame, frag4);
                ft.commit();
                break;

        }
    }


}