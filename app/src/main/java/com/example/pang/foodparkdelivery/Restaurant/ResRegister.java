package com.example.pang.foodparkdelivery.Restaurant;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pang.foodparkdelivery.R;
import com.example.pang.foodparkdelivery.ipConfig;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class ResRegister extends Activity {
    Button register,cancel,map;
    EditText name,surname,phone,email,pass,Cpass,resName,resAdd;
    Activity mActivity;


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
                mActivity.finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void Register(){

        String pass1 = pass.getText().toString();
        String pass2 = Cpass.getText().toString();

        String name1 = name.getText().toString();
        String surname1 = surname.getText().toString();
        String phone1 = phone.getText().toString();
        String email1 = email.getText().toString();
        String resName1 = resName.getText().toString();
        String resAdd1 = resAdd.getText().toString();

        if(pass1.equals(pass2)) {

        Intent goToUpdate = new Intent(this, getResLocationsMapsActivity.class);
        goToUpdate.putExtra("name", name1);
        goToUpdate.putExtra("surname", surname1);
        goToUpdate.putExtra("phone",phone1);
        goToUpdate.putExtra("email", email1);
        goToUpdate.putExtra("pass", pass1);
        goToUpdate.putExtra("resName", resName1);
        goToUpdate.putExtra("resAdd", resAdd1);
        this.startActivity(goToUpdate);

        }else {
            Toast.makeText(getBaseContext(), "รหัสผ่านยืนยันไม่ถูกต้อง", Toast.LENGTH_LONG).show();
        }
    }
}
