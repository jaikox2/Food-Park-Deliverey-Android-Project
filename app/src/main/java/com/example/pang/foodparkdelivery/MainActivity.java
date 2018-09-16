package com.example.pang.foodparkdelivery;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.example.pang.foodparkdelivery.Restaurant.ResLogin;
public class MainActivity extends Activity {
    Button customer,restaurant,delivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  //full page not have title bar
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        customer = (Button) findViewById(R.id.customer);
        restaurant = (Button) findViewById(R.id.restaurant);
        delivery = (Button) findViewById(R.id.delivery);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResLogin.class));
            }
        });
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}