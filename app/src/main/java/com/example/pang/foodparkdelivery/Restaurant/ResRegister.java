package com.example.pang.foodparkdelivery.Restaurant;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

public class ResRegister extends Activity {
    Button register,cancel;
    EditText name,surname,phone,email,pass,Cpass,resName,resAdd,resMapAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_register);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  //full page not have title bar
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = (EditText) findViewById(R.id.editName);
        surname = (EditText) findViewById(R.id.editSurname);
        phone = (EditText) findViewById(R.id.editPhone);
        email = (EditText) findViewById(R.id.editEmail);
        pass = (EditText) findViewById(R.id.editPass);
        Cpass = (EditText) findViewById(R.id.editCpass);
        resName = (EditText) findViewById(R.id.editResName);
        resAdd = (EditText) findViewById(R.id.editResAdd);
        resMapAdd = (EditText) findViewById(R.id.editResMapAdd);
        cancel = (Button) findViewById(R.id.Cancel);
        register = (Button) findViewById(R.id.Register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResLogin.class));
            }
        });
    }

    public void Register(){

        String pass1 = pass.getText().toString();
        String pass2 = Cpass.getText().toString();

        if(pass1.equals(pass2)) {

            Ion.with(getApplicationContext())
                    .load("http://192.168.1.14/testPJ/ResTable/ResRegister.php")
                    .setMultipartParameter("name", name.getText().toString())
                    .setMultipartParameter("surname", surname.getText().toString())
                    .setMultipartParameter("phone", phone.getText().toString())
                    .setMultipartParameter("email", email.getText().toString())
                    .setMultipartParameter("pass", pass.getText().toString())
                    .setMultipartParameter("resName", resName.getText().toString())
                    .setMultipartParameter("resAdd", resAdd.getText().toString())
                    .setMultipartParameter("resMapAdd", resMapAdd.getText().toString())
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            String re = result.get("res").getAsString();
                            String res = result.get("status").getAsString();
                            if (res.endsWith("#1")) {
                                startActivity(new Intent(getApplicationContext(), ResLogin.class));
                                Toast.makeText(getBaseContext(), re, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getBaseContext(), re, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else {
            Toast.makeText(getBaseContext(), "รหัสผ่านยืนยันไม่ถูกต้อง", Toast.LENGTH_LONG).show();
        }
    }
}
