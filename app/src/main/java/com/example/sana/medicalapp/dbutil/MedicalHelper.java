package com.example.sana.medicalapp.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by SANA on 01-04-2018.
 */

public class MedicalHelper extends SQLiteOpenHelper {
    Context context;
    public MedicalHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MedicalConstant.MED_SQL);
        sqLiteDatabase.execSQL(MedicalConstant.CL_SQL);
        sqLiteDatabase.execSQL(MedicalConstant.PA_SQL);
        Toast.makeText(context,"Table created",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
