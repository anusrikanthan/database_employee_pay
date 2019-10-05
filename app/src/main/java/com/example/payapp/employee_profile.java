package com.example.payapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class employee_profile extends AppCompatActivity {

    SQLiteDatabase db;
    EmpClass employeeDetails;
//    public String phone, email, dob, name, designation, joinDate, achievements;
//    int empNo;
//    Boolean manager;
    String pass;
    public TextView phone, email, dob, name, designation, joinDate, achievements,empNo,manager,inter,pay;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);
        getSupportActionBar().hide();

        db = openOrCreateDatabase("Employee", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS employee(name VARCHAR,designation VARCHAR,emp_id INTEGER,password VARCHAR,employer INTEGER,email VARCHAR,phone VARCHAR,joindate DATE, dob DATE, interpersonal_skills VARCHAR, achievements VARCHAR,rank INTEGER, rating INTEGER,pay INTEGER);");
        Bundle b = getIntent().getExtras();

        String reg_emp_no = b.getString("reg_emp_id");
        phone = findViewById(R.id.phonenumber);
        name = findViewById(R.id.usrname);
        email = findViewById(R.id.usremail);
        dob = findViewById(R.id.dob);
        designation = findViewById(R.id.desig);
        joinDate = findViewById(R.id.joindate);
        achievements = findViewById(R.id.achieve);
        empNo = findViewById(R.id.empno);
        manager = findViewById(R.id.manager);
        inter = findViewById(R.id.inter);
        pay = findViewById(R.id.payxx);

        Cursor c = db.rawQuery("SELECT * FROM employee WHERE emp_id='" + reg_emp_no + "'", null);
        if (c.moveToFirst()) {
            // Displaying record if found 
//            (name VARCHAR,designation VARCHAR,emp_id INTEGER,password VARCHAR,employer INTEGER,email VARCHAR,phone VARCHAR,joindate DATE, dob DATE, interpersonal_skills VARCHAR, achievements VARCHAR,rank INTEGER, rating INTEGER,pay INTEGER)
            phone.setText(c.getString(6));
            name.setText(c.getString(0));
            email.setText(c.getString(5));
            dob.setText(c.getString(8));
            designation.setText(c.getString(1));
            joinDate.setText(c.getString(7));
            achievements.setText(c.getString(10));
            empNo.setText(c.getString(2));
            if((c.getString(4)).equals("-1")) {
                manager.setText("No");
            }
            else {
                manager.setText("Yes");
            }
            inter.setText(c.getString(9));
            pass = c.getString(3);
            pay.setText(c.getString(13));
        }
    }
    public void edit(View view) {
        Intent i = new Intent(employee_profile.this,employeeEdit.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("phone",phone.getText().toString());
        i.putExtra("email",email.getText().toString());
        i.putExtra("dob",dob.getText().toString());
        i.putExtra("name",name.getText().toString());
        i.putExtra("designation",designation.getText().toString());
        i.putExtra("joinDate",joinDate.getText().toString());
        i.putExtra("achievements",achievements.getText().toString());
        i.putExtra("empNo",empNo.getText().toString());
        i.putExtra("interp",inter.getText().toString());
        i.putExtra("password",pass);
        startActivity(i);
//        finish();
//        overridePendingTransition(0, 0);
//        startActivity(getIntent());
//        overridePendingTransition(0, 0);
    }

}
