package com.onex.onexproject.Frag_Menu;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.onex.onexproject.MenuActivity;
import com.onex.onexproject.R;


public class Frag1_Home extends Fragment {

    FirebaseStorage firebaseStorage;
    FirebaseFirestore firebaseFirestore;
    MenuActivity activity;
    ImageView btn_exhibit;
    ImageView btn_creator;

    public void onAttach(Context context) {

        super.onAttach(context);
        activity = (MenuActivity) getActivity();
    }

    public void onDetach() {

        super.onDetach();
        activity = null;
    }

    private int setSimpleSize(BitmapFactory.Options options, int requestWidth, int requestHeight){
        // 이미지 사이즈를 체크할 원본 이미지 가로/세로 사이즈를 임시 변수에 대입.
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;

        // 원본 이미지 비율인 1로 초기화
        int size = 1;

        // 해상도가 깨지지 않을만한 요구되는 사이즈까지 2의 배수의 값으로 원본 이미지를 나눈다.
        while(requestWidth < originalWidth || requestHeight < originalHeight){
            originalWidth = originalWidth / 2;
            originalHeight = originalHeight / 2;

            size = size * 2;
        }
        return size;
    }

    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.menu_frag_home, container, false);
        btn_exhibit = (ImageView) rootView.findViewById(R.id.btn_exhibition);
        btn_creator = (ImageView) rootView.findViewById(R.id.btn_creator);





        btn_exhibit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.bottomNavigationView.setSelectedItemId(R.id.action_exhibition);
            }
        });
        btn_creator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.bottomNavigationView.setSelectedItemId(R.id.action_creator);
            }
        });

        return rootView;
    }
}

