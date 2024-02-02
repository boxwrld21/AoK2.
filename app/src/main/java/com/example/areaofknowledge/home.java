package com.example.areaofknowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);



        Button act2 = findViewById(R.id.act2);

        View.OnClickListener onClickListener = view -> {
            Intent intent = new Intent(home.this, Registration.class);
            startActivity(intent);
        };
        act2.setOnClickListener(onClickListener);

    }
}