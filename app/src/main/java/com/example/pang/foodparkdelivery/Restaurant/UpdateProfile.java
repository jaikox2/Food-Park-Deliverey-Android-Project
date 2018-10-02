package com.example.pang.foodparkdelivery.Restaurant;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pang.foodparkdelivery.R;
import com.example.pang.foodparkdelivery.ipConfig;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class UpdateProfile extends AppCompatActivity {

    Button actionButton;
    EditText res_name,res_address,name,surname,phone,resMapAdd;
    TextView email;
    private String resname,resAddress,name1,surname1,email1,phone1,resAddMap;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String User_id = "userKey";            //save session
    SharedPreferences sharedpreferences;

    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        res_name = (EditText) findViewById(R.id.res_name);
        res_address = (EditText) findViewById(R.id.res_address);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        email = (TextView) findViewById(R.id.res_email);
        phone = (EditText) findViewById(R.id.res_phone);
        resMapAdd = (EditText) findViewById(R.id.res_MapAdd);

        actionButton = (Button) findViewById(R.id.floatingActionButton2);

        //back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            //get intent to get person id
            resname = getIntent().getStringExtra("resName");
            resAddress = getIntent().getStringExtra("resAdd");
            name1 = getIntent().getStringExtra("name");
            surname1 = getIntent().getStringExtra("surname");
            email1 = getIntent().getStringExtra("email");
            phone1 = getIntent().getStringExtra("phone");
            resAddMap = getIntent().getStringExtra("resAddMap");
        } catch (Exception e) {
            e.printStackTrace();
        }

        res_name.setText(resname);
        res_address.setText(resAddress);
        name.setText(name1);
        surname.setText(surname1);
        email.setText(email1);
        phone.setText(phone1);
        resMapAdd.setText(resAddMap);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
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

    private void updateProfile(){
        String Resname = res_name.getText().toString();
        String ResAddress = res_address.getText().toString();
        String Name = name.getText().toString();
        String Surname = surname.getText().toString();
        String Phone = phone.getText().toString();
        String ResMapAdd = resMapAdd.getText().toString();


        sharedpreferences = this.getApplication().getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String user_id = sharedpreferences.getString(User_id, "");

        ipConfig ip = new ipConfig();
        final String baseUrl = ip.getBaseUrlRes() ;

        Ion.with(this)
                .load(baseUrl + "UpdateProfile.php")
                .setMultipartParameter("resname", Resname)
                .setMultipartParameter("address", ResAddress)
                .setMultipartParameter("firstname", Name)
                .setMultipartParameter("lastname", Surname)
                .setMultipartParameter("phone", Phone)
                .setMultipartParameter("addmap", ResMapAdd)
                .setMultipartParameter("Res_id", user_id)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        String res = result.get("status").getAsString();
                        if (res.equals("update success")) {
                            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                           // mActivity.finish();
                           // startActivity(new Intent(getApplicationContext(), profile.class));

                        } else {
                            Toast.makeText(getApplicationContext(), "Can't saved", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
