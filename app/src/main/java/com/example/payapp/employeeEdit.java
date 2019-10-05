package com.example.payapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class employeeEdit extends AppCompatActivity {

    SQLiteDatabase db, proj, rev, ids;
    EmpClass e;
    Bundle b;
    public EditText phone, email, dob, name, designation, joinDate, inter,achievements, password;
    public TextView empNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit);
        getSupportActionBar().hide();

        db = openOrCreateDatabase("Employee", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS employee(name VARCHAR,designation VARCHAR,emp_id INTEGER,password VARCHAR,employer INTEGER,email VARCHAR,phone VARCHAR,joindate DATE, dob DATE, interpersonal_skills VARCHAR, achievements VARCHAR,rank INTEGER, rating INTEGER,pay INTEGER);");

        proj = openOrCreateDatabase("Project", Context.MODE_PRIVATE, null);
        proj.execSQL("CREATE TABLE IF NOT EXISTS project(project_id INTEGER, start_date DATE, status VARCHAR, proj_desc VARCHAR, deadline DATE, completion_date DATE, emp_id INTEGER);");

        rev = openOrCreateDatabase("Review", Context.MODE_PRIVATE, null);
        rev.execSQL("CREATE TABLE IF NOT EXISTS review(review_no INTEGER, emp_pay FLOAT, rank INTEGER, appraisal_rating VARCHAR, emp_name VARCHAR, reviewer_name VARCHAR, proj_id INTEGER);");

        ids = openOrCreateDatabase("IDs", Context.MODE_PRIVATE, null);
        ids.execSQL("CREATE TABLE IF NOT EXISTS ids(review_no INTEGER, proj_id INTEGER);");

        e = new EmpClass();
        b = getIntent().getExtras();

        phone = findViewById(R.id.phonenumber);
        email = findViewById(R.id.usremail);
        dob = findViewById(R.id.dob);
        name = findViewById(R.id.usrname);
        designation = findViewById(R.id.desig);
        joinDate = findViewById(R.id.joindate);
        achievements = findViewById(R.id.achieve);
        password = findViewById(R.id.passwordfield);
        empNo = findViewById(R.id.empno);
        inter = findViewById(R.id.inter);

        e.name = b.getString("name");
        e.phone = b.getString("phone");
        e.email = b.getString("email");
        e.dob = b.getString("dob");
        e.designation = b.getString("designation");
        e.joinDate = b.getString("joinDate");
        e.achievements = b.getString("achievements");
        e.empNo = Integer.parseInt(b.getString("empNo"));
        e.interpersonalSkill = b.getString("interp");
        e.password = b.getString("password");

        phone.setText(e.phone);
        email.setText(e.email);
        dob.setText(e.dob);
        name.setText(e.name);
        designation.setText(e.designation);
        joinDate.setText(e.joinDate);
        achievements.setText(e.achievements);
        empNo.setText(String.valueOf(e.empNo));
        inter.setText(e.interpersonalSkill);
        password.setText(e.password);
    }

    public void edit(View view) {
        Toast.makeText(this, "Details successfully edited!", Toast.LENGTH_SHORT).show();
        Cursor c = db.rawQuery("SELECT * FROM employee WHERE emp_id=" + e.empNo, null);
        if (c.moveToFirst()) {
            // Modifying record if found 
//       (name VARCHAR,emp_id INTEGER,password VARCHAR,employer INTEGER,email VARCHAR,phone VARCHAR,joindate DATE, dob DATE, interpersonal_skills VARCHAR, achievements VARCHAR,rank INTEGER, rating INTEGER,pay INTEGER);");

            db.execSQL("UPDATE employee SET phone='" + phone.getText() + "',email='" + email.getText() +"',interpersonal_skills='" + inter.getText() +"',dob='" + dob.getText() + "',name='" + name.getText() + "',designation='" + designation.getText() + "',joindate='" + joinDate.getText() + "',achievements='" + achievements.getText() + "',password='" + password.getText() + "' WHERE emp_id=" + e.empNo);

//            Intent i = new Intent(employeeEdit.this, employee_profile.class);
            Intent i = new Intent(employeeEdit.this, MainActivity.class);
            Toast.makeText(this, "Details changed! Please log in again to preesrve integrity of data!", Toast.LENGTH_SHORT).show();
            i.putExtra("reg_emp_id",e.empNo);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }
}
