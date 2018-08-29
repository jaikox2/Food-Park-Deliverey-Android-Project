package com.example.pang.foodparkdelivery.Restaurant;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pang.foodparkdelivery.R;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class ResLogin extends Activity {

    EditText EdUser, passED;
    Button login,register;
    String user, pass;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String User_id = "userKey";            //save session
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_login);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  //full page not have title bar
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        EdUser = (EditText) findViewById(R.id.edtUser);
        passED = (EditText) findViewById(R.id.edtPasswd);  //declare Edit text and button
        login = (Button) findViewById(R.id.btnLogin);
        register = (Button) findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResRegister.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateUser();
            }
        });

        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);  // ดึง share preference ชื่อ MyPrefs เก็บไว้ในตัวแปร sharedpreferences
        if (sharedpreferences.contains(User_id))   // ตรวจสอบ name ใน preference
        {
                startActivity(new Intent(getApplicationContext(), ResMenuActivity.class));
        }
    }

    public void ValidateUser() {

        final SharedPreferences.Editor editor = sharedpreferences.edit();
        user = EdUser.getText().toString();
        pass = passED.getText().toString();

        System.out.println("User" + user);
        System.out.println("Pass" + pass);

        Ion.with(getApplicationContext())
                .load("http://192.168.136.213/testPJ/ResTable/ResLogin.php")
                .setBodyParameter("login", EdUser.getText().toString())
                .setBodyParameter("password", passED.getText().toString())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        String user_id = result.get("user_id").getAsString();
                        String res = result.get("status").getAsString();
                        if (res.endsWith("#1")) {
                            startActivity(new Intent(getApplicationContext(), ResMenuActivity.class));
                            editor.putString(User_id, user_id);  // preferance เก็บค่า user_id จาก edittext
                            editor.commit();  // ยืนยันการแก้ไข preferance
                        } else {
                            Toast.makeText(getBaseContext(), res, Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
