package com.example.areaofknowledge;






import android.content.Intent;
import android.os.Bundle;

import android.app.Activity;
import android.os.Handler;


public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, home.class);
            startActivity(intent);
            finish();
        }, 5*1000);


    }
}
