package com.example.payapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText empno;
    EditText password;
    SQLiteDatabase db,proj,rev,ids;
//        "name":"pranesh",
//            "emp_id":"1",
//            "password":"qwerty",
//            "employer":"0",
//            "email":"123",
//            "phone":"123",
//            "joindate":"1-3-15",
//              "dob" : "1-1-1"
//            "interpersonal_skill":"lol",
//            "achievements":"none",
//            "rank":-1,
//            "rating":-1,
//            "pay":-1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("Employee", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS employee(name VARCHAR,designation VARCHAR,emp_id INTEGER,password VARCHAR,employer INTEGER,email VARCHAR,phone VARCHAR,joindate DATE, dob DATE, interpersonal_skills VARCHAR, achievements VARCHAR,rank INTEGER, rating INTEGER,pay INTEGER);");

        proj = openOrCreateDatabase("Project", Context.MODE_PRIVATE, null);
        proj.execSQL("CREATE TABLE IF NOT EXISTS project(project_id INTEGER, start_date DATE, status VARCHAR, proj_desc VARCHAR, deadline DATE, completion_date DATE, emp_id INTEGER);");

        rev = openOrCreateDatabase("Review", Context.MODE_PRIVATE, null);
        rev.execSQL("CREATE TABLE IF NOT EXISTS review(review_no INTEGER, emp_pay FLOAT, rank INTEGER, appraisal_rating VARCHAR, emp_name VARCHAR, reviewer_name VARCHAR, proj_id INTEGER);");

        ids = openOrCreateDatabase("IDs", Context.MODE_PRIVATE, null);
        ids.execSQL("CREATE TABLE IF NOT EXISTS ids(review_no INTEGER, proj_id INTEGER);");
        getSupportActionBar().hide();
    }
    public void registerOpen(View view) {
        Intent i = new Intent(MainActivity.this,Register.class);
        startActivity(i);
    }
    public void signin(View view) {
        empno = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);

        Cursor c = db.rawQuery("SELECT password,employer FROM employee WHERE emp_id='" + empno.getText() + "'", null);
        if (c.moveToFirst()) {
            // Displaying record if found 
            if(c.getString(0).equals(password.getText().toString())) {
                if(c.getString(1).equals("-1")) {
                    Intent i = new Intent(MainActivity.this,employee_profile.class);
                    i.putExtra("reg_emp_id",empno.getText().toString());
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(MainActivity.this,Employees.class);
                    i.putExtra("reg_emp_id",empno.getText().toString());
                    startActivity(i);
                }
            }
            else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
