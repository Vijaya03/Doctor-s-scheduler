package com.example.sana.medicalapp.dbutil;

/**
 * Created by SANA on 01-04-2018.
 */

public class MedicalConstant {
    public static final String DBNAME="medical";
    public static final String TABLE_NAME="doctor";
    public static final String TABLE_NAME1="clerk";
    public static final String TABLE_NAME2="patient";
    public static final int DBVERSION=1;
    public static final String FNAME="firstName";
    public static final String LNAME="lastName";
    public static final String EMAIL="email";
    public static final String PASSWORD="password";
    public static final String NUMBER="phone";
    public static final String CFNAME="firstName";
    public static final String CLNAME="lastName";
    public static final String CEMAIL="email";
    public static final String CPASSWORD="password";
    public static final String CNUMBER="phone";
    public static final String PFNAME="Name";
    public static final String PAGE="Age";
    public static final String PHEIGHT="height";
    public static final String PPROBLEM="problem";
    public static final String PNUMBER="phone";
    public static final String Appoint="day";
    public static final String Appoint1="month";
    public static final String DoctorNumber="9919679734";
    public static final String PA_SQL="create table patient(Name text primary key,Age integer,height text,problem text,phone text ,day text,month text)";
    public static final String MED_SQL="create table doctor(firstName text,lastName text,email text primary key,password text,phone text)";
    public static final String CL_SQL="create table clerk(firstName text,lastName text,email text primary key,password text,phone text)";
}
