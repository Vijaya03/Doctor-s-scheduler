package com.example.sana.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ClerkPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk_page);
    }

    public void addPatient(View view) {
        Intent intent=new Intent(this,PatientDetails.class);
        startActivity(intent);
    }

    public void patientDetails(View view) {
        Intent intent=new Intent(this,ViewPatientDetail.class);
        startActivity(intent);
    }
}
