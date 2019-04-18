package com.example.sana.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ButtonChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_choice);
    }

    public void DoctorLogin(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void ClerkLogin(View view) {
        Intent i=new Intent(this,ClerkLogin.class);
        startActivity(i);

    }
}
