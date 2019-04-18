package com.example.sana.medicalapp.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SANA on 01-04-2018.
 */

public class MedicalManager {
    MedicalHelper medicalHelper;
    SQLiteDatabase sqLiteDatabase;
    public MedicalManager(Context context)
    {
        medicalHelper=new MedicalHelper(context,MedicalConstant.DBNAME,null,MedicalConstant.DBVERSION);
    }
    public SQLiteDatabase openDB()
    {
        sqLiteDatabase=medicalHelper.getWritableDatabase();
        return sqLiteDatabase;
    }
    public void closeDB()
    {
        medicalHelper.close();
    }
}

