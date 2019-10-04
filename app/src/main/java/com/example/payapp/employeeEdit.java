package com.example.payapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class employeeEdit extends AppCompatActivity {

    EmpClass e;
    Bundle b;
    public EditText phone, email, dob, name, designation, joinDate, achievements,empNo,manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit);
        getSupportActionBar().hide();
        e = new EmpClass();
        b = getIntent().getExtras();
        e.name = b.getString("name");
        e.phone =b.getString("phone");
        e.email =b.getString("email");
        e.dob =b.getString("dob");
        e.designation =b.getString("designation");
        e.joinDate =b.getString("joinDate");
        e.achievements =b.getString("achievements");

        phone = findViewById(R.id.phonenumber);
        email = findViewById(R.id.usremail);
        dob = findViewById(R.id.dob);
        name = findViewById(R.id.usrname);
        designation = findViewById(R.id.desig);
        joinDate = findViewById(R.id.joindate);
        achievements = findViewById(R.id.achieve);
        manager = findViewById(R.id.manager);

        phone.setText(e.phone);
        email.setText(e.email);
        dob.setText(e.dob);
        name.setText(e.name);
        designation.setText(e.designation);
        joinDate.setText(e.joinDate);
        achievements.setText(e.achievements);
    }
    public void edit(View view) {


        //UPDATE database with updated values


        Intent i = new Intent(employeeEdit.this,employee_profile.class);
        startActivity(i);
    }
}
