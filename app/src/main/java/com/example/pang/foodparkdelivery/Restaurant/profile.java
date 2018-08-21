package com.example.pang.foodparkdelivery.Restaurant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pang.foodparkdelivery.R;

import static android.content.Context.MODE_PRIVATE;

public class profile extends Fragment {

    FloatingActionButton actionButton;
    SharedPreferences sharedpreferences;
    static final String MyPREFERENCES = "MyPrefs" ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_res_profile, null);

        actionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton2);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

        return view;
    }

    public void logOut(){

        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();  // ทำการลบข้อมูลทั้งหมดจาก preferences
        editor.commit();  // ยืนยันการแก้ไข preferences

        startActivity(new Intent(getActivity(), ResLogin.class));
    }
}
