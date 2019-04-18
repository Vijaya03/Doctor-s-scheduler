package com.example.sana.medicalapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sana.medicalapp.com.beans.Patient;
import com.example.sana.medicalapp.dbutil.MedicalConstant;
import com.example.sana.medicalapp.dbutil.MedicalManager;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewPatientDetail extends AppCompatActivity {
    MedicalManager medicalManager;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<Patient> patientList;
    Patient patient;
    ListView listView;
    int day,month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_detail);
        listView=findViewById(R.id.listView);
        medicalManager=new MedicalManager(this);
        sqLiteDatabase=medicalManager.openDB();
        patientList=new ArrayList<>();
        Calendar cr =Calendar.getInstance();
        month = cr.get(Calendar.MONTH);
        day = cr.get(Calendar.DAY_OF_MONTH);
        Cursor c=sqLiteDatabase.query(MedicalConstant.TABLE_NAME2,null,null,null,null,null,null);
        if (c != null && c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(MedicalConstant.PFNAME));
                int age = c.getInt(c.getColumnIndex(MedicalConstant.PAGE));
                int height = c.getInt(c.getColumnIndex(MedicalConstant.PHEIGHT));
                String pro = c.getString(c.getColumnIndex(MedicalConstant.PPROBLEM));
                String phon = c.getString(c.getColumnIndex(MedicalConstant.PNUMBER));
                int day1 = c.getInt(c.getColumnIndex(MedicalConstant.Appoint));
                int month1 = c.getInt(c.getColumnIndex(MedicalConstant.Appoint1));
                patient = new Patient(name,pro,age,height,phon,day1,month1);
                patientList.add(patient);
            }
            while (c.moveToNext());
        }
        ArrayAdapter<Patient> ad = new ArrayAdapter<Patient>(this, android.R.layout.simple_list_item_1, patientList);
        listView.setAdapter(ad);
        c.close();
    }
}

