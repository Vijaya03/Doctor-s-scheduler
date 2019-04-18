package com.example.sana.medicalapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sana.medicalapp.dbutil.MedicalConstant;
import com.example.sana.medicalapp.dbutil.MedicalManager;

public class ClerkRegister extends AppCompatActivity {
    EditText cfirstName,clastName,cemail,cpassword,cnumber;
    MedicalManager medicalManager;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk_register);
        cfirstName=findViewById(R.id.cfirstName);
        clastName=findViewById(R.id.clastName);
        cemail=findViewById(R.id.cemail);
        cnumber=findViewById(R.id.cnumber);
        cpassword=findViewById(R.id.cpassword);
        medicalManager=new MedicalManager(this);
        sqLiteDatabase=medicalManager.openDB();
    }

    public void clerkRegister(View view) {
        String fname=cfirstName.getText().toString();
        String lname=clastName.getText().toString();
        String em=cemail.getText().toString();
        String pass=cpassword.getText().toString();
        String phn=cnumber.getText().toString();
        if(TextUtils.isEmpty(fname)|| TextUtils.isEmpty(lname) || TextUtils.isEmpty(em) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(phn))
        {
            if(TextUtils.isEmpty(fname)){
                cfirstName.setError("Enter first name");
                cfirstName.requestFocus();
            }
            if(TextUtils.isEmpty(lname)){
                clastName.setError("Enter last name");
                clastName.requestFocus();
            }
            if(TextUtils.isEmpty(em)){
                cemail.setError("Enter email");
                cemail.requestFocus();
            }
            if(TextUtils.isEmpty(pass)){
                cpassword.setError("Enter password");
                cpassword.requestFocus();
            }
            if(TextUtils.isEmpty(phn)){
                cnumber.setError("Enter phone");
                cnumber.requestFocus();
            }
        }
        if(fname.isEmpty())
            Toast.makeText(this,"Please Enter First Name",Toast.LENGTH_SHORT).show();
        if(lname.isEmpty())
            Toast.makeText(this,"Please Enter Last Name",Toast.LENGTH_SHORT).show();
        if(pass.isEmpty())
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
        ContentValues cv=new ContentValues();
        cv.put(MedicalConstant.CFNAME,fname);
        cv.put(MedicalConstant.CLNAME,lname);
        cv.put(MedicalConstant.CEMAIL,em);
        cv.put(MedicalConstant.CPASSWORD,pass);
        cv.put(MedicalConstant.CNUMBER,phn);
        long l=sqLiteDatabase.insert(MedicalConstant.TABLE_NAME1,null,cv);
        if( l > 0)
        { Toast.makeText(this,"data inserted",Toast.LENGTH_LONG).show();}
//        Intent i=new Intent(this,MainActivity.class);
//        startActivity(i);
    }
}
