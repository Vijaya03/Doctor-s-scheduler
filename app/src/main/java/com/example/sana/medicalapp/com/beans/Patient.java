package com.example.sana.medicalapp.com.beans;

/**
 * Created by SANA on 08-04-2018.
 */

public class Patient {
    String name,prob,phn;
    int age,height,day,month;
    public String toString()
    {
        return "name="+name+"\t\t"+"Phone="+phn;
    }

    public Patient(String name, String prob, int age, int height, String phn, int day, int month) {
        this.name = name;
        this.prob = prob;
        this.age = age;
        this.height = height;
        this.phn = phn;
        this.day = day;
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProb() {
        return prob;
    }

    public void setProb(String prob) {
        this.prob = prob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
