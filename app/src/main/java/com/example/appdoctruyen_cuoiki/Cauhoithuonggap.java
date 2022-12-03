package com.example.appdoctruyen_cuoiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Cauhoithuonggap extends AppCompatActivity {
    ImageView backcauhoithuonggap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cauhoithuonggap);
        backcauhoithuonggap=findViewById(R.id.backcauhoithuonggap);
        backcauhoithuonggap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}