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
    EmpClass(String name,String designation,int emp_id, String password, int employer,String email,String phone, String joindate,String dob, String interpersonal_skill,String achievements, int rank, int rating, int pay) {
        this.name = name;
        this.designation = designation;
        this.empNo = emp_id;
        this.password = password;
        this.employer = employer;
        this.email = email;
        this.phone = phone;
        this.joinDate = joindate;
        this.dob = dob;
        this.interpersonalSkill = interpersonal_skill;
        this.achievements = achievements;
        this.rank = rank;
        this.rating = rating;
        this.pay = pay;
    }
}
