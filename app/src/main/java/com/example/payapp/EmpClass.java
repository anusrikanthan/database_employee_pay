package com.example.payapp;

public class EmpClass {
    public String phone, email, dob, name, designation, joinDate, achievements;
    int empNo;
    Boolean manager;

    EmpClass(String name,String designation,int empNo) {
        this.name = name;
        this.designation = designation;
        this.empNo = empNo;
    }
    EmpClass() {

    }
}
