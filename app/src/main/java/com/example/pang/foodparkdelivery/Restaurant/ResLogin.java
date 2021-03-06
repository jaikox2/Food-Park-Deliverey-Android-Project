package com.example.pang.foodparkdelivery.Restaurant;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pang.foodparkdelivery.R;
import com.example.pang.foodparkdelivery.ipConfig;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import static android.content.Context.MODE_PRIVATE;

public class ResLogin extends Fragment {

    EditText EdUser, passED;
    Button login,register;
    String user, pass;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String User_id = "userKey";            //save session
    SharedPreferences sharedpreferences;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_res_login, null);


        EdUser = (EditText) view.findViewById(R.id.edtUser);
        passED = (EditText) view.findViewById(R.id.edtPasswd);  //declare Edit text and button
        login = (Button) view.findViewById(R.id.btnLogin);
        register = (Button) view.findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ResRegister.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateUser();
            }
        });

        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);  // ดึง share preference ชื่อ MyPrefs เก็บไว้ในตัวแปร sharedpreferences
        if (sharedpreferences.contains(User_id))   // ตรวจสอบ name ใน preference
        {
                startActivity(new Intent(getContext(), ResMenuActivity.class));
        }

        return view;
    }

    public void ValidateUser() {

        final SharedPreferences.Editor editor = sharedpreferences.edit();
        user = EdUser.getText().toString();
        pass = passED.getText().toString();

        System.out.println("User" + user);
        System.out.println("Pass" + pass);

        ipConfig ip = new ipConfig();
        final String baseUrl = ip.getBaseUrlRes() ;

        Ion.with(getContext())
                .load(baseUrl+"ResLogin.php")
                .setBodyParameter("login", EdUser.getText().toString())
                .setBodyParameter("password", passED.getText().toString())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        String user_id = result.get("user_id").getAsString();
                        String res = result.get("status").getAsString();
                        if (res.endsWith("#1")) {
                            startActivity(new Intent(getContext(), ResMenuActivity.class));
                            editor.putString(User_id, user_id);  // preferance เก็บค่า user_id จาก edittext
                            editor.commit();  // ยืนยันการแก้ไข preferance
                        } else {
                            Toast.makeText(getContext(), res, Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
