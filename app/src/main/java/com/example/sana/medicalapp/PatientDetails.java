package com.example.sana.medicalapp;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sana.medicalapp.dbutil.MedicalConstant;
import com.example.sana.medicalapp.dbutil.MedicalManager;

import java.util.Calendar;
import java.util.Date;

public class PatientDetails extends AppCompatActivity implements View.OnClickListener {
    TextView TextPatient;
    EditText name, age, height, problem, phone, date;
    MedicalManager medicalManager;
    SQLiteDatabase sqLiteDatabase;
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        TextPatient = findViewById(R.id.TextPatient);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        problem = findViewById(R.id.problem);
        phone = findViewById(R.id.phone);
        date = findViewById(R.id.date);
        medicalManager = new MedicalManager(this);
        sqLiteDatabase = medicalManager.openDB();
        date.setOnClickListener(PatientDetails.this);
        //registerReceiver( new MyReceiver(),new IntentFilter("com.IT"));
    }
    public String getDate() {
        StringBuilder builder = new StringBuilder();
        builder.append(day + "/");
        builder.append((month + 1) + "/");//month is 0 based
        builder.append(year);
        return builder.toString();
    }
    public void onClick(View view) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mdatepicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int syear, int smonth, int sday) {
                day = sday;
                month = smonth;
                year = syear;
                date.setText(getDate());
            }
        }, mYear, mMonth, mDay);
        mdatepicker.setTitle("Select date");
        mdatepicker.show();
    }
    public void PatientDetail(View view) {
        String pname = name.getText().toString().trim();
        String page = age.getText().toString().trim();
        String pheight = height.getText().toString().trim();
        String pproblem = problem.getText().toString().trim();
        String phn = phone.getText().toString().trim();
        String pdate = getDate();
        String pdaay = String.valueOf(day);
        String pmoonth = String.valueOf(month + 1);

        if (TextUtils.isEmpty(pname) || TextUtils.isEmpty(page) || TextUtils.isEmpty(pheight) || TextUtils.isEmpty(pproblem) || TextUtils.isEmpty(pdate)) {
            if (TextUtils.isEmpty(pname)) {
                name.setError("Enter name");
                name.requestFocus();
            }
            if (TextUtils.isEmpty(page)) {
                age.setError("Enter Age");
                age.requestFocus();
            }
            if (TextUtils.isEmpty(pheight)) {
                height.setError("Enter height");
                height.requestFocus();
            }
            if (TextUtils.isEmpty(pproblem)) {
                problem.setError("Enter Problem");
                problem.requestFocus();
            }
            if (TextUtils.isEmpty(pdate)) {
                problem.setError("Enter date");
                problem.requestFocus();
            }
            if (TextUtils.isEmpty(phn)) {
                problem.setError("Enter phone number");
                problem.requestFocus();
            }
        }
        else
        {
            Toast.makeText(this, "day="+pdaay+"   month"+pmoonth, Toast.LENGTH_LONG).show();
            ContentValues cv = new ContentValues();
            cv.put(MedicalConstant.PFNAME, pname);
            cv.put(MedicalConstant.PAGE, page);
            cv.put(MedicalConstant.PHEIGHT, pheight);
            cv.put(MedicalConstant.PPROBLEM, pproblem);
            cv.put(MedicalConstant.PNUMBER, phn);
            cv.put(MedicalConstant.Appoint, pdaay);
            cv.put(MedicalConstant.Appoint1, pmoonth);
            long l = sqLiteDatabase.insert(MedicalConstant.TABLE_NAME2, null, cv);
            if (l > 0) {
                Toast.makeText(this, "data inserted", Toast.LENGTH_LONG).show();
                Toast.makeText(this,phn,Toast.LENGTH_SHORT).show();
                SmsManager sm=SmsManager.getDefault();
                Intent intent=new Intent(this,PatientDetails.class);
                PendingIntent pi=PendingIntent.getActivity(this,2,intent,PendingIntent.FLAG_ONE_SHOT);
                sm.sendTextMessage(phn,null,"your appintment has been scheduled on="+pdaay+"/"+pmoonth,pi,null);
                Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void PatientService(View view) {
        Intent i=new Intent(this,MyService.class);
        startService(i);
    }
}

