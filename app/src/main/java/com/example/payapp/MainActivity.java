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
    SQLiteDatabase db;
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
        db.execSQL("CREATE TABLE IF NOT EXISTS employee(name VARCHAR,emp_id INTEGER,password VARCHAR,employer INTEGER,email VARCHAR,phone VARCHAR,joindate DATE, dob DATE, interpersonal_skills VARCHAR, achievements VARCHAR,rank INTEGER, rating INTEGER,pay INTEGER);");
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
            Toast.makeText(this, c.getColumnName(0), Toast.LENGTH_SHORT).show();
            // Displaying record if found 
            if(c.getString(0).equals(password.getText().toString())) {
                if(c.getString(1).equals("-1")) {
                       
                }
                else {

                }
            }
        } else {
            Toast.makeText(this, "INVALID LOGIN!", Toast.LENGTH_SHORT).show();
        }
    }
}
