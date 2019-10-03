package com.example.payapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class employee_profile extends AppCompatActivity {

    EmpClass employeeDetails;
//    public String phone, email, dob, name, designation, joinDate, achievements;
//    int empNo;
//    Boolean manager;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);
        getSupportActionBar().hide();
        employeeDetails = new EmpClass();
        employeeDetails.name = ((TextView)findViewById(R.id.usrname)).getText().toString();
        employeeDetails.phone = ((TextView)findViewById(R.id.phonenumber)).getText().toString();
        employeeDetails.email = ((TextView)findViewById(R.id.usremail)).getText().toString();
        employeeDetails.dob = ((TextView)findViewById(R.id.dob)).getText().toString();
        employeeDetails.designation = ((TextView)findViewById(R.id.desig)).getText().toString();
        employeeDetails.joinDate = ((TextView)findViewById(R.id.joindate)).getText().toString();
        employeeDetails.achievements = ((TextView)findViewById(R.id.achieve)).getText().toString();
        employeeDetails.empNo = Integer.parseInt(((TextView )findViewById(R.id.empno)).getText().toString());
        ;

    }
    public void edit(View view) {
        Intent i = new Intent(employee_profile.this,employeeEdit.class);
        i.putExtra("phone",employeeDetails.phone);
        i.putExtra("email",employeeDetails.email);
        i.putExtra("dob",employeeDetails.dob);
        i.putExtra("name",employeeDetails.name);
        i.putExtra("designation",employeeDetails.designation);
        i.putExtra("joinDate",employeeDetails.joinDate);
        i.putExtra("achievements",employeeDetails.achievements);
        i.putExtra("empNo",employeeDetails.empNo);
        startActivity(i);
    }
}
