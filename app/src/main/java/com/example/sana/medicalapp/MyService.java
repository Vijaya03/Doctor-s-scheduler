package com.example.sana.medicalapp;


import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.sana.medicalapp.dbutil.MedicalConstant;
import com.example.sana.medicalapp.dbutil.MedicalManager;

import java.util.Calendar;

/**
 * Created by SANA on 13-04-2018.
 */


public class MyService extends Service {
    MedicalManager medicalManager;
    SQLiteDatabase sqLiteDatabase;
    String appoint,appoint1;
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"start",Toast.LENGTH_SHORT).show();
        medicalManager=new MedicalManager(this);
        sqLiteDatabase=medicalManager.openDB();

        Calendar c=Calendar.getInstance();
        String[] arg={MedicalConstant.Appoint,MedicalConstant.Appoint1};
        int mMonth=c.get(Calendar.MONTH);
        String a=String.valueOf(mMonth+1);
        int mDay=c.get(Calendar.DAY_OF_MONTH);
        String b=String.valueOf(mDay);
        Cursor cursor=sqLiteDatabase.query(MedicalConstant.TABLE_NAME2,arg,null,null,null,null,null);
        if (cursor != null && cursor.moveToFirst()) {
            //Toast.makeText(this,a+b,Toast.LENGTH_SHORT).show();
            do {
                appoint = cursor.getString(cursor.getColumnIndex(MedicalConstant.Appoint));
                appoint1 = cursor.getString(cursor.getColumnIndex(MedicalConstant.Appoint1));
            }
            while (cursor.moveToNext());
        }
//        int mon=Integer.parseInt(appoint1);
//        int day=Integer.parseInt(appoint);
        if(a.equals(appoint1) && b.equals(appoint))
        {
            SmsManager sm=SmsManager.getDefault();
            Intent i=new Intent(this,PatientDetails.class);
            PendingIntent pi=PendingIntent.getActivity(this,2,i,PendingIntent.FLAG_ONE_SHOT);
            sm.sendTextMessage(MedicalConstant.DoctorNumber,null,"You have an Appointment",pi,null);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
