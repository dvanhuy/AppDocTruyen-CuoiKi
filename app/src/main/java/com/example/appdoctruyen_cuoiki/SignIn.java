package com.example.appdoctruyen_cuoiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        TextView forgotpass = findViewById(R.id.forgotpass);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
            }
        });

        TextView signupforgotpass = findViewById(R.id.signupforgotpass);
        signupforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this,ForgotPass.class);
                startActivity(intent);
            }
        });
    }
}