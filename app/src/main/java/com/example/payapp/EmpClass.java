package com.example.payapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EmpClass {
    public String phone, email, dob, name, designation, joinDate, achievements,password,interpersonalSkill;
    int empNo,rank,rating,pay,employer;
    Boolean manager;

    EmpClass(String name,String designation,int empNo) {
        this.name = name;
        this.designation = designation;
        this.empNo = empNo;
    }
    EmpClass() {

    }
}
